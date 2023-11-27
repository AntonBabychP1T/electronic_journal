package electronic.journal.service.impl;

import electronic.journal.model.Grade;
import electronic.journal.model.Student;
import electronic.journal.model.Subject;
import electronic.journal.repository.GradeDao;
import electronic.journal.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeDao gradeDao;
    @Override
    public Grade addGrade(Student student, Subject subject, int score) {
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setScore(score);
        return gradeDao.addGrade(grade);
    }

    @Override
    public Grade getGradeForSubjectByStudent(Student student, Subject subject) {
        return gradeDao.getGradeByStudentAndSubject(student,subject).get();
    }

    @Override
    public List<Grade> getAllGradeByStudent(Student student) {
        return gradeDao.getAllGradeForStudent(student);
    }

    @Override
    public Double getAverageGradeByStudent(Student student) {
        List<Grade> allGradeByStudent = getAllGradeByStudent(student);
        return allGradeByStudent.stream().mapToInt(Grade::getScore).average().getAsDouble();
    }
}
