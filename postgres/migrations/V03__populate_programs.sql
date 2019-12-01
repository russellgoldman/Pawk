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
    'Computer Science',
    'From applied cryptography to mobile computing, Computer Science will help you develop strong algorithm analysis and design skills in procedural, object-oriented and low-level languages. Our Bachelor of Science (BSc) in Computer Science program provides the hands-on experience that is key to understanding computing fundamentals with an optional introduction to functional, parallel and logical paradigms.',
    4,
    '{{CP104,CP164},{CP213,CP264},{CP312,CP386},{CP414,NULL}}',
    '{"2.0 elective credits if MA103 is taken in Winter","2.5 elective credits","1.5 elective credits","4.5 elective credits"}',
    3,
    '{"Spring 2018","Winter 2019","Fall 2020"}',
    '{"Some stuff","Some more stuff"}'
),
(
    'Sociology',
    'As social beings, we shape and are shaped by society. With an eye to social justice and equity, Laurier’s Sociology program provides you with the analytic skills to understand how society works, and how we can go about changing it for the better.',
    4,
    '{{SY101,SY102},{SY207, SY210},{SY382,SY389}}',
    null,
    3,
    null,
    null
),
(
    'Psychology',
    'Psychology involves the scientific study of behaviour and experience, including thinking, feeling and action. Psychology teaches us how we learn languages, how we function in community and how to promote healthy development across the lifespan, among other topics.',
    4,
    '{{PS101,PS102},{PS295,PS296},{PS360,PS385},{PS400,PS487}}',
    null,
    3,
    null,
    null
),
(
    'English',
    'Explore the written word as medium for creativity, persuasion, and engagement with life’s most persistent questions in the Laurier Waterloo English program.',
    4,
    '{{EN107,EN108},{EN211,EN219},{EN312,EN389},{EN421,EN487}}',
    null,
    3,
    null,
    null
);