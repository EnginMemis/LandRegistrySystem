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
	wallet NUMERIC(15,2) NOT NULL,
	user_role VARCHAR(16) NOT NULL
);

CREATE TABLE property (
	property_id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('property_id_seq'),
	address VARCHAR(256) NOT NULL,
	property_type VARCHAR(64) NOT NULL,
	property_value NUMERIC(15,2),
	area NUMERIC(6,1)
);

CREATE TABLE property_feature (
	feature_id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('property_feature_id_seq'),
	property_id NUMERIC NOT NULL,
	title VARCHAR(16) NOT NULL,
	value VARCHAR(64),
	
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

CREATE VIEW user_land_registry AS
	SELECT ssn, fname, lname, birth_date, gender, phone_number, email, address, wallet, user_role, land_registry_id, property_id, price, issued_at 
	FROM users INNER JOIN land_registry ON users.ssn = land_registry.buyer_ssn;

CREATE VIEW land_registry_property AS
	SELECT land_registry_id, buyer_ssn, price, issued_at, is_active, property.property_id, address, property_type, property_value, area
	FROM land_registry INNER JOIN property ON land_registry.property_id = property.property_id;

CREATE OR REPLACE FUNCTION sell_property()
RETURNS TRIGGER AS $$
DECLARE
	amount_to_share NUMERIC(15,2);
	number_of_employees NUMERIC;
	price NUMERIC(15,2);
	buyer_wallet NUMERIC(15,2);
	seller_wallet NUMERIC(15,2);
BEGIN
	-- Get wallets of buyer and seller into variables
	SELECT wallet INTO buyer_wallet FROM users WHERE ssn = new.buyer_ssn;
	SELECT wallet INTO seller_wallet FROM users WHERE ssn = new.seller_ssn;
	
	-- Check if both buyer and seller have enough money for 2.5% cut
	IF buyer_wallet < new.price * 1.025 THEN
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

INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Engin','Memiş',500000,'Customer');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Emirhan','Paksoy',70000000,'Customer');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Mehmet Anıl','Karaşah',10000,'Employee');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Nisa','Arslan',25000,'Employee');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Elif Sena','Yılmaz',2500000,'Customer');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Şeymanur','Korkmaz',17000000,'Customer');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Beyda','Güler',25000,'Customer');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Umut','Deşer',3100,'Employee');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Yiğit','Sökel',2500000,'Customer');
INSERT INTO users(fname,lname,wallet,user_role) VALUES ('Berkay','Demirhan',12000,'Employee');

INSERT INTO property(address,property_type) VALUES ('Davutpasa','Land');
INSERT INTO property(address,property_type) VALUES ('YTÜ Ortabahçe','Garden');
INSERT INTO property(address,property_type) VALUES ('Elektrik-Elektronik Fakültesi','Building');
INSERT INTO property(address,property_type) VALUES ('Fen-Edebiyat Fakültesi','Building');
INSERT INTO property(address,property_type) VALUES ('Büyük Ev','Cafe');
INSERT INTO property(address,property_type) VALUES ('Kayıntı','Cafe');
INSERT INTO property(address,property_type) VALUES ('Taş Bina','Building');

INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (1,1000002,1000001,100000);
INSERT INTO land_registry(property_id,buyer_ssn,seller_ssn,price) VALUES (1,1000001,1000002,600000);
