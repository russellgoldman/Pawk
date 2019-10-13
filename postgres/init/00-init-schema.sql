\connect postgraphile;

CREATE TABLE courses (
    code TEXT UNIQUE NOT NULL PRIMARY KEY,
    name TEXT NOT NULL,
    credits DECIMAL NOT NULL,
    term TEXT[],
    lecture_hours DECIMAL,
    lab_hours DECIMAL,
    description TEXT,
    prerequisite_description TEXT,
    prerequisite_courses TEXT[],
    corequisite_description TEXT,
    corequisite_courses TEXT[],
    exclusions_description TEXT,
    exclusions_courses TEXT[]
);

COMMENT ON TABLE courses IS
'All course data from the Academic Calendar';

CREATE TABLE programs (
    name TEXT UNIQUE NOT NULL PRIMARY KEY,
    description TEXT,
    years INTEGER,
    required_courses TEXT[][],
    elective_credits TEXT[],
    coop_count INTEGER,
    coop_terms TEXT[],
    progression_requirements TEXT[]
);

COMMENT ON TABLE programs IS
'All program data from the Academic Calendar';