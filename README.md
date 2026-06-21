# **Definition**

- This is a basic Task Flow project, to create API's to create, edit, update, print tasks
- In this project we are working with PostgreSQL database

# **Run the code**

```shell
./mvnw spring-boot:run
```

# **The code flow**

- Controller -> Service -> Repository

- Controller: Handle HTTP Requests and HTTP Responses
- Services: Hanle everything releated to logic

# **Dockerization**

- First you should install Docker Desktop

```shell
# run this command to create the postgreSQL image and contianer:

docker run --name taskflow-postgres \
  -e POSTGRES_DB=taskflow_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=yourpassword \
  -v pgdata:/var/lib/postgresql/data \
  -p 5432:5432 \
  -d postgres

# if the image didn't run and the container wasn't created use this command:

docker start taskflow-postgres

# create a databse with this database name using PgAdmin
```

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