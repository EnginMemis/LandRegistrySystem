CREATE TABLE customer (
	ssn NUMERIC(9,0) PRIMARY KEY,
	fname VARCHAR(64),
	lname VARCHAR(32),
	birth_date DATE,
	gender VARCHAR(8),
	phone_number VARCHAR(16),
	email VARCHAR(64),
	address VARCHAR(256),
	wallet NUMERIC(17, 2)
);

CREATE TABLE employee (
	ssn NUMERIC(9,0) PRIMARY KEY,
	fname VARCHAR(64),
	lname VARCHAR(32),
	birth_date DATE,
	gender VARCHAR(8),
	phone_number VARCHAR(16),
	email VARCHAR(64),
	address VARCHAR(256),
	wallet NUMERIC(17, 2)
);

CREATE TABLE property (
	property_id NUMERIC PRIMARY KEY,
	address VARCHAR(256),
	property_type VARCHAR(64),
	property_value NUMERIC(17,2),
	area NUMERIC(6,1)
);

CREATE TABLE property_feature (
	feature_id NUMERIC PRIMARY KEY,
	property_id NUMERIC,
	title VARCHAR(16),
	value VARCHAR(64)
);

CREATE TABLE land_registry (
	land_registry_id NUMERIC PRIMARY KEY,
	property_id NUMERIC,
	price NUMERIC(17,2),
	date DATE
);

CREATE TABLE owns (
	owner_ssn NUMERIC(9,0),
	lr_id NUMERIC,
	
	PRIMARY KEY (owner_ssn, lr_id)
);

ALTER TABLE property_feature ADD FOREIGN KEY (property_id) REFERENCES property(property_id) ON DELETE CASCADE;
ALTER TABLE land_registry ADD FOREIGN KEY (property_id) REFERENCES property(property_id) ON DELETE CASCADE;
ALTER TABLE owns ADD FOREIGN KEY (owner_ssn) REFERENCES customer(ssn) ON DELETE CASCADE;
ALTER TABLE owns ADD FOREIGN KEY (lr_id) REFERENCES land_registry(land_registry_id) ON DELETE CASCADE;
