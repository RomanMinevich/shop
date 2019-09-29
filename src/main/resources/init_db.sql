CREATE SCHEMA `Shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `Shop`.`Items` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `Price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`Id`));

INSERT INTO `Shop`.`Items` (Name, Price) VALUES ('Salt', 300);
INSERT INTO `Shop`.`Items` (Name, Price) VALUES ('Spice', 250);
INSERT INTO `Shop`.`Items` (Name, Price) VALUES ('Mushrooms', 400);
INSERT INTO `Shop`.`Items` (Name, Price) VALUES ('Herbs', 200);


