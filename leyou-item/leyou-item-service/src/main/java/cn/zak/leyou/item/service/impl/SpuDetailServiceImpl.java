package cn.zak.leyou.item.service.impl;

import cn.zak.leyou.item.mapper.SpuDetailMapper;
import cn.zak.leyou.item.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpuDetailServiceImpl implements SpuDetailService {
    @Autowired
    private SpuDetailMapper mapper;
}
