UPDATE pawk.programs 
SET pawk.programs.required_courses = '{{CP104,CP164},{CP213,CP264},{CP312,CP372},{CP386,CP414}}'
WHERE pawk.programs.name = 'Computer Science';

DELETE FROM pawk.programs
WHERE pawk.programs.name = 'Honours BSc Computer Science';
