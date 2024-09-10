## Components:

## Model
### Employee
Represents an employee entity with immutable attributes. It uses a UUID for unique identification, and email verification is handled at the service layer.

### Jurisdiction
Defines jurisdictional details such as tax rates and annual leave policies. Although not currently utilized, it is referenced via countryId or jurisdictionId in the Employee model. This allows for future processing or validation in workflows like payroll management.

## Service
### EmployeeService
Manages all employee-related operations, including addition, updating, retrieval, and listing of employee records. The service uses an in-memory data store and incorporates time zone handling to present timestamps in the user's local time zone.
#### Functionality
1. Add Employee
    * **Description**: Adds a new employee to the system.
    * **Exceptions**: Throws IllegalArgumentException if the email format is invalid.
    * **Returns**: The newly added Employee object.
2. Update Employee
    * **Description**: Updates the details of an existing employee.
    * **Exceptions**: Throws IllegalArgumentException if employee is not found and if the email format is invalid.
    * **Returns**: The updated Employee object.
3. Get Employee by ID
    * **Description**: Retrieves an employee's information by their unique ID and converts the createdAt and modifiedAt timestamps to the specified local time zone.
    * **Exceptions**: Throws IllegalArgumentException if the employee is not found.
    * **Returns**: The Employee object with timestamps adjusted to the specified local time zone.
4. Get All Employees
    * **Description**: Retrieves all employees and converts their createdAt and modifiedAt timestamps to the specified local time zone.
    * **Returns**: A list of all Employee objects with timestamps adjusted to the specified local time zone.
5. Get All Employees as String
    * **Description**: Retrieves all employees and returns their details as a string, with timestamps formatted according to the specified local time zone.
    * **Returns**: A string representation of all employees with formatted timestamps.

#### Design Choices
1. In-Memory Data Store: Utilizes a HashMap for storing employee records to provide quick access and manipulation of data.
2. Time Zone Handling: Timestamps (createdAt and modifiedAt) are stored in UTC to maintain consistency. When displaying timestamps to users, they are converted to the user's local time zone using the ZoneId specified by the timezone parameter. We can further enhance the display of the date time by returning the formatted createdAt and modifiedAt dates based on the user's local time zone. Created a custom 'formatDateTime' method in `TimeZoneUtil` for the same which could be called at controller level and return the createdAt and modifiedAt as string to frontend. Did not call this formatter in the `toString` method of Employee as the toString method is typically used for representing the object’s state, not for formatting. Putting complex logic here can make the method less straightforward.
3. Utility Classes: Utility classes (EmailUtil and TimeZoneUtil) are used to encapsulate common functionalities such as email validation and time zone conversion. This promotes code reuse and separation of concerns.
4. Builder Pattern: The Employee class uses the Builder pattern to provide a flexible and readable way to create immutable Employee objects. This approach enhances code clarity and maintainability.

## Dependencies
This project uses teh following maven dependencies
1. Lombok - For reducing boilerplate code with annotations
2. spring-boot-starter - Core Spring Boot dependencies.

## Running the Application
Ensure you have Java 17 or later installed.

To run the application:
1. Build project
2. Run the `EmployeeDriver` main method.
   