CREATE DATABASE IF NOT EXISTS InternetCLafes;

USE InternetCLafes;

CREATE TABLE users (
  UserID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  UserName varchar(255) NOT NULL,
  Password varchar(255) NOT NULL,
  Age INT NOT NULL,
  Role varchar(20) NOT NULL
);

CREATE TABLE pcs (
  pc_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  pc_condition varchar(255) NOT NULL
);

CREATE TABLE TransactionHeaders (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    staff_id INT NOT NULL,
    staff_name VARCHAR(255) NOT NULL,
    transaction_date DATE NOT NULL,
    FOREIGN KEY (staff_id) REFERENCES users(UserID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE TransactionDetails (
    transaction_id INT NOT NULL,
    pc_id INT NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    booked_time DATETIME NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES TransactionHeaders(transaction_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (pc_id) REFERENCES pcs(pc_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Reports (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    user_role INT NOT NULL,
    pc_id INT NOT NULL,
    report_note VARCHAR(255) NOT NULL,
    report_date DATE NOT NULL,
    FOREIGN KEY (pc_id) REFERENCES pcs(pc_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE PCBooks (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    pc_id INT NOT NULL,
    user_id INT NOT NULL,
    booked_date DATE NOT NULL,
    FOREIGN KEY (pc_id) REFERENCES pcs(pc_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(UserID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE jobs (
  job_id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id int NOT NULL,
  pc_id int NOT NULL,
  job_status varchar(50) DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users(UserID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (pc_id) REFERENCES pcs(pc_id) ON UPDATE CASCADE ON DELETE CASCADE
);