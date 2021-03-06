package cn.zak.leyou.item.controller;

import cn.zak.leyou.item.pojo.Category;
import cn.zak.leyou.item.pojo.SpecGroup;
import cn.zak.leyou.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.util.CollectionUtils;
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
        if(CollectionUtils.isEmpty(result)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(result);
        }
    }
    @PutMapping("/group")
    public ResponseEntity<Void> save(SpecGroup specGroup){
        this.service.saveSpecGroup(specGroup);
        System.out.println(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/group")
    public ResponseEntity<Void> newSave(SpecGroup specGroup){
        this.service.addSpecGroup(specGroup);
        System.out.println(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){
        SpecGroup item=this.service.getById(id);
        if(item==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try{
            this.service.deleteById(id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
