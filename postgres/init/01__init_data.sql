\connect postgraphile;

INSERT INTO pawk.courses (
    code,
    name,
    credits,
    term,
    lecture_hours,
    lab_hours,
    description,
    prerequisite_description,
    prerequisite_courses,
    corequisite_description,
    corequisite_courses,
    exclusions_description,
    exclusions_courses
) VALUES
(
    'CP320/PC320',
    'Physical Computing: Digital Interaction with the Analog World',
    0.5,
    '{"Fall"}',
    3.0,
    1.0,
    'Design and construction of computational systems that interact with the physical world for applications such as home or experiment automation. Basics of electrical circuits, reading from analog and digital sensors, controlling analog and digital actuators, single board computers such as Arduino or Raspberry PI, analog components including diodes, transistors and operational amplifiers.(Cross-listed as PC320.)',
    'CP164 (or CP114), CP220/PC220 (or CP120/PC120).',
    ARRAY['CP164', 'CP220/PC220'],
    NULL,
    NULL,
    'CP300/PC300.',
    NULL
),
(
    'CP104',
    'Introduction to Programming',
    0.5,
    '{"Fall", "Winter", "Spring"}',
    3.0,
    2.5,
    'An introductory course designed to familiarize the student with modern software development techniques. Emphasis is on problem-solving and structured program design methodologies. Programming projects are implemented in a widely used high-level language',
    NULL,
    NULL,
    NULL,
    NULL,
    NULL,
    NULL
);

INSERT INTO pawk.programs (
    name,
    description,
    years,
    required_courses,
    elective_credits,
    coop_count,
    coop_terms,
    progression_requirements
) VALUES
(
    'Honours BSc Computer Science',
    'Some stuff',
    4,
    ARRAY[['CP104', 'CP164'], ['CP213', 'CP264'], ['CP312', 'CP386'], ['CP414', NULL]],
    ARRAY['2.0 elective credits if MA103 is taken in Winter', '2.5 elective credits', '1.5 elective credits', '4.5 elective credits'],
    3,
    ARRAY['Spring 2018', 'Winter 2019', 'Fall 2020'],
    ARRAY['Some stuff', 'Some more stuff']
);