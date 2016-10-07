-- MySQL Script generated by MySQL Workbench
-- 10/07/16 15:40:06
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema services_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema services_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `services_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `services_db` ;

-- -----------------------------------------------------
-- Table `services_db`.`Simple Service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `services_db`.`Simple Service` (
  `ssid` VARCHAR(255) NOT NULL COMMENT '',
  `ss_name` VARCHAR(255) NULL COMMENT '',
  `ss_value` VARCHAR(255) NULL COMMENT '',
  `ss_protocol` VARCHAR(255) NULL COMMENT '',
  `ss_url` VARCHAR(255) NULL COMMENT '',
  `ss_TTL` INT NULL COMMENT '',
  `ss_timestamp` TIMESTAMP NULL COMMENT '',
  PRIMARY KEY (`ssid`)  COMMENT '',
  UNIQUE INDEX `ssid_UNIQUE` (`ssid` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `services_db`.`Complex Service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `services_db`.`Complex Service` (
  `csid` VARCHAR(255) NOT NULL COMMENT '',
  `cs_name` VARCHAR(255) NULL COMMENT '',
  `cs_provider` VARCHAR(255) NULL COMMENT '',
  PRIMARY KEY (`csid`)  COMMENT '',
  UNIQUE INDEX `csid_UNIQUE` (`csid` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `services_db`.`Service Relation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `services_db`.`Service Relation` (
  `csid` VARCHAR(255) NOT NULL COMMENT '',
  `ssid` VARCHAR(255) NOT NULL COMMENT '',
  INDEX `csid_idx` (`csid` ASC)  COMMENT '',
  INDEX `ssid_idx` (`ssid` ASC)  COMMENT '',
  CONSTRAINT `csid`
    FOREIGN KEY (`csid`)
    REFERENCES `services_db`.`Complex Service` (`csid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ssid`
    FOREIGN KEY (`ssid`)
    REFERENCES `services_db`.`Simple Service` (`ssid`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
