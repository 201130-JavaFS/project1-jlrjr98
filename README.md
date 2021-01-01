# Employee Reimbursement System (ERS) Project

## Project Description
An application to serve as an Expense Reimbursement System (ERS). Employees are able to log in, submit requests for reimbursements, and view past requests. Financial Managers have the additional capability of reviewing other employees' reimbursement requests, approving or denying them.

## Technologies Used
* Java 8
* Spring Tool Suite 4 version 4.8.0
* Maven version 2.22.1
* Tomcat 9
* Log4J2 version 2.13.3
* JUnit version 5.4.2
* Postman version 7.36.0
* JavaScript
* HTML
* CSS
* Visual Studio Code version 1.52.1
* PostgreSQL 10
* DBeaver version 7.2.5
* Git

## Features
**Ready Features**
* Login
* View all your submitted reimbursement request tickets
* Submit a new reimbursement request
* Financial Managers can view all the reimbursement requests of other employees and can filter view by status (pending, approved, declined)
* Financial Managers can approve or decline requests submitted by other employees
* Signout

**To-do List of Improvements**
* Add the ability for users to upload receipts with reimbursement requests
* Add the ability for Financial Managers to view employees and their relevant information even if they have not submitted a reimbursement request ticket
* Add the ability for Financial Managers to filter tickets by more options than just status.
* Add the ability for users to filter their own tickets by various options

## Getting Started
* git clone https://github.com/201130-JavaFS/project1-jlrjr98.git
* git pull
* Open the project in Spring Tool Suite 4 or equivalent. Perform a Maven Update Project to acquire all the necessary dependencies
* Create the following tables in a PostgreSQL database
![](./supplemental-material/physical.jpg) using the SQL code in [a relative link](SQL.txt)
* Users and additional employee roles will need to be added manually using the SQL template code in [a relative link](SQL_Template.txt)
* Set up environment variables postgresPassword and postgresUserName for your Postgres password and username respectively

## Usage
* In IDE, right click project > RUN AS > RUN AS SERVER
* Open rev.html, found in directory revature-front-end, in a browser