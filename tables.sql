CREATE SEQUENCE user_id_seq INCREMENT 1 START 1000000;
CREATE SEQUENCE property_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE property_feature_id_seq INCREMENT 1 START 1;
CREATE SEQUENCE land_registry_id_seq INCREMENT 1 START 1;

CREATE TABLE user (
	ssn NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('user_id_seq'),
	fname VARCHAR(64) NOT NULL,
	lname VARCHAR(32) NOT NULL,
	birth_date DATE,
	gender VARCHAR(8),
	phone_number VARCHAR(16),
	email VARCHAR(64),
	address VARCHAR(256),
	wallet DOUBLE NOT NULL,
	role VARCHAR(16) NOT NULL
);

CREATE TABLE property (
	property_id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('property_id_seq'),
	address VARCHAR(256) NOT NULL,
	property_type VARCHAR(64) NOT NULL,
	property_value DOUBLE,
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
	user_id NUMERIC NOT NULL
	price DOUBLE NOT NULL,
	issued_at DATE,
	isActive BOOLEAN NOT NULL DEFAULT true,
	
	FOREIGN KEY (property_id) REFERENCES property(property_id) ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE
);

ALTER TABLE user ADD CONSTRAINT check_role CHECK (role IN ('Customer', 'Employee'));

CREATE VIEW user_land_registry AS
	SELECT ssn, fname, lname, birth_date, gender, phone_number, email, address, wallet, role, land_registry_id, property_id, price, date, 
	FROM user INNER JOIN land_registry ON user.user_id = land_registry.user_id;

CREATE VIEW land_registry_property AS
	SELECT land_registry_id, user_id, price, date, isActive, property_id, address, property_type, property_value, area
	FROM land_registry INNER JOIN property ON land_registry.property_id = property.property_id;

CREATE OR REPLACE FUNCTION sell_property(buyer_id user.user_id%TYPE, seller_id user.user_id%TYPE, property_id property.property_id%TYPE, sell_price DOUBLE)
RETURNS VOID AS $$
DECLARE
	buyer_wallet DOUBLE;
	seller_wallet DOUBLE;
BEGIN
	-- Get wallets of buyer and seller into variables
	SELECT wallet INTO buyer_wallet FROM user WHERE user_id = buyer_id;
	SELECT wallet INTO seller_wallet FROM user WHERE user_id = seller_id;
	
	-- Check if both buyer and seller have enough money for 2.5% cut
	IF buyer_wallet < price * 1.025 OR seller_wallet < price * 0.025 THEN
		RETURN;
	END IF;
	
	-- Update wallets of buyer and seller
	UPDATE user SET wallet = wallet - price * 1.025 WHERE user_id = buyer_id;
	UPDATE user SET wallet = wallet + price * 0.975 WHERE user_id = seller_id;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION circulating_capital(price DOUBLE)
RETURNS TRIGGER AS $$
DECLARE
	amount_to_share DOUBLE;
	number_of_employees NUMERIC;
BEGIN
	-- How many employees do we have?
	SELECT count(*) INTO number_of_employees FROM user WHERE role = 'Employee';
	
	-- Calculate how much money we will send to wallets of each employee
	amount_to_share := price * 0.05 / number_of_employees;
	
	-- Send money to wallets
	UPDATE user SET wallet = wallet + amount_to_share WHERE role = 'Employee';
END;
$$ LANGUAGE 'plpgsql';
