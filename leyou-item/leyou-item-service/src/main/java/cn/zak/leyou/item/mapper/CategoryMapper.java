package cn.zak.leyou.item.mapper;

import cn.zak.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
    @Select("select * from tb_category where id in (select category_id from tb_category_brand where brand_id=#{bid})")
    List<Category> selectByBid(Long bid);

}
