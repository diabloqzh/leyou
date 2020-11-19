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

    @Override
    public List<SpecParam> getByPojo(SpecParam specParam) {

        List<SpecParam> list = this.mapper.select(specParam);
        return list;
    }

    @Override
    public void saveNew(SpecParam specParam) {
        this.mapper.insert(specParam);
    }

    @Override
    public void save(SpecParam specParam) {
        this.mapper.updateByPrimaryKey(specParam);
    }

    @Override
    public SpecParam getById(Long id) {
        SpecParam specParam = this.mapper.selectByPrimaryKey(id);
        return specParam;
    }

    @Override
    public void delete(Long id) {
        this.mapper.deleteByPrimaryKey(id);
    }
}
