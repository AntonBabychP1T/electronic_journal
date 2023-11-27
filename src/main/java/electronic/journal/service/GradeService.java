package electronic.journal.service;

import electronic.journal.model.Grade;
import electronic.journal.model.Student;
import electronic.journal.model.Subject;

import java.util.List;

public interface GradeService {
    Grade addGrade(Student student, Subject subject, int score);
    Grade getGradeForSubjectByStudent(Student student, Subject subject);
    List<Grade> getAllGradeByStudent(Student student);
    Double getAverageGradeByStudent(Student student);
}
