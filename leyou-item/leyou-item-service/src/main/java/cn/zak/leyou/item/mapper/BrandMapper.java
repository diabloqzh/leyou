package cn.zak.leyou.item.mapper;

import cn.zak.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface BrandMapper extends Mapper<Brand> {

    @Insert("insert into tb_category_brand (category_id,brand_id) values(#{cid},#{id})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("id") Long id);
}
