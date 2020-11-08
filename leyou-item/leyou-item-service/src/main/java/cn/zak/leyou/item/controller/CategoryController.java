package cn.zak.leyou.item.controller;

import cn.zak.leyou.item.pojo.Category;
import cn.zak.leyou.item.pojo.SpecGroup;
import cn.zak.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id 查询子节点
     *
     * @param pid
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<List<Category>> getCategroyByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        if (pid == null || pid < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<Category> categories = categoryService.findByPid(pid);
        if (!CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> getCategroyByBid(@PathVariable(name = "bid") Long bid){
        List<Category> list=this.categoryService.findByBid(bid);
        if(list==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(list);
        }

    }
}
