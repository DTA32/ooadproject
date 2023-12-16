USE InternetCLafes;

INSERT INTO users (UserName, Password, Age, Role) VALUES
    ('admin', 'admin', 30, 'admin'),
    ('customer', 'customer', 15, 'customer'),
    ('operator', 'operator', 22, 'operator'),
    ('technician', 'technician', 20, 'technician');

INSERT INTO pcs (pc_condition) VALUES
    ('Usable'),
    ('Maintenance'),
    ('Broken');

INSERT INTO TransactionHeaders (staff_id, staff_name, transaction_date) VALUES
    (3, 'operator', '2020-01-01'),
    (4, 'technician', '2020-01-02');

INSERT INTO TransactionDetails (transaction_id, pc_id, customer_name, booked_time) VALUES
    (1, 1, 'customer', '2020-01-01 10:00:00'),
    (1, 2, 'customer', '2020-01-01 11:00:00'),
    (2, 1, 'customer', '2020-01-02 10:00:00'),
    (2, 2, 'customer', '2020-01-02 11:00:00');

INSERT INTO Reports (user_role, pc_id, report_note, report_date) VALUES
    (0, 1, 'Report 1', '2020-01-01'),
    (1, 1, 'Report 2', '2020-01-02'),
    (2, 2, 'Report 3', '2020-01-03'),
    (3, 2, 'Report 4', '2020-01-04');

INSERT INTO PCBooks (pc_id, user_id, booked_date) VALUES
    (1, 2, '2020-01-01'),
    (2, 2, '2020-01-01'),
    (1, 2, '2020-01-02'),
    (2, 2, '2020-01-02');

INSERT INTO jobs (user_id, pc_id, job_status) VALUES
    (4, 2, 'Uncomplete'),
    (4, 3, 'Complete');