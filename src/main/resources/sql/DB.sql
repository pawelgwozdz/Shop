CREATE DATABASE  IF NOT EXISTS `STORE_DB`;
USE STORE_DB;
create user 'store_owner'@'localhost' identified by 'store_owner';

CREATE TABLE `PRODUCTS` (
	`ID` bigint not null auto_increment,
    `NAME` varchar(100) not null,
    `DESCRIPTION` varchar(3000),
    `AMOUNT` int not null,
    `PRICE` decimal(20, 2) not null,
    primary key (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `CUSTOMERS` (
	`ID` bigint not null auto_increment,
    `COUNTRY` varchar(2) not null,
    `CITY` varchar(50) not null,
    `ADDRESS` varchar(200) not null,
    `HOUSE_NUMBER` varchar(10) not null,
    `APARTMENT_NUMBER` varchar(10),
    `PHONE_NUMBER` int not null,
    primary key (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `ORDERS` (
	`ID` bigint not null auto_increment,
    `CUSTOMER_ID` bigint not null,
    `ORDER_DATE` date not null,
    `PRICE`  decimal(20, 2) not null,
    `STATUS` varchar(20) not null,
    primary key (ID),
    foreign key (CUSTOMER_ID) References CUSTOMERS(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `ORDERED_PRODUCTS` (
	`ID` bigint not null auto_increment,
    `PRODUCT_ID` bigint not null,
    `ORDER_ID` bigint not null,
    `AMOUNT` int not null,
    primary key (ID),
    foreign key (PRODUCT_ID) References PRODUCTS(ID),
    foreign key (ORDER_ID) References ORDERS(ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into products values(1, 'HEADPHONES', 'Bluetooth wireless headphones', 10, 50.54);
insert into products values(2, 'PLAYSTATION 5', 'New generation gaming console', 5, 800.23);
insert into products values(3, 'SAMSUNG TV', '4K resolution 120HZ 55 inches TV', 0, 500);
insert into products values(4, 'WOODEN TABLE', 'Big table from oak wood', 3, 1010.10);
insert into customers values(1, 'PL', 'Ledziny', 'Ledzinska', '22c', null, 605777555);