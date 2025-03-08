-- 创建部门表
CREATE TABLE departments
(
    dept_id   INT PRIMARY KEY,
    dept_name VARCHAR(50)
);

-- 创建员工表
CREATE TABLE employees
(
    emp_id    INT PRIMARY KEY,
    emp_name  VARCHAR(50),
    salary    DECIMAL(10, 2),
    dept_id   INT,
    hire_date DATE,
    FOREIGN KEY (dept_id) REFERENCES departments (dept_id)
);

-- 创建员工项目表
CREATE TABLE employee_projects
(
    assignment_id INT PRIMARY KEY,
    emp_id        INT,
    project_name  VARCHAR(50),
    start_date    DATE,
    end_date      DATE,
    FOREIGN KEY (emp_id) REFERENCES employees (emp_id)
);