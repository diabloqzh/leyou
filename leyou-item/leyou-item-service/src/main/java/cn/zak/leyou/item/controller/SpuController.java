package cn.zak.leyou.item.controller;

import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.bo.SpuBo;
import cn.zak.leyou.item.pojo.Spu;
import cn.zak.leyou.item.pojo.SpuDetail;
import cn.zak.leyou.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService service;
    @GetMapping("/page")
    public ResponseEntity<PageResult<SpuBo>> findAll(@RequestParam(value = "key",required = false) String key,
                                                     @RequestParam(value="saleable" , required = false) Boolean saleable,
                                                     @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                     @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                     @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                                                     @RequestParam(value = "desc", defaultValue = "false") Boolean desc){
        PageResult<SpuBo> all= this.service.getSpuByPage(key,page,sortBy,rows,desc,saleable);
        if(all==null || CollectionUtils.isEmpty(all.getItems())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(all);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<SpuDetail> findDetailBySpuId(@PathVariable(name = "id",required = true) long id){
        SpuDetail detail=this.service.findDetailBySpuId(id);
        if(detail==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(detail);
        }
    }
    public ResponseEntity<Spu> findById(@PathVariable(name = "id",required = true) Long id){

        Spu spu=this.service.fingById(id);
        if(spu==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(spu);
        }
    }
}
