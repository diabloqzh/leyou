package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.mapper.BrandMapper;
import cn.zak.leyou.item.pojo.Brand;
import cn.zak.leyou.item.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据分页条件获取查询内容
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @Override
    public PageResult<Brand> getBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化一个Example
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name 模糊查询和letter进行查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%");
        }
        criteria.andLike("name", "%" + key + "%").orLike("letter", key);
        //添加limit条件
        PageHelper.startPage(page, rows);
        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        return new PageResult<>(brandPageInfo.getTotal(), brandPageInfo.getPages(), brandPageInfo.getList());
    }

    @Override
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        if(brand.getId()!=null){
            this.brandMapper.updateByPrimaryKey(brand);
        }else{
            Boolean flag = this.brandMapper.insertSelective(brand)==1;
            if(flag){
                cids.forEach(cid->{
                    this.brandMapper.insertCategoryAndBrand(cid,brand.getId());
                });
            }
        }
        return ;
    }

    @Override
    public Brand findById(Integer id) {
        return this.brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void removeById(Integer id) {
        this.brandMapper.deleteByPrimaryKey(id);
    }
}
