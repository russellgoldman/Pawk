CREATE TABLE pawk.course_offerings (
    course_code TEXT REFERENCES pawk.courses(code) ON DELETE RESTRICT,
    crn INT UNIQUE PRIMARY KEY,
    section TEXT,
    credits FLOAT,
    startTime TEXT,
    endTime TEXT,
    weekDays TEXT[],
    campus TEXT,
    instructor TEXT
);

COMMENT ON TABLE pawk.course_offerings IS
'Course offerings for a Laurier course';