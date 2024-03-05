CREATE DATABASE IF NOT EXISTS CarRental;


USE CarRental;


CREATE TABLE Customer (
    ID INT NOT NULL AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    Zipcode INT(10) NOT NULL,
    City VARCHAR(50) NOT NULL,
    Mobile_phone INT(12) NOT NULL,
    Email VARCHAR(50) NOT NULL,
    License_Number INT(50) NOT NULL,
    License_Issue_Date DATE NOT NULL,
    CONSTRAINT Customer_pk PRIMARY KEY (ID)
);

CREATE TABLE Contract (
    ID INT(10) NOT NULL AUTO_INCREMENT,
    CustomerID INT(10) NOT NULL,
    CarID INT(10) NOT NULL,
    FromDateAndTime VARCHAR(50) NOT NULL,
    ToDateAndTime VARCHAR(50) NOT NULL,
    MaxKm INT(10) NOT NULL,
    CONSTRAINT Contract_pk PRIMARY KEY (ID)
);

CREATE TABLE Car (
  ID int(10) NOT NULL AUTO_INCREMENT, 
  Car_CategoryID int(10) NOT NULL, 
  Brand varchar(50) NOT NULL, 
  Model varchar(50) NOT NULL, 
  TypeOfFuel varchar(20) NOT NULL, 
  License_Plate varchar(50) NOT NULL, 
  Registration_YYYY_MM int(10) NOT NULL, 
  CarMileage int(10), 
  CONSTRAINT Car_pk PRIMARY KEY (ID));

CREATE TABLE Car_Category (
  ID   int(1) NOT NULL AUTO_INCREMENT, 
  Type varchar(50) NOT NULL, 
  CONSTRAINT Car_Category_pk PRIMARY KEY (ID));
ALTER TABLE Car ADD CONSTRAINT FKCar FOREIGN KEY (Car_CategoryID) REFERENCES Car_Category (ID);
ALTER TABLE Contract ADD CONSTRAINT FKContract1 FOREIGN KEY (CustomerID) REFERENCES Customer (ID); -- forvirret her
ALTER TABLE Contract ADD CONSTRAINT FKContract2 FOREIGN KEY (CarID) REFERENCES Car (ID); -- og her


  INSERT INTO Customer (Name, Address, Zipcode, City, Mobile_phone, Email, License_Number, License_Issue_Date) VALUES 
  ('Johnny Johnnson', 'Strandvej 7', 12345, 'Kbh', 123456789, 'johnnyboy@yahoo.com', 1234567890, '2023-01-01'),
  ('Bobby Bobsen', 'Bobvej 133', 67890, 'Næstved', 555666777,'bobby@example.com', 123456789, '2001-01-01'),
  ('Michael bruun', 'Hastrupvej 99', 98765, 'Hastrup', 555444333, 'michael.bruun@example.com', 654987321, '2019-08-10');
  
  INSERT INTO Car_Category (Type) VALUES
  ('Family'), ('Sport'), ('Luxury');
  
  INSERT INTO CAR (Car_CategoryID, Brand, Model, TypeOfFuel, License_Plate, Registration_YYYY_MM, CarMileage) VALUES
  (1, 'Toyota', 'Corolla', 'Gasoline', 'ABC123', 2022, 5000),
  (2, 'Honda', 'Civic', 'Gasoline', 'XYZ456', 2021, 8000),
  (1, 'Ford', 'Focus', 'Diesel', 'DEF789', 2020, 10000);
  
  INSERT INTO CONTRACT (CustomerID, CarID, FromDateAndTime, ToDateAndTime, MaxKm) VALUES
  (1, 1, '2024-03-01 09:00:00', '2024-03-10 17:00:00', 1000),
  (2, 2, '2024-03-05 10:00:00', '2024-03-15 18:00:00', 1200),
  (3, 3, '2024-03-10 08:00:00', '2024-03-20 16:00:00', 1500);

SELECT 
    Contract.ID AS ID,
    Contract.CustomerID AS CustomerID,
    Customer.Name AS Name,
    Customer.Address AS Address,
    Customer.Zipcode AS Zipcode,
    Customer.City AS City,
    Customer.License_Number AS License_Number,
    Car.Brand AS Brand,
    Car.Model AS Model,
    Car_Category.Type AS CarCategory,
    Car.Registration_YYYY_MM AS Car_Registration_Year,
    Car.License_Plate AS License_Plate,
    Car.CarMileage AS CarMileage,
    Contract.MaxKm AS MaxKm,
    Contract.FromDateAndTime AS FromDateAndTime,
    Contract.ToDateAndTime AS ToDateAndTime
FROM 
    Contract
JOIN 
    Customer ON Contract.CustomerID = Customer.ID
JOIN 
    Car ON Contract.CarID = Car.ID
JOIN 
    Car_Category ON Car.Car_CategoryID = Car_Category.ID;


  
  
  
  
  