package electronic.journal.repository;

import electronic.journal.model.Grade;
import electronic.journal.model.Student;
import electronic.journal.model.Subject;

import java.util.List;
import java.util.Optional;

public interface GradeDao {
    Grade addGrade(Grade grade);
    Optional<Grade> getGradeByStudentAndSubject(Student student, Subject subject);
    List<Grade> getAllGradeForStudent(Student student);
    List<Grade> getAllGradeForSubject(Subject subject);
}
