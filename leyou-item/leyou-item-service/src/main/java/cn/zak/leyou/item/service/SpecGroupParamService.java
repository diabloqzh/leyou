package cn.zak.leyou.item.service;

import cn.zak.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecGroupParamService {

    List<SpecParam> getByGid(Long gid);
}
