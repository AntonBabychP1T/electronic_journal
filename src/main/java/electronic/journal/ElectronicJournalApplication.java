package electronic.journal;

import electronic.journal.model.Group;
import electronic.journal.model.Student;
import electronic.journal.model.Subject;
import electronic.journal.service.GradeService;
import electronic.journal.service.GroupService;
import electronic.journal.service.StudentService;
import electronic.journal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ElectronicJournalApplication {
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private GradeService gradeService;

	public static void main(String[] args) {

		SpringApplication.run(ElectronicJournalApplication.class, args);


	}
	@Bean
	CommandLineRunner commandLineRunner() {
		return  new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Subject history = new Subject();
				history.setName("History");
				Subject programming = new Subject();
				programming.setName("Programming");
				Subject management = new Subject();
				management.setName("Management");
				subjectService.addSubject(history);
				subjectService.addSubject(programming);
				subjectService.addSubject(management);
				Group manGroup = new Group();
				manGroup.setName("SP-237B");
				manGroup.setSubjects(List.of(programming, history));
				Group womanGroup = new Group();
				womanGroup.setName("MG-333A");
				womanGroup.setSubjects(List.of(history,management));
				groupService.addGroup(manGroup);
				groupService.addGroup(womanGroup);



			}
		};
	}

}
