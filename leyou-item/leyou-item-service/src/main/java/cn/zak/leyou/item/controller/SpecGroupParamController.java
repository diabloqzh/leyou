package cn.zak.leyou.item.controller;

import cn.zak.leyou.item.pojo.SpecParam;
import cn.zak.leyou.item.service.SpecGroupParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/spec")
public class SpecGroupParamController {
    @Autowired
    private SpecGroupParamService service;

    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> getByGid(
            @RequestParam(value = "gid" ,required = false) Long gid,
            @RequestParam(value = "cid" ,required = false) Long cid,
            @RequestParam(value = "generic" ,required = false) Boolean generic,
            @RequestParam(value = "searching" ,required = false) Boolean searching
            ){
        List<SpecParam> list;
        SpecParam specParam=new SpecParam();
        specParam.setCid(cid);
        specParam.setGroupId(gid);
        specParam.setGeneric(generic);
        specParam.setSearching(searching);
        list=this.service.getByPojo(specParam);
        if(null == list){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(list);
        }
    }

    @PostMapping("/param")
    public ResponseEntity<Void> saveNew(@RequestBody  SpecParam specParam){
        System.out.println(specParam);
        try{
            this.service.saveNew(specParam);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/param")
    public ResponseEntity<Void> save(@RequestBody SpecParam specParam){

        try{
            this.service.save(specParam);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/param/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id){

        try{
            SpecParam item=this.service.getById(id);
            if(null == item){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            this.service.delete(id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
