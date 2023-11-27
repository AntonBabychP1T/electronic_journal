package electronic.journal.service.impl;

import electronic.journal.repository.GroupDao;
import electronic.journal.model.Group;
import electronic.journal.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Override
    public Group addGroup(Group group) {
        return groupDao.addGroup(group);
    }
}
