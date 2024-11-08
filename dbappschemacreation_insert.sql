-- THIS IS SAMPLE ONLY (BEFORE REVISIONS) SINCE I USED THE MENU NLNG TO EDIT TABLES INSTEAD OF WRITING QUERIES


-- Table: employees
CREATE TABLE employees (
  employee_id INT PRIMARY KEY,
  last_name VARCHAR(50),
  first_name VARCHAR(50),
  employee_email VARCHAR(50),
  contact_number VARCHAR(50),
  job_title VARCHAR(50),
  hire_date DATE,
  employee_schedule VARCHAR(50),
  hourly_rate DECIMAL(10,2)
);

-- Table: branch
CREATE TABLE branch (
  branch_id INT PRIMARY KEY,
  branch_code VARCHAR(5),
  location VARCHAR(50),
  founding_year INT,
  contact_person_id INT,
  number_of_employees INT,
  performance_ratings DECIMAL(2,1),
  FOREIGN KEY (contact_person_id) REFERENCES employees(employee_id)
);

-- Table: products
CREATE TABLE products (
  product_id INT PRIMARY KEY,
  product_name VARCHAR(100),
  cost_price DECIMAL(10,2),
  selling_price DECIMAL(10,2),
  product_category VARCHAR(50)
);

-- Table: supplier
CREATE TABLE supplier (
  supplier_id INT PRIMARY KEY,
  supplier_name VARCHAR(50),
  contact_person VARCHAR(50),
  contact_number VARCHAR(50),
  supplier_email VARCHAR(50),
  contract_terms_start_date DATE,
  contract_terms_end_date DATE,
  product_id INT,
  performance_ratings DECIMAL (2,1),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Table: inventory
CREATE TABLE inventory (
  product_id INT PRIMARY KEY,
  quantity_in_stock INT,
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Table: sales
CREATE TABLE sales (
  product_id INT PRIMARY KEY,
  noted_by_employee_id INT,
  total_sales_made_daily DECIMAL(10,2),
  sale_date DATE,
  FOREIGN KEY (noted_by_employee_id) REFERENCES employees(employee_id),
  FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO branch (branch_id, branch_code, location, founding_year, contact_person_id, number_of_employees, performance_ratings)
VALUES (1, 'BR001', 'New York', 2000, 1, 50, 4.5);
