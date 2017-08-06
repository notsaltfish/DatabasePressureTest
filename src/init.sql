Oracle
-- Create table
create table TEST_PRESSURE
(
  id         NUMBER not null,
  name       VARCHAR2(100),
  age        NUMBER,
  sex        VARCHAR2(10),
  addr       VARCHAR2(400),
  phone      VARCHAR2(50),
  country    VARCHAR2(100),
  department VARCHAR2(100)
)

MySQL


CREATE TABLE `test_pressure` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `addr` varchar(400) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
