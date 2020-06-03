use autocamper_db;


Insert into accessory_type (description, price, id , name) Values
('very good product. Alot of great functionalities', '1000', DEFAULT, 'Childseat'),
('not as good. It has alot of great uses for the camping lifestyle', '200', DEFAULT, 'Bikerack'),
('bad product. It is very cheap', '100', DEFAULT, 'Outertent'),
('medium quality. Great color and taste', '3000', DEFAULT, 'Micrcowave');



INSERT INTO accessory (id, fk_accessory_type_id, fk_rental_id)
VALUES (default, 1, NULL), (default, 2, NULL), (default, 3, NULL), (default, 4, NULL);


INSERT INTO `autocamper_db`.`autocamper_type`
(`brand`,
`model`,
`price`,
`production_year`,
`weight`,
`fuel_capacity`,
`horse_power`,
`max_speed`,
`standing_height`,
`area_sqm`,
`height`,
`length`,
`width`,
`description`)
VALUES
("Farrari","Moccasin","1332","2010","4287","49","26","104","3","12","4","14","4","Dinah my dear! Let this be a book of rules for shutting people up like telescopes: this time it all seemed quite dull and stupid for life to go among mad people,' Alice remarked. 'Oh, you foolish."),
("Skiles IV","PaleGoldenRod","878","1983","2126","30","50","108","5","8","7","13","4","NOT a serpent!' said Alice angrily. 'It wasn't very civil of you to death.""' 'You are old,' said the Cat. '--so long as it was as much as she could not think of any good reason, and as for the White."),
("Jacobson II","AntiqueWhite","4527","2018","1140","50","38","168","5","7","4","11","4","For instance, suppose it doesn't matter much,' thought Alice, 'to speak to this last word with such sudden violence that Alice could hardly hear the very tones of the fact. 'I keep them to be a."),
("Volkswagen","Gainsboro","2757","2020","4447","53","27","136","3","9","7","13","4","I had it written down: but I can't be civil, you'd better finish the story for yourself.' 'No, please go on!' Alice said very humbly	 'I won't indeed!' said the King, 'unless it was a different."),
("Runte III","LightSalmon","2497","1971","3552","50","34","182","6","10","6","10","4","She said the Duchess, who seemed to be sure! However, everything is to-day! And yesterday things went on to himself as he spoke, 'we were trying--' 'I see!' said the March Hare said to itself in a."),
("Toyota","Yellow","4546","1990","4857","65","42","157","3","8","5","14","4","Who would not give all else for two reasons. First, because I'm on the back. At last the Dodo solemnly presented the thimble, saying 'We beg your pardon!' said the King: 'leave out that one of the."),
("modelVerla Toy","Aquamarine","4171","2009","3497","40","40","121","3","8","6","15","4","Northumbria, declared for him: and even Stigand, the patriotic archbishop of Canterbury, found it made no mark	 but he now hastily began again, using the ink, that was said, and went on in these."),
("Zusuki","MidnightBlue","1594","2006","4009","34","52","100","4","9","4","13","4","Caterpillar's making such a curious croquet-ground in her hands, and began:-- 'You are old,' said the Duchess	 'and that's why. Pig!' She said the Cat	 and this was of very little use without my.");


INSERT INTO autocamper_db.autocamper
(id, mileage, current_status, picture, fk_brand, fk_model) VALUES
(DEFAULT, '1000', 2, NULL, 'Zusuki', 'MidnightBlue'),
(DEFAULT, '2000', 1, NULL, 'Toyota', 'Yellow'),
(DEFAULT, '1034', 3, NULL, 'Volkswagen', 'Gainsboro'),
(DEFAULT, '3000', 2, NULL, 'Farrari', 'Moccasin');


INSERT INTO `autocamper_db`.`customer`
(`id`,
`last_name`,
`first_name`,
`email`,
`phone_nr`,
`cpr_nr`,
`postalcode`,
`street_name`,
`street_nr`,
`apartment_floor`,
`card_nr`,
`cvv`)
VALUES 
(1,"Altenwerth","Madisyn","shanny.maggio@example.com","+72(2)4333140061","0","47709","Jude Viaduct","1869","","4556757599654136","7"),
(2,"Donnelly","Otto","xrutherford@example.net","+15(4)5149393254","500","59206","Juanita Corners","7033","7","4929443955697897","7"),
(3,"Upton","Selina","jonatan67@example.org","064-643-5722x57720","5396","49336","Friedrich Loaf","67024","2","4929790115840367","0"),
(4,"Harber","Hubert","lisa.bahringer@example.org","(415)244-1785x099","306872291","8986","Justina Garden","6016","5","5409385643527559","8");

INSERT INTO `autocamper_db`.`maintenance`
(`id`,
`fuel_gauge`,
`mileage`,
`cleaning_price`,
`maintenance_notes`,
`frame`,
`wheels`,
`lights`,
`oil`,
`battery`,
`interior`,
`coolingflued`,
`brakes`,
`suspention`,
`maintenance_date`,
`fk_autocamper_id`)
VALUES
(DEFAULT, 50, 378, 200, "no notes", 1, 1, 1, 1, 1, 1, 1, 1, 1, "2020-12-08", 1);


INSERT INTO `autocamper_db`.`rental`
(`id`,
`start_date`,
`end_date`,
`lon_pickUp_loc`,
`lat_pickUp_loc`,
`lon_dropOff_loc`,
`lat_dropOff_loc`,
`fk_autocamper_id`,
`fk_customer_id`,
`fk_maintenance_id`)
VALUES
(DEFAULT, "2019-12-08", "2020-12-08", 55.676097, 12.568337, 55.676097, 12.568337, 3, 1, NULL);

INSERT INTO `autocamper_db`.`built_in_feature` (`id`, `name`, `description`) VALUES ('1', 'Køkken', 'Den er fin');
INSERT INTO `autocamper_db`.`built_in_feature` (`id`, `name`, `description`) VALUES ('2', 'Handicap Lift', 'Nice');
INSERT INTO `autocamper_db`.`built_in_feature` (`id`, `name`, `description`) VALUES ('3', 'Bad', 'Godt med et bad');
INSERT INTO `autocamper_db`.`built_in_feature` (`id`, `name`, `description`) VALUES ('4', 'Internet', 'Free Wifi');
INSERT INTO `autocamper_db`.`built_in_feature` (`id`, `name`, `description`) VALUES ('5', '3 senge', 'Godnat');
INSERT INTO `autocamper_db`.`built_in_feature` (`id`, `name`, `description`) VALUES ('6','2 senge', 'Sov godt');

INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Zusuki', 'MidnightBlue', '1');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Zusuki', 'MidnightBlue', '2');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Zusuki', 'MidnightBlue', '3');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Toyota', 'Yellow', '1');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Toyota', 'Yellow', '3');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Toyota', 'Yellow', '4');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Volkswagen', 'Gainsboro', '4');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Volkswagen', 'Gainsboro', '2');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Volkswagen', 'Gainsboro', '6');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Farrari', 'Moccasin', '2');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Farrari', 'Moccasin', '6');
INSERT INTO `autocamper_db`.`type_features` (`type_brand`, `type_model`, `feature_id`) VALUES ('Farrari', 'Moccasin', '1');


insert into users(username, password, enabled)
values('admin', 'pass', 1);

insert into users(username, password, enabled)
values('user', 'pass', 1);
insert into users(username, password, enabled)
values('sales', 'pass', 1);

INSERT INTO authorities (username, authority)
values('admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
values('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
values('sales', 'ROLE_SALES');