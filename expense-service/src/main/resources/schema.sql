CREATE TABLE EXPENSE (
                         id INTEGER PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(255) NOT NULL,
                         EXPENSE_VALUE FLOAT,
                         discount FLOAT,
                         ipi FLOAT,
                         icms FLOAT,
                         installments INT
);