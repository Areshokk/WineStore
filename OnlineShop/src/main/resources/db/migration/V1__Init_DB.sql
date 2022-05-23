CREATE SCHEMA 'winedb';

CREATE TABLE `winedb`.`orders` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `address` VARCHAR(255) NULL,
    `city` VARCHAR(255) NULL,
    `date` DATE NULL,
    `email` VARCHAR(255) NULL,
    `first_name` VARCHAR(255) NULL,
    `last_name` VARCHAR(255) NULL,
    `phone_number` VARCHAR(255) NULL,
    `post_index` VARCHAR(45) NOT NULL,
    `total_price` FLOAT NULL,
    `user_id` INT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE `winedb`.`orders_wine_list` (
                                             `order_id` INT NOT NULL,
                                             `wine_list_id` INT NOT NULL,
                                             `wine_list_order` INT NOT NULL,
                                             PRIMARY KEY (`order_id`, `wine_list_order`));


CREATE TABLE `winedb`.`wine` (
                                 `id` INT NOT NULL AUTO_INCREMENT,
                                 `country` VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    `filename` VARCHAR(255) NULL,

    `decantation` VARCHAR(255) NULL,
    `combination` VARCHAR(255) NULL,
    `grape` VARCHAR(255) NULL,

    `color` VARCHAR(255) NULL,
    `wine_title` VARCHAR(255) NULL,
    `brand` VARCHAR(255) NULL,
    `price` INT NOT NULL,
    `type` VARCHAR(255) NULL,
    `volume` VARCHAR(255) NULL,
    `year` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE `winedb`.`user_role` (
                                      `user_id` INT NOT NULL,
                                      `roles` VARCHAR(255) NULL);

CREATE TABLE `winedb`.`usr` (
                                `id` INT NOT NULL AUTO_INCREMENT,
                                `activation_code` VARCHAR(255) NULL,
    `active` TINYINT NOT NULL,
    `email` VARCHAR(255) NULL,
    `password` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));


CREATE TABLE `winedb`.`usr_wine_list` (
                                          `user_id` INT NOT NULL,
                                          `wine_list_id` INT NOT NULL);




ALTER TABLE `winedb`.`orders`
    ADD CONSTRAINT `FK7ncuqw9n77odylknbo8aikc9w`
    FOREIGN KEY (`user_id`)
    REFERENCES `winedb`.`usr` (`id`)
    ON DELETE NO ACTION
       ON UPDATE NO ACTION;


ALTER TABLE `winedb`.`orders_wine_list`
    ADD CONSTRAINT `FKi6hpa14qaenek8pc9pf3vmlei`
    FOREIGN KEY (`wine_list_id`)
    REFERENCES `winedb`.`wine` (`id`)
    ON DELETE NO ACTION
       ON UPDATE NO ACTION;

ALTER TABLE `winedb`.`orders_wine_list`
    ADD CONSTRAINT `FK8jft4d30d5dgvauht7ssndwau`
    FOREIGN KEY (`order_id`)
    REFERENCES `winedb`.`orders` (`id`)
    ON DELETE NO ACTION
       ON UPDATE NO ACTION;


ALTER TABLE `winedb`.`user_role`
    ADD CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5`
    FOREIGN KEY (`user_id`)
    REFERENCES `winedb`.`usr` (`id`)
    ON DELETE NO ACTION
       ON UPDATE NO ACTION;


ALTER TABLE `winedb`.`usr_wine_list`
    ADD CONSTRAINT `FK1n7n8prjoexkp1twc9f6kgbtm`
    FOREIGN KEY (`wine_list_id`)
    REFERENCES `winedb`.`wine` (`id`)
    ON DELETE NO ACTION
       ON UPDATE NO ACTION;


ALTER TABLE `winedb`.`usr_wine_list`
    ADD CONSTRAINT `FKc5b4lo20noteewtlrq1kd8nhs`
    FOREIGN KEY (`user_id`)
    REFERENCES `winedb`.`usr` (`id`)
    ON DELETE NO ACTION
       ON UPDATE NO ACTION;