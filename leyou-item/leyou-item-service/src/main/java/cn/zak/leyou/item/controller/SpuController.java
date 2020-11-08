package cn.zak.leyou.item.controller;

import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.pojo.Spu;
import cn.zak.leyou.item.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService service;
    @GetMapping("/page")
    public ResponseEntity<PageResult<Spu>> findAll(@RequestParam(value = "key",required = false) String key,
                                             @RequestParam(value="saleable" , defaultValue = "true") Boolean saleable,
                                             @RequestParam(value = "page",defaultValue = "1") Integer page,
                                             @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                             @RequestParam(value = "rows",defaultValue = "5") Integer rows,
                                             @RequestParam(value = "desc", defaultValue = "false") Boolean desc){
        PageResult<Spu> all= this.service.getSpuByPage(key,page,sortBy,rows,desc,saleable);
        return ResponseEntity.ok(all);
    }
}
