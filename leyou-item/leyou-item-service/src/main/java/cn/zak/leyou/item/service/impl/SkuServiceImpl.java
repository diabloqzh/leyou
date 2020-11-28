package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.item.bo.SpuBo;
import cn.zak.leyou.item.mapper.SkuMapper;
import cn.zak.leyou.item.mapper.SpuDetailMapper;
import cn.zak.leyou.item.mapper.SpuMapper;
import cn.zak.leyou.item.mapper.StockMapper;
import cn.zak.leyou.item.pojo.Sku;
import cn.zak.leyou.item.pojo.SpuDetail;
import cn.zak.leyou.item.pojo.Stock;
import cn.zak.leyou.item.service.SkuService;
import cn.zak.leyou.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuMapper mapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private StockMapper stockMapper;
    @Override
    public List<Sku> findBySpuId(Long id) {
        Example ex=new Example(Sku.class);
        Example.Criteria criteria = ex.createCriteria();
        criteria.andEqualTo("spuId",id);
        List<Sku> skus = this.mapper.selectByExample(ex);
        skus.forEach(sku -> {
            Stock stock = this.stockMapper.selectByPrimaryKey(sku.getId());
            sku.setStock(stock.getStock());
        });
        return skus;
    }

    @Override
    @Transactional
    public void saveSpu(SpuBo spuBo) {
        //先新增spu
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());
        spuBo.setLastUpdateTime(new Date());
        this.spuMapper.insertSelective(spuBo);
        //新增spudetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        this.spuDetailMapper.insertSelective(spuDetail);
        //新增sku
        List<Sku> skus = spuBo.getSkus();
        for(Sku sku : skus){
            sku.setId(null);
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(new Date());
            sku.setSpuId(spuBo.getId());
            this.mapper.insertSelective(sku);
            Stock stock = new Stock();
            stock.setSeckillStock(0);
            stock.setSeckillTotal(0);
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock);
        }
        //新增stock
    }

    @Override
    @Transactional
    public void editSpu(SpuBo spuBo) {
        //删除stock
        Sku tempSku=new Sku();
        tempSku.setSpuId(spuBo.getId());
        List<Sku> oldSku = this.mapper.select(tempSku);
        oldSku.forEach((sku)->{
            this.stockMapper.deleteByPrimaryKey(sku.getId());
        });
        this.mapper.delete(tempSku);

        //先修改spu

        spuBo.setLastUpdateTime(new Date());
        this.spuMapper.updateByPrimaryKey(spuBo);
//        this.spuMapper.insertSelective(spuBo);
        //修改spudetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        this.spuDetailMapper.updateByPrimaryKeySelective(spuDetail);

        //新增sku


        List<Sku> skus = spuBo.getSkus();
        for(Sku sku : skus){
            sku.setId(null);
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(new Date());
            sku.setSpuId(spuBo.getId());
            this.mapper.insertSelective(sku);
            Stock stock = new Stock();
            stock.setSeckillStock(0);
            stock.setSeckillTotal(0);
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock);
        }
        //新增stock
    }
}
