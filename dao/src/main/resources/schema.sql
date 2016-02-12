DROP TABLE IF EXISTS GOOD;

CREATE TABLE GOOD (
       GOOD_ID INT NOT NULL AUTO_INCREMENT
     , NAME VARCHAR(100) NOT NULL
     , PRICE DOUBLE NOT NULL
     , UNIQUE UQ_NAME (NAME)
     , PRIMARY KEY (GOOD_ID)
);
