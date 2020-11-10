package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.item.mapper.SpecParamMapper;
import cn.zak.leyou.item.pojo.SpecParam;
import cn.zak.leyou.item.service.SpecGroupParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpecParamServiceImpl implements SpecGroupParamService {
    @Autowired
    private SpecParamMapper mapper;

    @Override
    public List<SpecParam> getByGid(Long gid) {
        Example example=new Example(SpecParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId",gid);
        return this.mapper.selectByExample(example);

    }
}
