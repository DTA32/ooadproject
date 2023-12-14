CREATE DATABASE IF NOT EXISTS InternetCLafes;

USE InternetCLafes;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_age INT NOT NULL,
    user_role INT NOT NULL
);

CREATE TABLE PCs (
    pc_id INT PRIMARY KEY AUTO_INCREMENT,
    pc_condition VARCHAR(255) NOT NULL
);

CREATE TABLE TransactionHeaders (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    staff_id INT NOT NULL,
    staff_name VARCHAR(255) NOT NULL,
    transaction_date DATE NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES Users(user_id)
);

CREATE TABLE TransactionDetails (
    transaction_id INT NOT NULL,
    pc_id INT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    booked_time DATETIME NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES TransactionHeaders(transaction_id),
    FOREIGN KEY (pc_id) REFERENCES PCs(pc_id)
);

CREATE TABLE Reports (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    user_role INT NOT NULL,
    pc_id INT NOT NULL,
    report_note VARCHAR(255) NOT NULL,
    report_date DATE NOT NULL,
    FOREIGN KEY (pc_id) REFERENCES PCs(pc_id)
);

CREATE TABLE PCBooks (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    pc_id INT NOT NULL,
    user_id INT NOT NULL,
    booked_date DATE NOT NULL,
    FOREIGN KEY (pc_id) REFERENCES PCs(pc_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Jobs (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    pc_id INT NOT NULL,
    job_status INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (pc_id) REFERENCES PCs(pc_id)
);