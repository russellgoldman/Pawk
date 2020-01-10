export class Course {
    code: string;
    name: string;
    credits: number;
    term: Array<string>;
    lectureHours: number;
    labHours: number;
    description: string;
    prerequisiteDescription: string;
    prerequisiteCourses: Array<string>;
    corequisiteDescription: string;
    corerequsiteCourses: Array<string>;
    exclusionsDescription: string;
    exclusionsCourses: Array<string>;

    constructor(data: any) {
        this.code = data.code;
        this.name = data.string;
        this.credits = data.credits;
        this.term = data.term;
        this.lectureHours = data.lectureHours;
        this.labHours = data.labHours;
        this.description = data.description;
        this.prerequisiteDescription = data.prerequisiteDescription;
        this.prerequisiteCourses = data.prerequisiteCourses;
        this.corequisiteDescription = data.corequisiteDescription;
        this.corerequsiteCourses = data.corerequsiteCourses;
        this.exclusionsDescription = data.exclusionsDescription;
        this.exclusionsCourses = data.exclusionsCourses;
    }
}