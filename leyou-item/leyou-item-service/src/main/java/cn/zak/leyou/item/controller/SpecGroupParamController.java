package cn.zak.leyou.item.controller;

import cn.zak.leyou.item.pojo.SpecParam;
import cn.zak.leyou.item.service.SpecGroupParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/spec")
public class SpecGroupParamController {
    @Autowired
    private SpecGroupParamService sevice;

    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> getByGid(Long gid){
        List<SpecParam> list=this.sevice.getByGid(gid);
        if(null == list){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
}
