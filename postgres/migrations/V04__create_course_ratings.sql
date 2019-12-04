CREATE TABLE pawk.course_ratings (
    course_code TEXT REFERENCES pawk.courses(code) ON DELETE RESTRICT,
    rating_id INT UNIQUE PRIMARY KEY NOT NULL, 
    rating INT NOT NULL
);

COMMENT ON TABLE pawk.course_ratings IS
'Course ratings for Laurier Courses';