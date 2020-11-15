-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema delivery_service
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema delivery_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `delivery_service` DEFAULT CHARACTER SET utf8 ;
USE `delivery_service` ;

-- -----------------------------------------------------
-- Table `delivery_service`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_service`.`roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_service`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `role_id` INT NOT NULL DEFAULT 1,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role`
    FOREIGN KEY (`role_id`)
    REFERENCES `delivery_service`.`roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
KEY_BLOCK_SIZE = 8;


-- -----------------------------------------------------
-- Table `delivery_service`.`cars`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_service`.`cars` (
  `car_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `manufacture_year` INT NOT NULL,
  `carrying` DOUBLE NOT NULL,
  `luggage_volume` DOUBLE NOT NULL,
  PRIMARY KEY (`car_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `delivery_service`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`routes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_service`.`routes` (
  `route_id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  `car_id` INT NULL,
  PRIMARY KEY (`route_id`),
  INDEX `car_id_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `car_id`
    FOREIGN KEY (`car_id`)
    REFERENCES `delivery_service`.`cars` (`car_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_service`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL DEFAULT 'не подтвержден',
  `sender_id` INT NOT NULL,
  `executor_id` INT NULL DEFAULT NULL,
  `date_in` DATE NOT NULL,
  `town` VARCHAR(100) NOT NULL,
  `route_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `sender_id_idx` (`sender_id` ASC) VISIBLE,
  INDEX `executor_id_idx` (`executor_id` ASC) VISIBLE,
  INDEX `rout_id_idx` (`route_id` ASC) VISIBLE,
  CONSTRAINT `sender_id`
    FOREIGN KEY (`sender_id`)
    REFERENCES `delivery_service`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `executor_id`
    FOREIGN KEY (`executor_id`)
    REFERENCES `delivery_service`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `rout_id`
    FOREIGN KEY (`route_id`)
    REFERENCES `delivery_service`.`routes` (`route_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery_service`.`goods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery_service`.`goods` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `order_id` INT NOT NULL,
  `count` INT NOT NULL DEFAULT 1,
  `weight` DOUBLE NOT NULL,
  `volume` DOUBLE NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `order_id_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `delivery_service`.`orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
