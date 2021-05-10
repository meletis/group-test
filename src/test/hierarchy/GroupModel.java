package test.hierarchy;

import test.hierarchy.domain.Group;

import java.util.*;

/**
 * Author: Meletis Margaritis
 */
public class GroupModel {

  private final Map<Integer, Group> groupMap;

  public GroupModel() {
    this(new LinkedHashMap<>());
  }

  public GroupModel(Map<Integer, Group> groupMap) {
    this.groupMap = groupMap;
  }

  public Group getById(int groupId) {
    return groupMap.get(groupId);
  }

  public Integer findTopLevelGroupId(int groupId) {
    Group group = groupMap.get(groupId);
    // it may be a deleted group.
    if (group == null) {
      return null;
    }
    Integer parentGroupId = group.getParentId();

    // first check this this is the root group
    if (parentGroupId == null) {
      // yes, it is, so the "top-level" group for the root is the root. (that's just a convention of ours)
      return groupId;
    }

    // then check if this is already a top-level group
    if (groupMap.get(parentGroupId).getParentId() == null) {
      return groupId;
    }

    // otherwise it is a common folder deeper.

    //This while loop can make the heap space explode as we are dealing with unlimited levels.
    
    /*An alternative for this is to add a top_level_id field in the database schema so for every child reference we can have an immediate parent reference
     and there is no need to move through the entire tree*/

    while (true) {
      Group parent = groupMap.get(parentGroupId);
      if (groupMap.get(parent.getParentId()).getParentId() == null) {
        // we found a top-level group!
        return parent.getId();
      }

      parentGroupId = parent.getParentId();
    }
  }

  public void clear() {
    groupMap.clear();
  }

  public boolean isEmpty() {
    return groupMap.isEmpty();
  }

  public void cache(List<Group> groups) {
    this.clear();

    for (Group group : groups) {
      groupMap.put(group.getId(), group);
    }
  }
}
