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
SELECT emp_name, salary
FROM employees;

-- 2. 查询部门 ID 为 1 的所有员工信息
SELECT *
FROM employees
WHERE dept_id = 1;

-- 3. 查询每个部门的员工数量
SELECT dept_id, COUNT(emp_id) as emp_count
FROM employees
GROUP BY dept_id;

-- 4. 查询工资最高的员工的姓名和工资
SELECT emp_name, salary
FROM employees
WHERE salary = (SELECT MAX(salary) FROM employees);

-- 5. 查询每个部门中工资最高的员工的姓名、部门名称和工资
SELECT d.dept_name, e.emp_name, e.salary
FROM employees e
         JOIN departments d ON e.dept_id = d.dept_id
         JOIN (
    SELECT dept_id, MAX(salary) as max_salary
    FROM employees
    GROUP BY dept_id
) sub ON e.dept_id = sub.dept_id AND e.salary = sub.max_salary;

-- 6. 查询在 2023 年入职的员工参与的所有项目名称
SELECT DISTINCT ep.project_name
FROM employees e
         JOIN employee_projects ep ON e.emp_id = ep.emp_id
WHERE EXTRACT(YEAR FROM e.hire_date) = 2023;

-- 7. 查询参与项目数量最多的员工的姓名和参与项目数量
WITH project_counts AS (
    SELECT emp_id, COUNT(assignment_id) as project_count
    FROM employee_projects
    GROUP BY emp_id
)
SELECT e.emp_name, pc.project_count
FROM employees e
         JOIN project_counts pc ON e.emp_id = pc.emp_id
WHERE pc.project_count = (SELECT MAX(project_count) FROM project_counts);

-- 8. 查询每个部门中，在 2023 年参与项目的员工的平均工资
SELECT d.dept_name, AVG(e.salary) as avg_salary
FROM employees e
         JOIN departments d ON e.dept_id = d.dept_id
         JOIN employee_projects ep ON e.emp_id = ep.emp_id
WHERE EXTRACT(YEAR FROM ep.start_date) = 2023
GROUP BY d.dept_name;

-- 9. 查询连续参与项目的员工（假设项目按时间顺序排列）
WITH consecutive_projects AS (
    SELECT emp_id,
           start_date,
           start_date - ROW_NUMBER() OVER (PARTITION BY emp_id ORDER BY start_date) as grp
    FROM employee_projects
),
     project_sequences AS (
         SELECT emp_id,
                grp,
                COUNT(*) as consecutive_project_count
         FROM consecutive_projects
         GROUP BY emp_id, grp
     )
SELECT DISTINCT emp_id
FROM project_sequences
WHERE consecutive_project_count > 1;

-- 10. 查询每个部门中，参与项目时间最长的员工的姓名和项目时长
WITH project_durations AS (
    SELECT ep.emp_id,
           e.dept_id,
           e.emp_name,
           SUM(EXTRACT(DAY FROM (ep.end_date - ep.start_date))) as total_duration
    FROM employee_projects ep
             JOIN employees e ON ep.emp_id = e.emp_id
    GROUP BY ep.emp_id, e.dept_id, e.emp_name
),
     ranked_durations AS (
         SELECT dept_id,
                emp_name,
                total_duration,
                RANK() OVER (PARTITION BY dept_id ORDER BY total_duration DESC) as rnk
         FROM project_durations
     )
SELECT dept_id, emp_name, total_duration
FROM ranked_durations
WHERE rnk = 1;