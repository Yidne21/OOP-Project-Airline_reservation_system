-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ars
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ars
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ars` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ars` ;

-- -----------------------------------------------------
-- Table `ars`.`admintbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ars`.`admintbl` (
  `fname` VARCHAR(50) NULL DEFAULT NULL,
  `lname` VARCHAR(50) NULL DEFAULT NULL,
  `password` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(50) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `sex` VARCHAR(8) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `phone_no` INT NOT NULL,
  PRIMARY KEY (`phone_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ars`.`journeytbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ars`.`journeytbl` (
  `journeyId` INT NOT NULL,
  `from` VARCHAR(50) NOT NULL,
  `destination` VARCHAR(50) NOT NULL,
  `rout` VARCHAR(50) NOT NULL,
  `cost` FLOAT NOT NULL,
  PRIMARY KEY (`journeyId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ars`.`scheduletbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ars`.`scheduletbl` (
  `schedulId` INT NOT NULL,
  `journeyId` INT NOT NULL,
  `DDate` DATE NOT NULL,
  `departure` TIME NOT NULL,
  `arrival` TIME NOT NULL,
  `no_of_seat` INT NOT NULL,
  PRIMARY KEY (`schedulId`),
  INDEX `scheduletbl_ibfk_1` (`journeyId` ASC) VISIBLE,
  CONSTRAINT `scheduletbl_ibfk_1`
    FOREIGN KEY (`journeyId`)
    REFERENCES `ars`.`journeytbl` (`journeyId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ars`.`passengertbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ars`.`passengertbl` (
  `fname` VARCHAR(50) NOT NULL,
  `lname` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `residence` VARCHAR(50) NOT NULL,
  `nationality` VARCHAR(50) NOT NULL,
  `sex` VARCHAR(8) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `phone_no` INT NOT NULL,
  PRIMARY KEY (`phone_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ars`.`tickettbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ars`.`tickettbl` (
  `ticketID` INT NOT NULL,
  `class` VARCHAR(30) NOT NULL,
  `status` VARCHAR(30) NOT NULL,
  `seat_no` INT NOT NULL,
  `schedulId` INT NOT NULL,
  `journeyId` INT NOT NULL,
  PRIMARY KEY (`ticketID`),
  INDEX `tickettbl_ibfk_1` (`journeyId` ASC) VISIBLE,
  INDEX `tickettbl_ibfk_2` (`schedulId` ASC) VISIBLE,
  CONSTRAINT `tickettbl_ibfk_1`
    FOREIGN KEY (`journeyId`)
    REFERENCES `ars`.`journeytbl` (`journeyId`),
  CONSTRAINT `tickettbl_ibfk_2`
    FOREIGN KEY (`schedulId`)
    REFERENCES `ars`.`scheduletbl` (`schedulId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ars`.`bookedtbl`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ars`.`bookedtbl` (
  `phone_no` INT NOT NULL,
  `ticketID` INT NOT NULL,
  `schedulId` INT NOT NULL,
  `journeyId` INT NOT NULL,
  PRIMARY KEY (`phone_no`),
  INDEX `reservationtbl_ibfk_1` (`journeyId` ASC) VISIBLE,
  INDEX `reservationtbl_ibfk_2` (`schedulId` ASC) VISIBLE,
  INDEX `reservationtbl_ibfk_4` (`ticketID` ASC) VISIBLE,
  CONSTRAINT `bookedtbl_ibfk_1`
    FOREIGN KEY (`journeyId`)
    REFERENCES `ars`.`journeytbl` (`journeyId`),
  CONSTRAINT `bookedtbl_ibfk_2`
    FOREIGN KEY (`schedulId`)
    REFERENCES `ars`.`scheduletbl` (`schedulId`),
  CONSTRAINT `bookedtbl_ibfk_3`
    FOREIGN KEY (`phone_no`)
    REFERENCES `ars`.`passengertbl` (`phone_no`),
  CONSTRAINT `bookedtbl_ibfk_4`
    FOREIGN KEY (`ticketID`)
    REFERENCES `ars`.`tickettbl` (`ticketID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
