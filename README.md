<img src="https://user-images.githubusercontent.com/15056496/71398406-efd9fa80-25e5-11ea-8beb-c9f2f8f62a0e.png" width="150" />

# Pawk
Pawk is a course and program exploration app for students at Wilfrid Laurier University. It was inspired by [CourseHawk](https://github.com/russellgoldman/CourseHawk) and built to address CourseHawk's architectural shortcomings.

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

### Database
Manages PostgreSQL setup, SQL migrations, and connection to GraphQL. GraphQL
type definitions, queries and mutations also defined here. Entrypoint is
a Node.js server.

### Docker
Manages services and containerizes the backend ecosystem to be accessible with
scale from frontend clients.

## Frontend
### Figma
Mockups designed in Figma and translated into a functioning mobile application.
https://www.figma.com/file/B03C0QA1loWb7f0XSgVc3g/Pawk-Version-2?node-id=403%3A2

### Android Frontend
Accesses the backend ecosystem to serve data to our frontend Android application.

## References
### Docker + Postgraphile Setup
https://github.com/alexisrolland/docker-postgresql-postgraphile/wiki
