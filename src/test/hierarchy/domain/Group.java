package test.hierarchy.domain;

/**
 * This class represents the objects that are loaded from the DB table.
 */
public class Group {

    private Integer id;
    private String name;
    private Integer parentId;
    private Integer topLevelId;

    public Group(Integer id, String name, Integer parentId, Integer topLevelId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.topLevelId = topLevelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTopLevelId() {
        return topLevelId;
    }

    public void setTopLevelId(Integer topLevelId) {
        this.topLevelId = topLevelId;
    }
}
