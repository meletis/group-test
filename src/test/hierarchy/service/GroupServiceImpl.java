package test.hierarchy.service;

import test.hierarchy.GroupModel;
import test.hierarchy.dao.GroupDao;
import test.hierarchy.domain.Group;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    /**
     * This is a cache that holds the group structure
     */
    private final GroupModel cachedGroupModel = new GroupModel();

    private GroupDao groupDao;

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group getGroupById(Integer groupId) {
        if (groupId == null) {
            return null;
        }

//        // Using the cache
//        GroupModel groupModel = getGroupModel();
//        return groupModel.getById(groupId);

        // Using the database
        return getGroupDao().getGroupById(groupId);
    }

    @Override
    public Integer getTopLevelGroupId(Integer groupId) {
        if (groupId == null) {
            // that means that the objectId is invalid or deleted!
            // we need to avoid an NPE to the caller.
            return null;
        }

        // TODO: Consider a solution that doesn't involve any cache

        // then we need to find the top-level group that contains that group.
        GroupModel groupModel = getGroupModel();
        return groupModel.findTopLevelGroupId(groupId);
    }

    protected GroupModel getGroupModel() {
        synchronized (this) {
            if (cachedGroupModel.isEmpty()) {
                // TODO: consider moving this slow operation outside the synchronized block.
                List<Group> groups = getGroupDao().getAllGroups();
                cachedGroupModel.cache(groups);
            }
        }

        return cachedGroupModel;
    }
}
