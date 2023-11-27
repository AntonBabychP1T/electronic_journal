package electronic.journal.service;

import electronic.journal.model.Group;
import electronic.journal.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudentByLastName(String lastName);
    List<Student> getAllStudent();
    List<Student> getAllStudentByGroup(Group group);
}
