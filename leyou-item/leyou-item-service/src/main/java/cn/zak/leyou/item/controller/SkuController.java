package cn.zak.leyou.item.controller;

import cn.zak.leyou.item.bo.SpuBo;
import cn.zak.leyou.item.pojo.Sku;
import cn.zak.leyou.item.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class SkuController {


    @Autowired
    private SkuService service;
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> findBySpuId(@RequestParam(value = "id",required = true) Long id){
        List<Sku> list=this.service.findBySpuId(id);
        if(null==list || CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
    @PostMapping("goods")
    public ResponseEntity<Void> saveSpu(@RequestBody SpuBo spuBo){
        System.out.println(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
