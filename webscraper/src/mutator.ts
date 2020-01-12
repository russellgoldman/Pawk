require('cross-fetch/polyfill');
const ApolloBoost = require('apollo-boost');
const ApolloClient = ApolloBoost.default;
const gql = ApolloBoost.gql;
import { Course } from './models';

const client = new ApolloClient({
  uri: 'http://localhost:5433/graphql'
});

export function createCourse(course: Course) {  
    client
        .mutate({
            mutation: CREATE_COURSE,
            variables: {
                CODE: course.code,
                NAME: course.name,
                CREDITS: course.credits,
                LECTURE_HOURS: course.lectureHours,
                LAB_HOURS: course.labHours,
                DESCRIPTION: course.description,
                PREREQ_DESC: course.prerequisiteDescription,
                PREREQ_COURSES: course.prerequisiteCourses,
                COREQ_DESC: course.corequisiteDescription,
                COREQ_COURSES: course.corerequsiteCourses,
                EXC_DESC: course.exclusionsDescription,
                EXC_COURSES: course.exclusionsCourses,
                NOTES: course.notes
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
    $NOTES: String
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
          notes: $NOTES
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