
# Parameta - Spring Boot

This API is made to get Employees from a Restful service and save them into MySQL database through a SOAP web service with the layers of Controller, Service and Domain.


## API Reference

#### Get all students

```http
  GET /api/v1/employee/
```

Employee entity

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `String` | **Required**. Employee's id |
| `name`      | `String` | **Required**. Employee's name |
| `lastName`      | `String` | **Required**. Employee's last name |
| `dateOfBirth`      | `LocalDate` | **Required**. Employee's date of birth |
| `hiringAge`      | `LocalDate` | **Required**. Employee's date of contract |
| `Salary`      | `Double` | **Required**. Employee's salary |








## Demo

![Alt Text](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExemV3MG16dXhrZjhwZDd1bW05dWs0ZnE1dGgxMzhnaHg4NjhsaWdiNSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/RQ6fwjiditBGIhN3Gq/giphy.gif)


## Features

- Costum API responses
- Costum exception handler
- Unit testing
- SQL Script with MySQL data base creation and its table



## Documentation

* Swagger: click on this [link](https://app.swaggerhub.com/apis/ARTURO3393_1/Test/1.0.0) that directs you to swagger documentation. Here you have a preview:

![Alt Text](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExcnIyYzBrYnMyOTVqYTZ6cnFxazFpZnE0MTlmMnJ2YmM3dTVxNmxrMiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/hGWJicK8XPtJqPuBq2/giphy.gif)


* JavaDoc: After running the JavaDoc gradle task go to **build/docs/javadoc/index.html** to find the respective documentation,


## Feedback

If you have any feedback, please reach out to us at arturo3393@gmail.com

