package cn.zak.leyou.item.service;

import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.pojo.Spu;

import java.util.List;

public interface SpuService {
    List<Spu> findAll();

    PageResult<Spu> getSpuByPage(String key, Integer page, String sortBy, Integer rows, Boolean desc, Boolean saleable);
}