package cn.zak.leyou.item.controller;

import cn.zak.leyou.common.pojo.PageResult;
import cn.zak.leyou.item.pojo.Brand;
import cn.zak.leyou.item.service.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping("/list")
    public ResponseEntity<List<Brand>> listAll() {
        List<Brand> all = brandService.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(all);
        }
    }

    /**
     * 根据分页条件查询品牌信息
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> listByPage(@RequestParam(value = "key", required = false) String key,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "rows", defaultValue = "5") Integer rows,
                                                        @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                        @RequestParam(value = "desc", defaultValue = "false") Boolean desc) {

        //http://api.leyou.com/api/item/brand/page?key=&page=1&rows=5&sortBy=id&desc=false
        PageResult<Brand> all = brandService.getBrandsByPage(key, page, rows, sortBy, desc);
        if (all == null || CollectionUtils.isEmpty(all.getItems())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(all);
        }
    }
    @GetMapping("/del/{id}")
    public ResponseEntity<Void> delById(@PathVariable(name = "id",required = true) Integer id){
        Brand brand = this.brandService.findById(id);
        if(brand==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            this.brandService.removeById(id);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
    @GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> getByCid(@PathVariable(name="cid",required = true) Integer cid){
        List<Brand> list = this.brandService.getByCid(cid);
        if(CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(list);
        }
    }
    @GetMapping("/bid/{id}")
    public ResponseEntity<Brand> getById(@PathVariable(name = "id",required = true) Integer id){
        Brand brand= this.brandService.findById(id);
        if(brand == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.ok(brand);
        }
    }
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids") List<Long> cids){
        System.out.println(brand);
        System.out.println(cids);
        this.brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<Void> saveEditBrand(Brand brand,@RequestParam("cids") List<Long> cids){
        System.out.println(brand);
        System.out.println(cids);
        this.brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//    @PostMapping
    public ResponseEntity<Void> saveBrand2(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = new HashMap<>();
        try {
            map = objectMapper.readValue(body, new TypeReference<HashMap<String, String>>() {});
            System.out.println(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        System.out.println(brand);
//        System.out.println(cids);
//        this.brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
