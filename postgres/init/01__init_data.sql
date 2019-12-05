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
),
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
    'CP470',
    'Android Programming',
    0.5,
    NULL,
    3.0,
    NULL,
    'How to write applications for the Android mobile devices using the Android Development Tools. Developing software with the Model-View-Controller paradigm. Knowledge of Java is required. The course project will be the development of an Android app.',
    'CP213, CP317.',
    ARRAY['CP213', 'CP317'],
    NULL,
    NULL,
    'CP400Q.',
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
    "The Honours BSc Computer Science program consists of a minimum of 20.0 credits. A maximum of 6.0 credits are allowed at the 100 level. The aim of the program is to develop a strong understanding of the basic principles of computer science, while providing the opportunity for specialization according to students' interests.",
    4,
    ARRAY[['CP104', 'CP164'], ['CP213', 'CP264'], ['CP312', 'CP386'], ['CP414', 'CP470']],
    ARRAY['2.0 elective credits if MA103 is taken in Winter', '2.5 elective credits', '1.5 elective credits', '4.5 elective credits'],
    3,
    ARRAY['Spring 2018', 'Winter 2019', 'Fall 2020'],
    ARRAY["Based on the results of the Calculus Preparation Evaluation, an entering student may be required to complete MA102 prior to completing MA103).", "The electives must include at least 3.5 senior CP credits among which at least 1.0 credit must be at the 400 level. The electives must also include at least 1.0 credit from a discipline outside of the Faculty of Science.", "Progression requirements will be based on grade point averages (GPAs) obtained from all courses taken and will be calculated on a cumulative basis. For progression and graduation, the requirement will be a cumulative GPA of 5.00 in Computer Science and a minimum overall GPA of 5.00."]
);