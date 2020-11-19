package cn.zak.leyou.item.service;

import cn.zak.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    List<Category> findByPid(Long pid);

    List<Category> findByBid(Long bid);
    List<String> findByIds(List<Long> ids);
}
