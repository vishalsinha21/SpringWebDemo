## Maven Spring Web Starter Project

1. Jetty plugin as embedded server
2. In Memory Derby Database
3. Liquibase integration
4. Spring Rest services for backend
5. Backbone as frontend (CRUD example)
6. Mustache templates
7. Twitter Bootstrap for styling

### First time setup
- Clone project
- Navigate to project path in terminal and execute **mvn inmemdb:run** to start Derby database server
- Navigate to project path in terminal and execute **mvn liquibase:update** to run liquibase scripts to create DB and load data
- Build project and start jetty server by executing **mvn clean install && mvn jetty:run**
- Open application in browser using url **http://localhost:8080/**


### Employee Manager

#### REST services details
- Get all employees: GET http://localhost:8080/employee
- Get employee by Id: GET http://localhost:8080/employee/{employeeId}
- Create new employee (with employee data): POST http://localhost:8080/employee
- Delete employee: DELETE http://localhost:8080/employee/{employeeId}
- Update employee (with employee data): PUT http://localhost:8080/employee/{employeeId}


#### Main Page
![Main Page](/src/main/resources/screenshots/mainPage.png?raw=true)

#### Create Page
![Main Page](/src/main/resources/screenshots/createPage.png?raw=true)

#### Edit Page
![Main Page](/src/main/resources/screenshots/editPage.png?raw=true)
