package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.item.mapper.SpecGroupMapper;
import cn.zak.leyou.item.pojo.SpecGroup;
import cn.zak.leyou.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SpecGroupServiceImpl implements SpecGroupService {
    @Autowired
    private SpecGroupMapper mapper;

    @Override
    public List<SpecGroup> getByCid(Long cid) {
        Example example=new Example(SpecGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cid",cid);
        List<SpecGroup> specGroups = mapper.selectByExample(example);
        return specGroups;
    }

    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        this.mapper.updateByPrimaryKey(specGroup);
    }

    @Override
    public void addSpecGroup(SpecGroup specGroup) {
        this.mapper.insert(specGroup);
    }

    @Override
    public SpecGroup getById(Long id) {
        SpecGroup specGroup = this.mapper.selectByPrimaryKey(id);
        return specGroup;
    }

    @Override
    public void deleteById(Long id) {
        this.mapper.deleteByPrimaryKey(id);
    }
}
