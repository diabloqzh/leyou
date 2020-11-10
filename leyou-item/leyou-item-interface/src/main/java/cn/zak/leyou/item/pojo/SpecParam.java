package cn.zak.leyou.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spec_param")
public class SpecParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cid;
    private Long groupId;
    private String name;
    private Boolean numeric;
    private Boolean unit;
    private Boolean generic;
    private Boolean searching;
    private Boolean segments;

    @Override
    public String toString() {
        return "SpecParam{" +
                "id=" + id +
                ", cid=" + cid +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", numeric=" + numeric +
                ", unit=" + unit +
                ", generic=" + generic +
                ", searching=" + searching +
                ", segemts=" + segments +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNumeric() {
        return numeric;
    }

    public void setNumeric(Boolean numeric) {
        this.numeric = numeric;
    }

    public Boolean getUnit() {
        return unit;
    }

    public void setUnit(Boolean unit) {
        this.unit = unit;
    }

    public Boolean getGeneric() {
        return generic;
    }

    public void setGeneric(Boolean generic) {
        this.generic = generic;
    }

    public Boolean getSearching() {
        return searching;
    }

    public void setSearching(Boolean searching) {
        this.searching = searching;
    }

    public Boolean getSegments() {
        return segments;
    }

    public void setSegments(Boolean segments) {
        this.segments = segments;
    }
}
