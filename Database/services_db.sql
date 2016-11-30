-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2016 at 01:26 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `services_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `complex_service`
--

CREATE TABLE `complex_service` (
  `csid` varchar(255) NOT NULL,
  `cs_name` varchar(255) DEFAULT NULL,
  `cs_provider` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `complex_service`
--

INSERT INTO `complex_service` (`csid`, `cs_name`, `cs_provider`) VALUES
('env', 'env', 'neonsofts'),
('rainy', 'rainy', 'raniny');

-- --------------------------------------------------------

--
-- Table structure for table `service_relation`
--

CREATE TABLE `service_relation` (
  `csid` varchar(255) NOT NULL,
  `ssid` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_relation`
--

INSERT INTO `service_relation` (`csid`, `ssid`) VALUES
('rainy', 'kelu'),
('rainy', 'light'),
('env', 'light'),
('env', 'temp');

-- --------------------------------------------------------

--
-- Table structure for table `simple_service`
--

CREATE TABLE `simple_service` (
  `ssid` varchar(255) NOT NULL,
  `ss_name` varchar(255) DEFAULT NULL,
  `ss_value` varchar(255) DEFAULT NULL,
  `ss_protocol` varchar(255) DEFAULT NULL,
  `ss_url` varchar(255) DEFAULT NULL,
  `ss_TTL` int(11) DEFAULT NULL,
  `ss_timestamp` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `simple_service`
--

INSERT INTO `simple_service` (`ssid`, `ss_name`, `ss_value`, `ss_protocol`, `ss_url`, `ss_TTL`, `ss_timestamp`) VALUES
('kelu', 'kelu', '13101206', 'http', 'http://koushikjay66@gmail.com', 500, '0000-00-00 00:00:00'),
('light', 'light', '10', 'https', 'https://google.com', 500, '0000-00-00 00:00:00'),
('temp', 'temp', '24', 'http', 'http://google.com', 500, '0000-00-00 00:00:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complex_service`
--
ALTER TABLE `complex_service`
  ADD PRIMARY KEY (`csid`),
  ADD UNIQUE KEY `csid_UNIQUE` (`csid`);

--
-- Indexes for table `service_relation`
--
ALTER TABLE `service_relation`
  ADD KEY `csid_idx` (`csid`),
  ADD KEY `ssid_idx` (`ssid`);

--
-- Indexes for table `simple_service`
--
ALTER TABLE `simple_service`
  ADD PRIMARY KEY (`ssid`),
  ADD UNIQUE KEY `ssid_UNIQUE` (`ssid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `service_relation`
--
ALTER TABLE `service_relation`
  ADD CONSTRAINT `csid` FOREIGN KEY (`csid`) REFERENCES `complex_service` (`csid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ssid` FOREIGN KEY (`ssid`) REFERENCES `simple_service` (`ssid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
