\connect postgraphile;

CREATE SCHEMA pawk;
CREATE TABLE pawk.courses (
    code TEXT UNIQUE NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    credits FLOAT NOT NULL,
    term TEXT[],
    lecture_hours FLOAT,
    lab_hours FLOAT,
    description TEXT,
    prerequisite_description TEXT,
    prerequisite_courses TEXT[],
    corequisite_description TEXT,
    corequisite_courses TEXT[],
    exclusions_description TEXT,
    exclusions_courses TEXT[]
);

COMMENT ON TABLE pawk.courses IS
'All course data from the Academic Calendar';

CREATE TABLE pawk.programs (
    name TEXT UNIQUE NOT NULL PRIMARY KEY,
    description TEXT,
    years INT,
    required_courses TEXT[][],
    elective_credits TEXT[],
    coop_count INTEGER,
    coop_terms TEXT[],
    progression_requirements TEXT[]
);

COMMENT ON TABLE pawk.programs IS
'All program data from the Academic Calendar';