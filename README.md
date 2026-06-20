# **Definition**

- This is a basic Task Flow project, to create API's to create, edit, update, print tasks
- In this project we are working with in-memory database not a relational database like PostgreSQL per example

- This is in-memory database so when we stop the server all the data will be deleted

# **Run the code**

```shell
./mvnw spring-boot:run
```

# **The code flow**

- Controller -> Service -> Repository

- Controller: Handle HTTP Requests and HTTP Responses
- Services: Hanle everything releated to logic

# **Test endpoints**

- Using postman application we can test all endpoints

```shell
# create task

method: POST

URL: http://localhost:8080/api/tasks

body:
{
    "title": "coding",
    "description": "finishing my 42 Beirut project"
}
```

```shell
# get all tasks

method: GET

URL: http://localhost:8080/api/tasks
```

```shell
# get a specific task

method: GET

# like this we get the task with the id = 1
URL: http://localhost:8080/api/tasks/1
```

```shell
# update task

method: PUT

URL: http://localhost:8080/api/tasks/1

body:

{
    "title": "spring boot",
    "description": "finishing my 42 Beirut project"
}
```

```shell
# delete a task

method: DELETE

URL: http://localhost:8080/api/tasks/1
```