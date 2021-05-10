package test.hierarchy;

import test.hierarchy.dao.GroupDao;
import test.hierarchy.dao.GroupDaoImpl;
import test.hierarchy.service.GroupService;
import test.hierarchy.service.GroupServiceImpl;
import test.hierarchy.service.MockData;

public class Application {
    public static void main(String[] args) {
        GroupDao groupRepository = new GroupDaoImpl(new MockData());
        GroupService groupService = new GroupServiceImpl(groupRepository);
        var group = groupService.getGroupById(1);
        var topLevel = groupService.getTopLevelGroupId(5);
        System.out.println("Top Level " + topLevel);
    }
}
