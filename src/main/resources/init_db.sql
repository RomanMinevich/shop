CREATE SCHEMA `Shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `Shop`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `Shop`.`items` (name, price) VALUES ('Salt', 300);
INSERT INTO `Shop`.`items` (name, price) VALUES ('Spice', 250);
INSERT INTO `Shop`.`items` (name, price) VALUES ('Mushrooms', 400);
INSERT INTO `Shop`.`items` (name, price) VALUES ('Herbs', 200);

CREATE TABLE `Shop`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` ENUM('USER', 'ADMIN') NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `Shop`.`roles` (`name`) VALUES ('USER');
INSERT INTO `Shop`.`roles` (`name`) VALUES ('ADMIN');

CREATE TABLE `Shop`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(45) NULL,
  `address` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `token_UNIQUE` (`token` ASC) VISIBLE);

INSERT INTO `Shop`.`users` (
`token`, `phone_number`, `password`, `first_name`, `last_name`, `email`) VALUES (
'1', '1', '1', 'Ivan', 'Ivanov', '1@gmail.com');
INSERT INTO `Shop`.`users` (
`token`, `phone_number`, `password`, `first_name`, `last_name`, `address`) VALUES (
'2', '2', '2', 'Peter', 'Petrov', 'Kiev');

CREATE TABLE `Shop`.`buckets` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id_bucket`
    FOREIGN KEY (`id`)
    REFERENCES `Shop`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `Shop`.`buckets` (`id`) VALUES ('1');
INSERT INTO `Shop`.`buckets` (`id`) VALUES ('2');

CREATE TABLE `Shop`.`buckets_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bucket_id` INT NULL,
  `item_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `bucket_id`
    FOREIGN KEY (`bucket_id`)
    REFERENCES `Shop`.`buckets` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `item_id_bucket`
    FOREIGN KEY (`item_id`)
    REFERENCES `Shop`.`items` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `Shop`.`buckets_items` (`bucket_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `Shop`.`buckets_items` (`bucket_id`, `item_id`) VALUES ('1', '2');
INSERT INTO `Shop`.`buckets_items` (`bucket_id`, `item_id`) VALUES ('1', '3');
INSERT INTO `Shop`.`buckets_items` (`bucket_id`, `item_id`) VALUES ('2', '4');
INSERT INTO `Shop`.`buckets_items` (`bucket_id`, `item_id`) VALUES ('2', '4');

CREATE TABLE `Shop`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id_order`
    FOREIGN KEY (`user_id`)
    REFERENCES `Shop`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `Shop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `Shop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `Shop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `Shop`.`orders` (`user_id`) VALUES ('2');

CREATE TABLE `Shop`.`orders_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NULL,
  `item_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `Shop`.`orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `item_id_order`
    FOREIGN KEY (`item_id`)
    REFERENCES `Shop`.`Items` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '3');
INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '4');
INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '4');
INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '4');
INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('4', '2');
INSERT INTO `Shop`.`orders_items` (`order_id`, `item_id`) VALUES ('4', '1');

CREATE TABLE `Shop`.`users_roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `role_id` INT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id_role`
    FOREIGN KEY (`user_id`)
    REFERENCES `Shop`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `Shop`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `Shop`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `Shop`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '1');
INSERT INTO `Shop`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '2');

ALTER TABLE `Shop`.`users`
ADD COLUMN `salt` BLOB NOT NULL AFTER `token`,
DROP INDEX `token_UNIQUE` ;