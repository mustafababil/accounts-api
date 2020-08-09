## Demo Project

[![Build Status](https://dev.azure.com/mustafababil/mustafababil/_apis/build/status/blue-harvest-accounts-api?branchName=master)](https://dev.azure.com/mustafababil/mustafababil/_build/latest?definitionId=6&branchName=master)

#### Tech stack
- Java 11
- Spring Boot 2.3.2
- OpenAPI v3
- Swagger 2
- Lombok
- H2 Database
- Liquibase
- JUnit and Mockito
- Sonarlint

1. Generate API Controllers and Models
```shell script
./mvnw clean compile
```

2. Run Tests
```shell script
./mvnw clean test
```

3. Start the API
```shell script
./mvnw spring-boot:run
```

### Taken Decisions and Assumptions
- Haven't applied git branching strategy. Worked only on master.
- Provided customer Ids are always existing.
- Project is developed on monolith architecture as the current scope is limited and the future is blurry.
- Minimal dependency between business services, i.e. *AccountService* and *TransactionService*.
- Event based communication between services, i.e. when a new account with initial credit is created, regarding
transaction is created asynchronously by *TransactionService*

### Swagger Page

http://localhost:8080/swagger-ui.html

### Application Overview
N-tier architecture approach is applied. Three main layers namely Controller, Service and Repository matches the Web, Business and Data tiers respectively.

Data repository is also abstracted to the way that it could connect different data sources at the same time.
```
+---------------------+
|Web Tier:Controller  |
+----------+----------+
           |
           v
+----------+----------+
|Business Tier:Service|
+----------+----------+
           |
           v
+----------+----------+
|Data Tier:Repository |
+---------------------+
```

### API First Approach
As spending time about design of the API, establishing the contract first has its own benefits like reusability, easy to
maintain and extend in future.

If there were other clients for this API, it would enable working in parallel.
 
It also decreased development time thanks to code generation from the API definition. By this way.
controller interface and API models are created automatically. So, I could focused on implementation quickly.

OpenAPI definition is at `resources/accounts-api.yml`.

#### Tests
##### Integration tests
Controller and Repository layers have integration tests that uses the actual components the app
uses in production. They can be found under test packages `com.mybank.demo.controller` and `com.mybank.demo.dal.inmemory.repository`.

##### Unit tests
There are unit tests for some important Service layer components (haven't implemented unit tests
for mappers to save time). Their notion show the mocking and assertion approach.

### Data persistence
In memory H2 Database is utilized to persist business data. Its credentials are plain text in project properties
but we could save them in safe encrypted vault in later stages. For now it's okay.

### Database migrations
For keeping versions and historical track of changes in database, there are Liquibase migration scripts in place.
They can be found under `resources/db`.

### CI / CD Pipeline     
Code version history is hosted on **[Github](https://github.com/mustafababil/accounts-api)**.

**Azure DevOps Pipelines** script is implemented to
- fetch the code
- build it with Maven (also runs tests)
- extract built Jar file to artifacts repository
- publish test and code coverage reports to Azure DevOps Pipeline page

CI pipeline script can be found at `azure-pipelines.yml`.

CD pipeline is also on Azure DevOps, and it is automatically deploys when there is a new artifact sourced from
master branch.
