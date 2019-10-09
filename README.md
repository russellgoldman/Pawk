# CP470-Group-Project

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

### Android Frontend
Accesses the backend ecosystem to serve data to our frontend Android application.