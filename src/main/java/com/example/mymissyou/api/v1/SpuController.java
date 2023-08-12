package com.example.mymissyou.api.v1;

import com.example.mymissyou.bo.PageCounter;
import com.example.mymissyou.exception.http.NotFoundException;
import com.example.mymissyou.model.Spu;
import com.example.mymissyou.service.SpuService;
import com.example.mymissyou.util.CommonUtil;
import com.example.mymissyou.vo.Paging;
import com.example.mymissyou.vo.PagingDozer;
import com.example.mymissyou.vo.SpuSimplifyVO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;

    @GetMapping("/id/{id}/detail")
    public Spu getDetail(@PathVariable @Positive Long id) {
        Spu spu = this.spuService.getSpu(id);
        if (spu == null) {
            throw new NotFoundException(30003);
        }
        return spu;
    }

    @GetMapping("/id/{id}/simplify")
    public SpuSimplifyVO getSimplifySpu(@PathVariable @Positive Long id) {
        Spu spu = this.spuService.getSpu(id);
        if (spu == null) {
            throw new NotFoundException(30003);
        }
        SpuSimplifyVO vo = new SpuSimplifyVO();
        BeanUtils.copyProperties(spu, vo);
        return vo;
    }

    @GetMapping("/latest/all")
    @ResponseBody
    public List<SpuSimplifyVO> getAll() {
        List<Spu> spuList = spuService.getAll();
        System.out.println(spuList);
        List<SpuSimplifyVO> voList = new ArrayList<>();
        spuList.forEach(spu -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            SpuSimplifyVO vo = mapper.map(spu, SpuSimplifyVO.class);
            voList.add(vo);
        });
        return voList;
    }

    @GetMapping("/latest")
    @ResponseBody
    public Paging<SpuSimplifyVO> getLatestSpuList(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "1") Integer count
    ) {
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        Page<Spu> Spu = this.spuService.getLatestPagingSpu(pageCounter.getPage(), pageCounter.getCount());
        Paging<SpuSimplifyVO> voList = new PagingDozer(Spu, SpuSimplifyVO.class);
        return voList;
    }
}
