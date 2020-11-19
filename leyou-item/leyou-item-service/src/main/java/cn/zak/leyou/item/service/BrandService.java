package cn.zak.leyou.item.service;

import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.pojo.Brand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {
    public List<Brand> findAll();

    PageResult<Brand> getBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void saveBrand(Brand brand, List<Long> cids);

    Brand findById(Integer id);
    void removeById(Integer id);


    List<Brand> getByCid(Integer cid);
}
