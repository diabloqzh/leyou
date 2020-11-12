package cn.zak.leyou.item.service;

import cn.zak.leyou.item.pojo.SpecGroup;

import java.util.List;

public interface SpecGroupService {

    List<SpecGroup> getByCid(Long cid);

    void saveSpecGroup(SpecGroup specGroup);

    void addSpecGroup(SpecGroup specGroup);

    SpecGroup getById(Long id);

    void deleteById(java.lang.Long id);
}
