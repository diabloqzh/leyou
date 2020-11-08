package cn.zak.leyou.item.service.impl;


import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.mapper.SpuMapper;
import cn.zak.leyou.item.pojo.Spu;
import cn.zak.leyou.item.service.SpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper mapper;
    @Override
    public List<Spu> findAll() {
        return this.mapper.selectAll();
    }

    @Override
    public PageResult<Spu> getSpuByPage(String key, Integer page, String sortBy, Integer rows, Boolean desc, Boolean saleable) {
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%" + key + "%");
        }
        criteria.andEqualTo("saleable",saleable);
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+(desc?" desc":" asc"));
        }
        List<Spu> spus = mapper.selectByExample(example);
        PageResult<Spu> all=new PageResult();
        PageInfo<Spu> info=new PageInfo<>(spus);
        all.setItems(info.getList());
        all.setTotal(info.getTotal());
        all.setTotalPage(info.getPages());
        return all;
    }
}
