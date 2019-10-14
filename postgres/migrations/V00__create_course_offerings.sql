CREATE TABLE pawk.course_offerings
(
    course_code INT REFERENCES courses(code),
    crn INT UNIQUE PRIMARY KEY,
    section TEXT,
    credits FLOAT,
    startTime TIME,
    endTime TIME,
    classDays TEXT[]
);