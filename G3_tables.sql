CREATE SEQUENCE user_id_seq INCREMENT 1 START 1000000;
CREATE SEQUENCE property_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE property_feature_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE land_registry_id_seq INCREMENT 1 START 1;

CREATE TABLE users (
	ssn NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('user_id_seq'),
	fname VARCHAR(64) NOT NULL,
	lname VARCHAR(32) NOT NULL,
	birth_date DATE,
	gender VARCHAR(8),
	phone_number VARCHAR(16),
	email VARCHAR(64),
	address VARCHAR(256),
	wallet NUMERIC(15,2) NOT NULL DEFAULT 0,
	user_role VARCHAR(16) NOT NULL DEFAULT 'Customer'
);

CREATE TABLE property (
	property_id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('property_id_seq'),
	address VARCHAR(256) NOT NULL,
	property_type VARCHAR(64) NOT NULL,
	property_value NUMERIC,
	area NUMERIC(6,1)
);

CREATE TABLE property_feature (
	feature_id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('property_feature_id_seq'),
	property_id NUMERIC NOT NULL,
	title VARCHAR(64) NOT NULL,
	value VARCHAR(16),
	
	FOREIGN KEY (property_id) REFERENCES property(property_id) ON DELETE CASCADE
);

CREATE TABLE land_registry (
	land_registry_id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('land_registry_id_seq'),
	property_id NUMERIC NOT NULL,
	buyer_ssn NUMERIC NOT NULL,
	seller_ssn NUMERIC NOT NULL,
	price NUMERIC(15,2) NOT NULL,
	issued_at DATE DEFAULT now(),
	is_active BOOLEAN NOT NULL DEFAULT true,
	
	FOREIGN KEY (property_id) REFERENCES property(property_id) ON DELETE CASCADE,
	FOREIGN KEY (buyer_ssn) REFERENCES users(ssn) ON DELETE CASCADE,
	FOREIGN KEY (seller_ssn) REFERENCES users(ssn) ON DELETE CASCADE
);

ALTER TABLE users ADD CONSTRAINT check_role CHECK (user_role IN ('Customer', 'Employee'));
ALTER TABLE users ADD CONSTRAINT chech_age CHECK ( EXTRACT(YEAR FROM AGE(NOW(), birth_date)) >= 18 );

CREATE VIEW user_land_registry AS
	SELECT ssn, fname, lname, birth_date, gender, phone_number, email, address, wallet, user_role, land_registry_id, property_id, price, issued_at, is_active
	FROM users INNER JOIN land_registry ON users.ssn = land_registry.buyer_ssn;

CREATE VIEW land_registry_property AS
	SELECT land_registry_id, buyer_ssn, seller_ssn, price, issued_at, is_active, property.property_id, address, property_type, property_value, area
	FROM land_registry INNER JOIN property ON land_registry.property_id = property.property_id;


CREATE OR REPLACE FUNCTION insertUser(first_name users.fname%type, last_name users.lname%type, b_date users.birth_date%type, userGender users.gender%type,phone users.phone_number%type, mail users.email%type, userAddress users.address%type, usrRole users.user_role%type)
    RETURNS VOID AS $$
DECLARE
    cur CURSOR FOR SELECT phone_number FROM users;
BEGIN
    FOR row IN cur LOOP
        IF row.phone_number = phone THEN
            RAISE WARNING 'Aynı Numaraya Sahip 2 Kişi Bulunamaz!';
            RETURN;
        END IF;
    END LOOP;
    INSERT INTO users (fname, lname, birth_date, gender, phone_number, email, address, user_role) VALUES (first_name, last_name, b_date, userGender, phone, mail, userAddress, usrRole);
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION insertProperty(pAddress property.address%type, pType property.property_type%type, pValue property.property_value%type, pArea property.area%type)
    RETURNS VOID AS $$
BEGIN
    INSERT INTO property (address, property_type, property_value, area) VALUES(pAddress, pType, pValue, pArea);
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION deleteUser(userSSN users.ssn%type)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM users WHERE ssn = userSSN;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION deleteProperty(propertyId property.property_id%type)
    RETURNS VOID AS $$
BEGIN
    DELETE FROM property WHERE property_id = propertyId;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION update_balance(user_ssn users.ssn%type, balance users.wallet%type)
    RETURNS VOID AS $$
BEGIN
    UPDATE users SET wallet = wallet + balance WHERE ssn = user_ssn;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION update_balance_trigger_function()
    RETURNS TRIGGER AS $$
BEGIN
    IF new.wallet < 0 THEN
        RAISE WARNING 'Bakiye Negatif Olamaz!';
        RETURN OLD;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER balance_trigger
    BEFORE UPDATE
    ON users
    FOR EACH ROW EXECUTE PROCEDURE update_balance_trigger_function();

CREATE TYPE rich_user AS (user_ssn INTEGER);

CREATE OR REPLACE FUNCTION max_user_land(user_address users.address%type)
    RETURNS rich_user AS $$
DECLARE
    rich rich_user;
    likes_statement VARCHAR(64);
BEGIN
    likes_statement := concat('%',user_address,'%');

    SELECT ssn into rich
    FROM user_land_registry
    WHERE address LIKE likes_statement AND is_active = TRUE
    GROUP BY ssn
    HAVING count(*) > 0;
    RETURN rich;
END;
$$ LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION sell_property()
RETURNS TRIGGER AS $$
DECLARE
	amount_to_share NUMERIC(15,2);
	number_of_employees NUMERIC;
	price NUMERIC(15,2);
	buyer_wallet NUMERIC(15,2);
	seller_wallet NUMERIC(15,2);
BEGIN
    -- Buyer and seller cannot be the same person
    IF new.buyer_ssn = new.seller_ssn THEN
        RAISE WARNING 'Alıcı ve Satıcı Aynı Kişi Olamaz!';
        RETURN NULL;
    END IF;

	-- Get wallets of buyer and seller into variables
	SELECT wallet INTO buyer_wallet FROM users WHERE ssn = new.buyer_ssn;
	SELECT wallet INTO seller_wallet FROM users WHERE ssn = new.seller_ssn;
	
	-- Check if both buyer and seller have enough money for 2.5% cut
	IF buyer_wallet < new.price * 1.025 THEN
        RAISE WARNING 'Alıcının Bakiyesi Yetersiz!';
		RETURN NULL;
	END IF;
	
	-- Update wallets of buyer and seller
	UPDATE users SET wallet = wallet - new.price * 1.025 WHERE ssn = new.buyer_ssn;
	UPDATE users SET wallet = wallet + new.price * 0.975 WHERE ssn = new.seller_ssn;
	
	-- How many employees do we have?
	SELECT count(*) INTO number_of_employees FROM users WHERE user_role = 'Employee';
	
	-- Calculate how much money we will send to wallets of each employee
	amount_to_share := new.price * 0.05 / number_of_employees;
	
	-- Send money to wallets
	UPDATE users SET wallet = wallet + amount_to_share WHERE user_role = 'Employee';
	
	UPDATE land_registry SET is_active = false WHERE property_id = new.property_id AND is_active = true;
	
	RETURN new;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER circulate_capital
BEFORE INSERT
ON land_registry
FOR EACH ROW EXECUTE PROCEDURE sell_property();


INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Engin','Memiş','2000-03-30','M', '0000000','engin.memis@std.yildiz.edu.tr', 'Güngören/İstanbul', 3000000, 'Employee');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Emirhan','Paksoy','2000-06-27','M','1111111','emirhan.paksoy@std.yildiz.edu.tr','Kadıköy/İstanbul',3000000,'Employee');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Mehmet Anıl','Karaşah','2001-05-14','M', '2222222','anil.karasah@std.yildiz.edu.tr', 'Esenler/İstanbul', 3000000, 'Employee');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Nisa','Arslan','1999-10-01','F', '3333333','nisa.arslan@std.yildiz.edu.tr', 'Esenler/İstanbul', 3000000, 'Customer');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Elif Sena','Yılmaz','2001-05-21','F', '4444444','sena.yilmaz@std.yildiz.edu.tr', 'Esenler/İstanbul', 3000000, 'Customer');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Şeyma','Korkmaz','2002-10-04','F', '5555555','seyma.korkmaz@std.yildiz.edu.tr', 'Bahçelievler/İstanbul', 3000000, 'Customer');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Beyda','Güler','2000-01-01','F', '6666666','beyda.guler@std.yildiz.edu.tr', 'Üsküdar/İstanbul', 3000000, 'Customer');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Ahmet','Tanrıöver','1990-11-27','M', '7777777','ahmet.tanriover@std.yildiz.edu.tr', 'Kadıköy/İstanbul', 3000000, 'Customer');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Yiğit','Sökel','2000-08-14','M', '8888888','yigit.sokel@std.yildiz.edu.tr', 'Güngören/İstanbul', 3000000, 'Customer');

INSERT INTO users(fname,lname,birth_date,gender,phone_number,email,address,wallet,user_role)
VALUES ('Berkay','Demirhan','2000-01-01','M', '9999999','berkay.demirhan@std.yildiz.edu.tr', 'Bahçelievler/İstanbul', 3000000, 'Customer');

INSERT INTO property(address,property_type,property_value,area) VALUES ('Davutpaşa','Land',2500000,64500);
INSERT INTO property(address,property_type,property_value,area) VALUES ('YTÜ Ortabahçe','Garden',800000,1250);
INSERT INTO property(address,property_type,property_value,area) VALUES ('Elektrik-Elektronik Fakültesi','Building',1500000,600);
INSERT INTO property(address,property_type,property_value,area) VALUES ('Fen-Edebiyat Fakültesi','Building',1500000,600);
INSERT INTO property(address,property_type,property_value,area) VALUES ('Büyük Ev','Cafe',1000000, 300);
INSERT INTO property(address,property_type,property_value,area) VALUES ('Kayıntı','Cafe', 900000, 200);
INSERT INTO property(address,property_type,property_value,area) VALUES ('Taş Bina','Building', 1250000, 350);
INSERT INTO property(address,property_type,property_value,area) VALUES ('8 Bit','Cafe', 600000, 100);
INSERT INTO property(address,property_type,property_value,area) VALUES ('YTÜ Konser Alanı','Garden',700000, 850);
INSERT INTO property(address,property_type,property_value,area) VALUES ('İnşaat Fakültesi','Building', 900000, 400);

INSERT INTO property_feature(property_id, title, value) VALUES (2, 'Number Of Tree', '200');
INSERT INTO property_feature(property_id, title, value) VALUES (2, 'Number Of Desk', '30');
INSERT INTO property_feature(property_id, title, value) VALUES (3, 'Number Of Room', '40');
INSERT INTO property_feature(property_id, title, value) VALUES (4, 'Number Of Room', '50');
INSERT INTO property_feature(property_id, title, value) VALUES (5, 'Number Of Table', '45');
INSERT INTO property_feature(property_id, title, value) VALUES (6, 'Number Of Table', '25');
INSERT INTO property_feature(property_id, title, value) VALUES (7, 'Number Of Room', '30');
INSERT INTO property_feature(property_id, title, value) VALUES (8, 'Number Of Table', '15');
INSERT INTO property_feature(property_id, title, value) VALUES (9, 'Number Of Tree', '110');
INSERT INTO property_feature(property_id, title, value) VALUES (9, 'Number Of Desk', '10');
INSERT INTO property_feature(property_id, title, value) VALUES (10, 'Number Of Room', '35');

INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (1,1000000,1000003,2500000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (2,1000004,1000000,800000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (3,1000002,1000001,1500000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (4,1000006,1000008,1500000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (5,1000000,1000005,1000000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (6,1000005,1000006,900000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (7,1000009,1000007,1250000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (8,1000004,1000006,600000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (9,1000002,1000007,700000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (10,1000001,1000009,900000);


