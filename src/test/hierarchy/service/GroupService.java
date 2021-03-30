package test.hierarchy.service;

import test.hierarchy.domain.Group;

public interface GroupService {

    /**
     * Fetches a group given its ID
     *
     * @param groupId the group ID to search with
     * @return an {@link Group} instance, or null if none found.
     */
    Group getGroupById(Integer groupId);

    /**
     * Returns the ID of the top-level parent of the group with the given {@code groupId}.
     *
     * @param groupId the group ID of the child group.
     * @return an ID that belongs to a top-level group, or null if none found.
     */
    Integer getTopLevelGroupId(Integer groupId);
}
