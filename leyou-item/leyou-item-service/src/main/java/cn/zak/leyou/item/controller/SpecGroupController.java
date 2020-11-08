package cn.zak.leyou.item.controller;

import cn.zak.leyou.item.pojo.Category;
import cn.zak.leyou.item.pojo.SpecGroup;
import cn.zak.leyou.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/spec")
public class SpecGroupController {
    @Autowired
    private SpecGroupService service;
    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> getByCid(@PathVariable(name = "cid",required = true) Long cid){
        List<SpecGroup> result = this.service.getByCid(cid);
        if(result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/group")
    public ResponseEntity<Void> save(SpecGroup specGroup){
        this.service.saveSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
