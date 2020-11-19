package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.item.mapper.CategoryMapper;
import cn.zak.leyou.item.pojo.Category;
import cn.zak.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    /**
     * 根据parentid获取分类
     *
     * @param pid
     * @return List<Categroy>
     */
    @Override
    public List<Category> findByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return this.categoryMapper.select(category);
    }

    @Override
    public List<Category> findByBid(Long bid) {
        return this.categoryMapper.selectByBid(bid);
    }

    @Override
    public List<String> findByIds(List<Long> ids) {
        List<Category> categoryList = this.categoryMapper.selectByIdList(ids);
        return categoryList.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}
