package electronic.journal.service.impl;

import electronic.journal.repository.SubjectDao;
import electronic.journal.model.Subject;
import electronic.journal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public Subject addSubject(Subject subject) {
        return subjectDao.addSubject(subject);
    }
}
