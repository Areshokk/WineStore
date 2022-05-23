CREATE SCHEMA 'hibernate';

CREATE TABLE `hibernate`.`orders` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `address` VARCHAR(255) NULL,
                                      `city` VARCHAR(255) NULL,
                                      `date` DATETIME NULL,
                                      `email` VARCHAR(255) NULL,
                                      `first_name` VARCHAR(255) NULL,
                                      `last_name` VARCHAR(255) NULL,
                                      `phone_number` VARCHAR(255) NULL,
                                      `post_index` VARCHAR(45) NOT NULL,
                                      `total_price` FLOAT NULL,
                                      `user_id` INT NULL,
                                      PRIMARY KEY (`id`));


CREATE TABLE `hibernate`.`orders_perfume_list` (
                                                   `order_id` INT NOT NULL,
                                                   `perfume_list_id` INT NOT NULL,
                                                   `perfume_list_order` INT NOT NULL,
                                                   PRIMARY KEY (`order_id`, `perfume_list_order`));


CREATE TABLE `hibernate`.`perfume` (
                                       `id` INT NOT NULL AUTO_INCREMENT,
                                       `country` VARCHAR(255) NULL,
                                       `description` VARCHAR(255) NULL,
                                       `filename` VARCHAR(255) NULL,

                                       `fragrance_base_notes` VARCHAR(255) NULL,
                                       `fragrance_middle_notes` VARCHAR(255) NULL,
                                       `fragrance_top_notes` VARCHAR(255) NULL,

                                       `perfume_gender` VARCHAR(255) NULL,
                                       `perfume_title` VARCHAR(255) NULL,
                                       `perfumer` VARCHAR(255) NULL,
                                       `price` INT NOT NULL,
                                       `type` VARCHAR(255) NULL,
                                       `volume` VARCHAR(255) NULL,
                                       `year` VARCHAR(255) NOT NULL,
                                       PRIMARY KEY (`id`));


CREATE TABLE `hibernate`.`user_role` (
                                         `user_id` INT NOT NULL,
                                         `roles` VARCHAR(255) NULL);

CREATE TABLE `hibernate`.`usr` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `activation_code` VARCHAR(255) NULL,
                                   `active` TINYINT NOT NULL,
                                   `email` VARCHAR(255) NULL,
                                   `password` VARCHAR(255) NOT NULL,
                                   `username` VARCHAR(255) NOT NULL,
                                   PRIMARY KEY (`id`));


CREATE TABLE `hibernate`.`usr_perfume_list` (
                                                `user_id` INT NOT NULL,
                                                `perfume_list_id` INT NOT NULL);




ALTER TABLE `hibernate`.`orders`
    ADD CONSTRAINT `FK7ncuqw9n77odylknbo8aikc9w`
    FOREIGN KEY (`user_id`)
    REFERENCES `hibernate`.`usr` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;


ALTER TABLE `hibernate`.`orders_perfume_list`
    ADD CONSTRAINT `FKi6hpa14qaenek8pc9pf3vmlei`
        FOREIGN KEY (`perfume_list_id`)
            REFERENCES `hibernate`.`perfume` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `hibernate`.`orders_perfume_list`
    ADD CONSTRAINT `FK8jft4d30d5dgvauht7ssndwau`
        FOREIGN KEY (`order_id`)
            REFERENCES `hibernate`.`orders` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `hibernate`.`user_role`
    ADD CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5`
        FOREIGN KEY (`user_id`)
            REFERENCES `hibernate_sequence`.`usr` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `hibernate_sequence`.`usr_perfume_list`
    ADD CONSTRAINT `FK1n7n8prjoexkp1twc9f6kgbtm`
        FOREIGN KEY (`perfume_list_id`)
            REFERENCES `hibernate_sequence`.`perfume` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `hibernate_sequence`.`usr_perfume_list`
    ADD CONSTRAINT `FKc5b4lo20noteewtlrq1kd8nhs`
        FOREIGN KEY (`user_id`)
            REFERENCES `hibernate_sequence`.`usr` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;






#----------------------------------------------



CREATE TABLE `wine`.`orders` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `address` VARCHAR(255) NULL,
                                  `city` VARCHAR(255) NULL,
                                  `date` DATETIME NULL,
                                  `email` VARCHAR(255) NULL,
                                  `first_name` VARCHAR(255) NULL,
                                  `last_name` VARCHAR(255) NULL,
                                  `phone_number` VARCHAR(255) NULL,
                                  `post_index` VARCHAR(45) NOT NULL,
                                  `total_price` FLOAT NULL,
                                  `user_id` INT NULL,
                                  PRIMARY KEY (`id`));


CREATE TABLE `wine`.`orders_wine_list` (
                                               `order_id` INT NOT NULL,
                                               `wine_list_id` INT NOT NULL,
                                               `wine_list_order` INT NOT NULL,
                                               PRIMARY KEY (`order_id`, `wine_list_order`));


CREATE TABLE `wine`.`wine` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `country` VARCHAR(255) NULL,
                                   `description` VARCHAR(255) NULL,
                                   `filename` VARCHAR(255) NULL,

                                   `decantation` VARCHAR(255) NULL, /*fragrance_base_notes*/
                                   `combination` VARCHAR(255) NULL, /*fragrance_middle_notes*/
                                   `grape` VARCHAR(255) NULL,       /*fragrance_top_notes*/

                                   `color` VARCHAR(255) NULL,
                                   `wine_title` VARCHAR(255) NULL,
                                   `brand` VARCHAR(255) NULL,
                                   `price` INT NOT NULL,
                                   `type` VARCHAR(255) NULL,
                                   `volume` VARCHAR(255) NULL,
                                   `year` VARCHAR(255) NOT NULL,
                                   PRIMARY KEY (`id`));


CREATE TABLE `wine`.`user_role` (
                                     `user_id` INT NOT NULL,
                                     `roles` VARCHAR(255) NULL);

CREATE TABLE `wine`.`usr` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `activation_code` VARCHAR(255) NULL,
                               `active` TINYINT NOT NULL,
                               `email` VARCHAR(255) NULL,
                               `password` VARCHAR(255) NOT NULL,
                               `username` VARCHAR(255) NOT NULL,
                               PRIMARY KEY (`id`));


CREATE TABLE `wine`.`usr_wine_list` (
                                            `user_id` INT NOT NULL,
                                            `wine_list_id` INT NOT NULL);




ALTER TABLE `wine`.`orders`
    ADD CONSTRAINT `FK7ncuqw9n77odylknbo8aikc9w`
        FOREIGN KEY (`user_id`)
            REFERENCES `wine`.`usr` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `wine`.`orders_wine_list`
    ADD CONSTRAINT `FKi6hpa14qaenek8pc9pf3vmlei`
        FOREIGN KEY (`wine_list_id`)
            REFERENCES `wine`.`wine` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `wine`.`orders_wine_list`
    ADD CONSTRAINT `FK8jft4d30d5dgvauht7ssndwau`
        FOREIGN KEY (`order_id`)
            REFERENCES `wine`.`orders` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `wine`.`user_role`
    ADD CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5`
        FOREIGN KEY (`user_id`)
            REFERENCES `wine`.`usr` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `wine`.`usr_wine_list`
    ADD CONSTRAINT `FK1n7n8prjoexkp1twc9f6kgbtm`
        FOREIGN KEY (`wine_list_id`)
            REFERENCES `wine`.`wine` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;


ALTER TABLE `wine`.`usr_wine_list`
    ADD CONSTRAINT `FKc5b4lo20noteewtlrq1kd8nhs`
        FOREIGN KEY (`user_id`)
            REFERENCES `wine`.`usr` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;