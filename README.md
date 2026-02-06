# Task Management System (Console Based) - Java JDBC Project

A console-based Task Management System built using **Core Java**, **JDBC**, and **MySQL**.  
This project supports user authentication, project creation, task management, and task status tracking.

---

## Features

- User Registration & Login
- Create and View Projects
- Create Tasks under Projects
- View Tasks of a Project
- Update Task Status (TODO / INPROGRESS / DONE)
- Delete Tasks
- MySQL Database Integration using JDBC
- Clean layered structure (Model → DAO → Service → UI)

---

## Tech Stack

- **Language:** Java  
- **Database:** MySQL  
- **Database Connectivity:** JDBC  
- **IDE:** IntelliJ IDEA  

---

## Project Structure

TaskManagerApp/
├── src/
│ ├── db/
│ ├── model/
│ ├── dao/
│ ├── service/
│ └── Main.java
├── schema.sql
├── db.properties
├── .gitignore
└── README.md

---

## Database Setup

### Step 1: Install MySQL
Make sure MySQL is installed and running.

### Step 2: Create Database and Tables
Run the schema file:

```bash
mysql -u root -p < schema.sql
```
This will create the database and required tables automatically.

Cofiguration Steps
###Step 1: Edit db.properties file
update your mysql username and password there.
