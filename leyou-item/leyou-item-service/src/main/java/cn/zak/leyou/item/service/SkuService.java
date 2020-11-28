package cn.zak.leyou.item.service;

import cn.zak.leyou.item.bo.SpuBo;
import cn.zak.leyou.item.pojo.Sku;

import java.util.List;

public interface SkuService {
    List<Sku> findBySpuId(Long id);

    void saveSpu(SpuBo spuBo);

    void editSpu(SpuBo spuBo);
}
