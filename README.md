# hierarchy-test

Our sample application is using a structure where:

1. There is only one **root** group. That group's parent ID is null. That's level 1 in the hierarchy and that level contains only one group, the root group.
2. There is an unknown number of groups in the next (2nd) level. The groups in that level are called "**top-level**" groups, and the parent of those is the root group.
3. There is an unknown number of levels beyond the second level.

This is the database schema behind this application:

```sql
CREATE TABLE `__GROUP`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `name`     varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parentId` int(11)                              DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_GROUP_TITLE` (`name`),
  KEY `IDX_PARENT_ID` (`parentId`),
  CONSTRAINT `fk_groups_group_id` FOREIGN KEY (`parentId`) REFERENCES `__GROUP` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci
```

## Application requirement 

The application has only one requirement: for any given group X in the tree the application is required to be able to get the ID of the top-level group that X is nested in.

## Problem description

The GroupServiceImpl class is using a local cache. The problem around that is two-fold:

* It prevents the application to scale horizontally.

* It can potentially make the heap space explode (it doesn't scale).

So, we need to make any necessary changes to eliminate the need of the cache. Such changes may include (but not limited to) the following:

1. Changes in the database structure
2. Changes in the Java code

## Proposed solutions

The while loop in the "findTopLevelGroupId"method located in GroupModel.java file can make the heap space explode as there is an unknown number of levels beyond second level. Adding a "top_level_id" field in the database schema can serve as an alternative for that while loop because for every group we can store the top_level_id here.

We can use a recursive CTE(common table expression) or a self-join to get the value of the  top_level_id field