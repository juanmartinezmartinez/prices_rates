DROP TABLE IF EXISTS BRANDS;

CREATE TABLE BRANDS(
    ID INT PRIMARY KEY,
    NAME VARCHAR(255)
);

INSERT INTO BRANDS (ID, NAME) VALUES (1, 'ZARA');

DROP TABLE IF EXISTS PRICES;
CREATE TABLE PRICES(
    ID INT PRIMARY KEY,
    BRAND_ID INT,
    START_DATE TIMESTAMP,
    END_DATE TIMESTAMP,
    PRICE_LIST INT,
    PRODUCT_ID INT,
    PRIORITY INT,
    PRICE NUMBER,
    CURRENCY VARCHAR(3)
);

ALTER TABLE PRICES ADD FOREIGN KEY (BRAND_ID) REFERENCES BRANDS (ID);

INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY) VALUES (1, 1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY) VALUES (2, 1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY) VALUES (3, 1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY) VALUES (4, 1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 4, 35455, 1, 38.95, 'EUR');
