FROM node:alpine
COPY package.json .
RUN npm install
COPY src ./src

RUN node ./src/courses/iterator.js