package test.hierarchy.service;

import test.hierarchy.GroupModel;
import test.hierarchy.dao.GroupDao;
import test.hierarchy.domain.Group;

public class GroupServiceImpl implements GroupService {

    /**
     * This is a cache that holds the group structure
     */
    private final GroupModel cachedGroupModel = new GroupModel();

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group getGroupById(Integer groupId) {
        if (groupId == null) {
            return null;
        }

        // Using the database
        return groupDao.getGroupById(groupId);
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

        try {
            return groupDao.getGroupById(groupId).getTopLevelId();
        }catch (Exception e) {
            //Log exception
            return null;
        }
    }

}
