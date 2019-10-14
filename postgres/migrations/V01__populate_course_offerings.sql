CREATE TYPE weekDays AS ENUM ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY')
INSERT INTO pawk.course_offerings (
    course_code INT REFERENCES pawk.courses(code)
    crn INT UNIQUE PRIMARY KEY,
    section TEXT,
    credits FLOAT,
    startTime TEXT,
    endTime TEXT,
    classDays weekDays[]
    campus TEXT
    instructor TEXT
) VALUES
(
    'CP104'
    '2936',
    '1A',
    0.5,
    '7PM',
    '9PM',
    ARRAY['THURSDAY'],
    'Brantford',
    'Richard Norman Hildred',
),
(
    'CP104'
    '60',
    'A',
    0.5,
    '1:30PM',
    '2:20PM',
    ARRAY['MONDAY', 'WEDNESDAY', 'FRIDAY'],
    'Waterloo',
    'Habib-ur Rehman'
),
(
    'CP104'
    '28',
    'L1',
    0.5,
    '2:30PM',
    '4:50PM',
    ARRAY['MONDAY'],
    'Waterloo',
    'David B. Brown'
);