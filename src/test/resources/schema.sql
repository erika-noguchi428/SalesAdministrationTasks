CREATE TABLE IF NOT EXISTS tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(255),
    status VARCHAR(255),
    salesman_name VARCHAR(255),
    clerk_name VARCHAR(255),
    created_at TIMESTAMP,
    deadline DATE,
    comment VARCHAR(255),
    is_deleted BOOLEAN
);

CREATE TABLE IF NOT EXISTS task_details (
    task_detail_id INT PRIMARY KEY,
    task_id INT,
    operation VARCHAR(255),
    company_name VARCHAR(255),
    requirements VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS a_request (
    a_request_id INT PRIMARY KEY AUTO_INCREMENT,
    item VARCHAR(255),
    request_status VARCHAR(255),
    task_detail_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS b_request (
    b_request_id INT PRIMARY KEY AUTO_INCREMENT,
    item VARCHAR(255),
    request_status VARCHAR(255),
    task_detail_id VARCHAR(255)
);

