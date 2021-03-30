package test.hierarchy.dao;

import test.hierarchy.domain.Group;

import java.util.List;

public interface GroupDao {

    List<Group> getAllGroups();

    Group getGroupById(Integer groupId);
}
