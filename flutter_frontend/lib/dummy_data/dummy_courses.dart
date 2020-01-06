// temporary dummy data, real data fetched using GraphQL
class DummyCourse {
  String code;
  String name;
  double rating;
  String description;

  DummyCourse({ this.code, this.name, this.rating, this.description });
}

class DummyCourses {
  final List<DummyCourse> courses = [
    DummyCourse(
      code: 'BU111',
      name: 'Understanding the Business Environment',
      rating: 4.7,
      description: 'This course highlights challenges in the external business environment with a focus on their impact on business decisions. Topics include competitive analysis, and an in-depth examination of political, economic, social, and technological factors. Students will apply their knowledge using cases, and by identifying and researching the feasibility of a new venture/business opportunity. The course includes a required weekly lab in which research, writing, teamwork, and individual and group presentation skills are also developed.'
    ),
    DummyCourse(
      code: 'BU121',
      name: 'Functional Areas of the Organization',
      rating: 4.4,
      description: 'This course provides an overview to the functional areas of a business; specifically marketing, finance, operations, and human resources. The areas will be examined using an integrative model and by focusing on current issues such as sustainability. Students will apply their knowledge using cases, and by developing a business plan for a new venture. The course includes a required weekly lab in which teamwork, business writing, critical thinking, negotiating, and individual and group presentation skills are also developed.'
    ),
    DummyCourse(
      code: 'BU127',
      name: 'Introduction to Financial Accounting',
      rating: 3.8,
      description: 'A study of basic accounting principles with emphasis on the recording, reporting and interpretation of financial data.'
    ),
    DummyCourse(
      code: 'CP104',
      name: 'Introduction to Programming',
      rating: 5.0,
      description: 'An introductory course designed to familiarize the student with modern software development techniques. Emphasis is on problem-solving and structured program design methodologies. Programming projects are implemented in a widely used high-level language.'
    ),
    DummyCourse(
      code: 'CP164',
      name: 'Data Structures I',
      rating: 4.6,
      description: 'Introduction to the study of data structures and their applications. Recursion, searching, sorting. Queues, stacks, heaps. Introduction to the analysis of algorithms, big "O" notation.'
    ),
    DummyCourse(
      code: 'CP212',
      name: 'Windows Application Programming',
      rating: 4.2,
      description: 'This course is designed for students who have a basic understanding of spreadsheets, word processors, and databases as well as introductory programming experience. The course introduces methods to automate repetitive tasks and create user-friendly applications in spreadsheets, word processors, and databases using the powerful macro language, Visual Basic for Applications (VBA). Topics include: a review of programming constructs such as data types, looping, conditional statements, and arrays; the design of graphical interfaces with the typical "look and feel" of Windows software; the design of dialog boxes with controls and event handling code that responds to user input; automating tasks; consolidating data; providing user-friendly reports.'
    ),
    DummyCourse(
      code: 'CP213',
      name: 'Introduction to Object-Oriented Programming',
      rating: 4.5,
      description: 'Fundamentals of object-oriented programming, classes, subclasses, inheritance, references, overloading, event-driven and concurrent programming, using modern application programming interface. The language Java will be used.'
    ),
    DummyCourse(
      code: 'CP264',
      name: 'Data Structures II',
      rating: 3.7,
      description: 'A continuation of the study of data structures and their applications using C. Linked lists, binary search trees, balanced search trees. Hashing, collision-avoidance strategies. A continuation of basic algorithm analysis.'
    ),
    DummyCourse(
      code: 'CP363',
      name: 'Database I',
      rating: 4.3,
      description: 'Introduction to database systems. Topics include data models, query languages, database design, recovery and concurrency, integrity and security.'
    ),
    DummyCourse(
      code: 'CP411',
      name: 'Computer Graphics',
      rating: 4.8,
      description: 'The principles, algorithms and techniques of computer graphics. Topics include introduction to graphics hardware, output primitives, two- and three-dimensional geometric transformations, three-dimensional object representation and viewing, illumination models and surface-rendering methods. Graphics software tools will be introduced in this course.'
    ),
    DummyCourse(
      code: 'CP470',
      name: 'Android Programming',
      rating: 4.8,
      description: 'How to write applications for the Android mobile devices using the Android Development Tools. Developing software with the Model-View-Controller paradigm. Knowledge of Java is required. The course project will be the development of an Android app.'
    ),
  ];

  List<DummyCourse> getDummyCourses() => courses;
}
