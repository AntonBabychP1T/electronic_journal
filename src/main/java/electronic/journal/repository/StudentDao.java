package electronic.journal.repository;

import electronic.journal.model.Group;
import electronic.journal.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    Student addStudent(Student student);
    Optional<Student> getStudentByLastName(String lastName);
    List<Student> getAllStudent();
    List<Student> getAllStudentByGroup(Group group);
}
