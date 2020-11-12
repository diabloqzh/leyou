package cn.zak.leyou.item.service;

import cn.zak.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecGroupParamService {

    List<SpecParam> getByGid(Long gid);

    void saveNew(SpecParam specParam);

    void save(SpecParam specParam);

    SpecParam getById(Long id);

    void delete(Long id);
}
