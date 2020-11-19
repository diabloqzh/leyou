package cn.zak.leyou.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_spu_detail")
public class SpuDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spuId;
    private String spu_id;
    private String description;
    private String generic_spec;
    private String special_spec;
    private String packing_list;
    private String after_service;

    @Override
    public String toString() {
        return "SpuDetail{" +
                "spuId=" + spuId +
                ", spu_id='" + spu_id + '\'' +
                ", description='" + description + '\'' +
                ", generic_spec='" + generic_spec + '\'' +
                ", special_spec='" + special_spec + '\'' +
                ", packing_list='" + packing_list + '\'' +
                ", after_service='" + after_service + '\'' +
                '}';
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGeneric_spec() {
        return generic_spec;
    }

    public void setGeneric_spec(String generic_spec) {
        this.generic_spec = generic_spec;
    }

    public String getSpecial_spec() {
        return special_spec;
    }

    public void setSpecial_spec(String special_spec) {
        this.special_spec = special_spec;
    }

    public String getPacking_list() {
        return packing_list;
    }

    public void setPacking_list(String packing_list) {
        this.packing_list = packing_list;
    }

    public String getAfter_service() {
        return after_service;
    }

    public void setAfter_service(String after_service) {
        this.after_service = after_service;
    }
}
