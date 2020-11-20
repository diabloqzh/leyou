package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.item.mapper.SkuMapper;
import cn.zak.leyou.item.pojo.Sku;
import cn.zak.leyou.item.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuMapper mapper;

    @Override
    public List<Sku> findBySpuId(Long id) {
        Example ex=new Example(Sku.class);
        Example.Criteria criteria = ex.createCriteria();
        criteria.andEqualTo("spuId",id);
        List<Sku> skus = this.mapper.selectByExample(ex);
        return skus;
    }
}
