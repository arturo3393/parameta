CREATE DATABASE employee;

USE employee;

CREATE TABLE employee (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    dateOfBirth DATE,
    hiringDate DATE,
    jobTitle VARCHAR(50),
    salary DOUBLE
);

