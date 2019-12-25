<img src="https://user-images.githubusercontent.com/15056496/71398406-efd9fa80-25e5-11ea-8beb-c9f2f8f62a0e.png" width="150" />

# Pawk
Pawk is a course and program exploration app for students at Wilfrid Laurier University. It was inspired by [CourseHawk](https://github.com/russellgoldman/CourseHawk) and built to address it's architectural shortcomings.

## Team Members (CP470 Final Project)
- Russell Goldman
- Ralph Suyat
- Michelle Wong
- Brian Hane
- Pirajeev Prabaharan

## Backend
### Webscraper
Node.js server uses webscraping libraries to access course/program data from
the Laurier Academic Calendar. The server mutates the PostgreSQL database with
the collected data using GraphQL.

### PostgreSQL Database
SQL init schema and migrations used to define logic within the database.

### GraphQL API (using Postgraphile)
Postgraphile creates a GraphQL API from our PostgreSQL database with the ability to create custom queries and mutations. If our GraphQL API relied upon many database management systems however (e.g. MongoDB, PostgreSQL), then Postgraphile wouldn't be an appropriate fit. For our usecase, it works quite well.

### Docker
Manages services and containerizes the backend ecosystem (GraphQL API + PostgreSQL database) to be accessible with
scale from many frontend clients.

## Frontend
### Figma
Mockups designed in Figma and translated into a functioning mobile application.
https://www.figma.com/file/B03C0QA1loWb7f0XSgVc3g/Pawk-Version-2?node-id=403%3A2

### Android Frontend
Accesses the backend ecosystem to serve data to our frontend Android application.

## References
### Docker + Postgraphile Setup
https://github.com/alexisrolland/docker-postgresql-postgraphile/wiki
