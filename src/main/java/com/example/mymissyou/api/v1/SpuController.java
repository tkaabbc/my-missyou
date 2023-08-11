package com.example.mymissyou.api.v1;

import com.example.mymissyou.exception.http.NotFoundException;
import com.example.mymissyou.model.Spu;
import com.example.mymissyou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
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
    @GetMapping("/latest")
    @ResponseBody
    public List<Spu> getAll() {
        List<Spu> spuList = spuService.getAll();
        System.out.println(spuList);
        return spuList;
    }
}
