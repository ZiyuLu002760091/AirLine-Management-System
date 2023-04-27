/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : DBRoyalAirline

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 27/04/2023 12:37:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `uuid` varchar(255) NOT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `flightid` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `server` varchar(255) DEFAULT NULL,
  `book_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  UNIQUE KEY `uq_flight_id` (`flightid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for flightsinfo
-- ----------------------------
DROP TABLE IF EXISTS `flightsinfo`;
CREATE TABLE `flightsinfo` (
  `uuid` varchar(255) NOT NULL,
  `flight_number` varchar(10) NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `estimated_departure_datetime` datetime NOT NULL,
  `estimated_arrival_datetime` datetime NOT NULL,
  `origin` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  `status` enum('planning','cancelled','delay','in_advance_on_time') DEFAULT NULL,
  `actual_departure_datetime` datetime DEFAULT NULL,
  `actual_arrival_datetime` datetime DEFAULT NULL,
  `price` double NOT NULL,
  `member_discount` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uuid` char(36) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `dob` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('male','female','other') NOT NULL,
  `status` enum('active','inactive','deleted') NOT NULL,
  `role` enum('customer','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phoneNo` varchar(20) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
