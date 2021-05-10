package test.hierarchy.service;

import test.hierarchy.domain.Group;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public List<Group> getData(){
        var data = new ArrayList<Group>();
        Group root = new Group(1, "root", null, null);
        Group top1 = new Group(2, "Level 1", root.getId(), 2);
        Group top2 = new Group(3, "Level 2", root.getId(), 3);
        Group child1 = new Group(4, "Child 1", top1.getId(), top1.getId());
        Group grandChild1 = new Group(5, "Grand Child 1", child1.getId(), top1.getId());
        data.addAll(List.of(root, top1, top2, child1, grandChild1));
        return data;
    }
}
