package electronic.journal.service.impl;

import electronic.journal.repository.StudentDao;
import electronic.journal.model.Group;
import electronic.journal.model.Student;
import electronic.journal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentByLastName(String lastName) {
        return studentDao.getStudentByLastName(lastName).get();
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDao.getAllStudent();
    }

    @Override
    public List<Student> getAllStudentByGroup(Group group) {
        return studentDao.getAllStudentByGroup(group);
    }
}
