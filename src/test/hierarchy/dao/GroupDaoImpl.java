package test.hierarchy.dao;

import test.hierarchy.domain.Group;
import test.hierarchy.service.MockData;

import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private final List<Group> groups;
    private final MockData mockData;

    public GroupDaoImpl(MockData mockData) {
        this.mockData = mockData;
        groups = mockData.getData();
    }

    @Override
    public List<Group> getAllGroups() {
        return groups;
    }

    @Override
    public Group getGroupById(Integer groupId) {
        if(groupId == null) {
            throw new IllegalArgumentException("groupId should not be null");
        }

        return groups.stream().filter(g -> g.getId().equals(groupId)).findFirst().orElseThrow();
    }
}
