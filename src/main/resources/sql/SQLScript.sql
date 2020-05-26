DROP DATABASE IF EXISTS autocamper_db;
CREATE DATABASE autocamper_db;
USE autocamper_db;


CREATE TABLE autocamper_type (
	brand VARCHAR(25) NOT NULL,
	model VARCHAR(45) NOT NULL,
	price INT NOT NULL,
	production_year DATE NOT NULL,
	weight INT NOT NULL,
	fuel_capacity INT NOT NULL,
	horse_power INT NOT NULL,
	max_speed INT NOT NULL,
	standing_height INT NOT NULL,
	area_sqm INT NOT NULL,
	height VARCHAR(45) NOT NULL,
	length INT NOT NULL,
	width INT NOT NULL,
	description VARCHAR(1000) NOT NULL,
  PRIMARY KEY (brand, model)
);

CREATE TABLE built_in_feature(
  id INT PRIMARY KEY AUTO_INCREMENT,
  picture VARCHAR(45) NULL,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(200) NULL
  );

CREATE TABLE type_features (
  type_brand VARCHAR(25) NOT NULL,
  type_model VARCHAR(45) NOT NULL,
  feature_id INT NOT NULL,
  PRIMARY KEY (type_brand, type_model, feature_id),
  CONSTRAINT fk_type_features_type1
    FOREIGN KEY (type_brand , type_model)
    REFERENCES autocamper_type (brand , model),
  CONSTRAINT fk_type_features_built_in_feature
    FOREIGN KEY (feature_id)
    REFERENCES built_in_feature (id)
);


CREATE TABLE autocamper(
  id INT PRIMARY KEY AUTO_INCREMENT,
  mileage INT NOT NULL,
  current_status TINYINT NOT NULL,
  picture VARCHAR(20) DEFAULT NULL,
  fk_brand VARCHAR(25) NOT NULL,
  fk_model VARCHAR(45) NOT NULL,
  CONSTRAINT fk_autocamper_type
    FOREIGN KEY (fk_brand , fk_model)
    REFERENCES autocamper_type (brand , model)
);


CREATE TABLE customer(
  id INT PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(45) NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone_nr VARCHAR(45) NOT NULL,
  cpr_nr INT NOT NULL,
  postalcode INT NOT NULL,
  street_name VARCHAR(45) NOT NULL,
  street_nr VARCHAR(45) NOT NULL,
  apartment_floor VARCHAR(45) NOT NULL,
  card_nr BIGINT NULL DEFAULT NULL,
  cvv SMALLINT NULL DEFAULT NULL
  );



CREATE TABLE maintenance (
  id INT PRIMARY KEY AUTO_INCREMENT,
  fuel_gauge INT NOT NULL,
  mileage INT NOT NULL,
  cleaning_price INT NOT NULL,
  maintenance_notes VARCHAR(500) NOT NULL,
  frame TINYINT NOT NULL,
  wheels TINYINT NOT NULL,
  lights TINYINT NOT NULL,
  oil TINYINT NOT NULL,
  battery TINYINT NOT NULL,
  interior TINYINT NOT NULL,
  coolingflued TINYINT NOT NULL,
  brakes TINYINT NOT NULL,
  suspention TINYINT NOT NULL,
  maintenance_date DATE NULL DEFAULT NULL,
  fk_autocamper_id INT NOT NULL,
  CONSTRAINT fk_autocamper
    FOREIGN KEY (fk_autocamper_id)
    REFERENCES autocamper (id)
);

CREATE TABLE rental (
  id INT PRIMARY KEY AUTO_INCREMENT,
  acc_price INT NULL DEFAULT NULL,
  start_date DATE NULL DEFAULT NULL,
  end_date DATE NULL DEFAULT NULL,
  lon_pickUp_loc FLOAT(10,6) NULL DEFAULT NULL,
  lat_pickUp_loc FLOAT(10,6) NULL DEFAULT NULL,
  lon_dropOff_loc FLOAT(10,6) NULL DEFAULT NULL,
  lat_dropOff_loc FLOAT(10,6) NULL DEFAULT NULL,
  fk_autocamper_id INT NOT NULL,
  fk_customer_id INT NULL DEFAULT NULL,
  fk_maintenance_id INT NOT NULL,

  CONSTRAINT fk_camper
    FOREIGN KEY (fk_autocamper_id)
    REFERENCES autocamper (id),
  CONSTRAINT fk_cus
    FOREIGN KEY (fk_customer_id)
    REFERENCES customer (id),
  CONSTRAINT fk_rental_maintenance1
    FOREIGN KEY (fk_maintenance_id)
    REFERENCES maintenance (id)
    );

    CREATE TABLE accessory_type(
    id INT	PRIMARY KEY	AUTO_INCREMENT,
	description VARCHAR(1000),
	price INT NOT NULL,
	name VARCHAR(45) NOT NULL
    );
    
CREATE TABLE accessory (
  id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  fk_accessory_type_id INT NOT NULL,
  fk_rental_id INT,
  CONSTRAINT fk_accessory_type
    FOREIGN KEY (fk_accessory_type_id)
    REFERENCES accessory_type (id),
  CONSTRAINT fk_accessory_rental
    FOREIGN KEY (fk_rental_id)
    REFERENCES rental (id)
    );
    
  CREATE TABLE variable_prices (
  id INT PRIMARY KEY AUTO_INCREMENT,
  excessKilometer_price VARCHAR(45) NULL,
  fuel_price INT NULL,
  dropOffKilometer_price INT NULL,
  pickUpKilometer_price INT NULL,
  cleaning_max_price INT NULL,
  cleaning_min_price INT NULL
  );

CREATE TABLE season (
  season_name VARCHAR(45) PRIMARY KEY NOT NULL,
  charge_percentage INT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL
);

CREATE TABLE bill (
  id INT PRIMARY KEY AUTO_INCREMENT,
  billing_date DATE NULL,
  cus_first_name VARCHAR(45) NULL,
  cus_last_name VARCHAR(45) NULL,
  postalcode INT NOT NULL,
  street_name VARCHAR(45) NOT NULL,
  street_nr VARCHAR(45) NOT NULL,
  apartment_floor VARCHAR(45) NOT NULL,
  accessory_cost SMALLINT NULL,
  rental_cost SMALLINT NULL
  );

create table users(
    username varchar(50) not null primary key,
    password varchar(50) not null,
    enabled TINYINT not null default 1
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users(username, password, enabled)
values('admin', 'pass', 1);

insert into users(username, password, enabled)
values('user', 'pass', 1);

INSERT INTO authorities (username, authority)
values('admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
values('user', 'ROLE_USER');
  
  

