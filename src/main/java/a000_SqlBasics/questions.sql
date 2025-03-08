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
-- 1. 查询所有员工的姓名和工资
-- 2. 查询部门 ID 为 1 的所有员工信息
-- 3. 查询每个部门的员工数量
-- 4. 查询工资最高的员工的姓名和工资
-- 5. 查询每个部门中工资最高的员工的姓名、部门名称和工资
-- 6. 查询在 2023 年入职的员工参与的所有项目名称
-- 7. 查询参与项目数量最多的员工的姓名和参与项目数量
-- 8. 查询每个部门中，在 2023 年参与项目的员工的平均工资
-- 9. 查询连续参与项目的员工（假设项目按时间顺序排列）
-- 10. 查询每个部门中，参与项目时间最长的员工的姓名和项目时长