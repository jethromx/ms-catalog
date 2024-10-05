-- -------------------------------------------------------------



DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `id` varchar(10) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `model` varchar(10) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

