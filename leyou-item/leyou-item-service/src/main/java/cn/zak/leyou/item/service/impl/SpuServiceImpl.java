package cn.zak.leyou.item.service.impl;


import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.bo.SpuBo;
import cn.zak.leyou.item.mapper.BrandMapper;
import cn.zak.leyou.item.mapper.CategoryMapper;
import cn.zak.leyou.item.mapper.SpuDetailMapper;
import cn.zak.leyou.item.mapper.SpuMapper;
import cn.zak.leyou.item.pojo.Category;
import cn.zak.leyou.item.pojo.Spu;
import cn.zak.leyou.item.pojo.SpuDetail;
import cn.zak.leyou.item.service.CategoryService;
import cn.zak.leyou.item.service.SpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    private SpuMapper mapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Override
    public List<Spu> findAll() {
        return this.mapper.selectAll();
    }

    @Override
    public PageResult<SpuBo> getSpuByPage(String key, Integer page, String sortBy, Integer rows, Boolean desc, Boolean saleable) {
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%" + key + "%");
        }
        if(saleable!=null){
            criteria.andEqualTo("saleable",saleable);
        }
        PageHelper.startPage(page,rows);
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+(desc?" desc":" asc"));
        }

        List<Spu> spus = mapper.selectByExample(example);
        PageInfo<Spu> info=new PageInfo<>(spus);
        List<SpuBo> spuBos = spus.stream().map((spu) -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            spuBo.setBname(this.brandMapper.selectByPrimaryKey(spuBo.getBrandId()).getName());
            List<String> cNames = this.categoryService.findByIds(Arrays.asList(spuBo.getCid1(), spuBo.getCid2(), spuBo.getCid3()));
            spuBo.setCname(StringUtils.join(cNames,"->"));
            return spuBo;
        }).collect(Collectors.toList());

        PageResult<SpuBo> all=new PageResult();

        all.setItems(spuBos);
        all.setTotal(info.getTotal());
        all.setTotalPage(info.getPages());
        return all;
    }

    @Override
    public Spu fingById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public SpuDetail findDetailBySpuId(long id) {
        Example example=new Example(SpuDetail.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId",id);
        SpuDetail spuDetail = this.spuDetailMapper.selectOneByExample(example);
        return spuDetail;
    }
}
