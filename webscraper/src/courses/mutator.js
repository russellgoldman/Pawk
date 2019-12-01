require('cross-fetch/polyfill');
const ApolloBoost = require('apollo-boost');
const ApolloClient = ApolloBoost.default;
const gql = ApolloBoost.gql;

const client = new ApolloClient({
  uri: 'http://localhost:5433/graphql'
});

function createCourse(course) {  
    client
        .mutate({
            mutation: CREATE_COURSE,
            variables: {
                CODE: course.code,
                NAME: course.name,
                CREDITS: course.credits,
                LECTURE_HOURS: course.lecture_hours,
                LAB_HOURS: course.lab_hours,
                DESCRIPTION: course.description,
                PREREQ_DESC: course.prereq_desc,
                PREREQ_COURSES: course.prereq_courses,
                COREQ_DESC: course.coreq_desc,
                COREQ_COURSES: course.coreq_courses,
                EXC_DESC: course.exc_desc,
                EXC_COURSES: course.exc_courses
            }
        })
        .then(() => {
            console.log(`Successfully mutated ${course.code}\n`)
        })
        .catch((err) => {
            console.log(`Duplicate course ${course.code}\n`);
        })
};

const CREATE_COURSE = gql`
  mutation(
    $CODE: String!
    $NAME: String!
    $CREDITS: Float!
    $LECTURE_HOURS: Float
    $LAB_HOURS: Float
    $DESCRIPTION: String
    $PREREQ_DESC: String
    $PREREQ_COURSES: [String]
    $COREQ_DESC: String
    $COREQ_COURSES: [String]
    $EXC_DESC: String
    $EXC_COURSES: [String]
  ) {
    createCourse(
      input: {
        course: {
          code: $CODE
          name: $NAME
          credits: $CREDITS
          lectureHours: $LECTURE_HOURS
          labHours: $LAB_HOURS
          description: $DESCRIPTION
          prerequisiteDescription: $PREREQ_DESC
          prerequisiteCourses: $PREREQ_COURSES
          corequisiteDescription: $COREQ_DESC
          corequisiteCourses: $COREQ_COURSES
          exclusionsDescription: $EXC_DESC
          exclusionsCourses: $EXC_COURSES
        }
      }
    ) {
      course {
        code
        name
        credits
        lectureHours
        labHours
        description
        prerequisiteDescription
        prerequisiteCourses
        corequisiteDescription
        corequisiteCourses
        exclusionsDescription
        exclusionsCourses
      }
    }
  }
`;

module.exports = {
    createCourse
}