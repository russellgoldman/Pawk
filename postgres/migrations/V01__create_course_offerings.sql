CREATE TABLE pawk.course_offerings (
    course_code TEXT REFERENCES pawk.courses(code) ON DELETE RESTRICT,
    crn INT UNIQUE PRIMARY KEY NOT NULL,
    section TEXT NOT NULL,
    credits FLOAT,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    week_days TEXT[] NOT NULL,
    campus TEXT NOT NULL,
    instructor TEXT
);

COMMENT ON TABLE pawk.course_offerings IS
'Course offerings for a Laurier course';