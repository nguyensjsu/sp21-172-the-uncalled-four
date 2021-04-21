-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `uncalledfour` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `uncalledfour` ;

DROP TABLE IF EXISTS `uncalledfour`.`customer` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(80) NULL,
  `last_name` VARCHAR(80) NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`register` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`register` (
  `register_id` INT NOT NULL AUTO_INCREMENT ,
  `employee_id` INT NOT NULL,
  `timestamp` TIMESTAMP,
  PRIMARY KEY (`register_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`order` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` TIMESTAMP,
  `order` TEXT NULL,
  PRIMARY KEY (`order_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`receipt` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`receipt` (
  `receipt_id` INT NOT NULL AUTO_INCREMENT,
  `timestamp` TIMESTAMP,
  `total_price` FLOAT,
  `payment_type` ENUM ('CARD','REWARD') NOT NULL,
  PRIMARY KEY (`receipt_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`card` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`card` (
  `card_number` INT NOT NULL,
  `first_name` VARCHAR(80) NULL,
  `last_name` VARCHAR(80) NULL,
  `exp_date` DATE,
  `ccv` INT NOT NULL,
  PRIMARY KEY (`card_number`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`reward` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`reward` (
  `reward_code` VARCHAR(12) NOT NULL,
  `reward_amount` FLOAT,
  `exp_date` DATE,
  PRIMARY KEY (`reward_code`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`order_line` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`order_line` (
  `order_id` INT NOT NULL,
  `order_ins_id` INT NOT NULL,
  `order_count` INT NOT NULL,
  `unit_price` DATE
)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`order_instruction` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`order_instruction` (
  `order_ins_id` INT NOT NULL AUTO_INCREMENT,
  `additional_instruction` TEXT,
   PRIMARY KEY (`order_ins_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`drink` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`drink` (
  `drink_id` INT NOT NULL AUTO_INCREMENT,
  `drink_name` VARCHAR(54) NOT NULL,
  `price` FLOAT, 
  `drink_type` ENUM ('Americano','BrewedCoffee', 'Capuccino', 'EspressoShots', 'FlatWhite', 'Latte', 'Macchiato', 'Mocha', 'CloverBrewedCoffee') NOT NULL,
   PRIMARY KEY (`drink_id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`recipe` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`recipe` (
  `recipe_id` INT NOT NULL,
  `drink_id` INT NOT NULL,
  `recipe` TEXT NOT NULL
)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`ingredients` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`ingredients` (
  `recipe_id` INT NOT NULL,
  `ingredient_id` INT NOT NULL,
  `ingredients` TEXT NOT NULL
)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `uncalledfour`.`menu` ;
CREATE TABLE IF NOT EXISTS `uncalledfour`.`menu` (
  `menu_id` INT NOT NULL,
  `drink_id` INT NOT NULL,
  `seasonal` BOOLEAN
)
ENGINE = InnoDB;




