package cn.zak.leyou.item.mapper;

import cn.zak.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface BrandMapper extends Mapper<Brand> {

    @Insert("insert into tb_category_brand (category_id,brand_id) values(#{cid},#{id})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("id") Long id);
    @Select("select b.* from tb_category_brand c ,tb_brand b where c.brand_id=b.id and c.category_id=#{cid}")
    List<Brand> getByCid(@Param("cid") Integer cid);
}
