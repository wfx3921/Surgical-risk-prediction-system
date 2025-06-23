package com.ouc.riskprediction.controller;


import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.entity.Syndrome;
import com.ouc.riskprediction.service.SyndromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Syndrome")
public class SyndromeController {

    @Autowired
    private SyndromeService syndromeService;

    @Autowired
    private MongoTemplate template;
    @PostMapping("/save")
    public R<String> saveSyndrome(@RequestBody Syndrome syndrome){
        Syndrome newsyndrome=syndromeService.getSyndromeById(syndrome.getId());
        if(newsyndrome==null){
            Integer ok=syndromeService.insertSyndrome(syndrome);
            if(ok==1){
                return R.success("插入成功");
            }else {
                return R.error("插入失败");
            }
        }
        else {
            Integer ok=syndromeService.updateSyndromeById(syndrome);
            if(ok==1){
                return R.success("修改成功");
            }else {
                return R.error("修改失败");
            }
        }
    }
    @GetMapping("/getSyndromeList")
    public R<List<Syndrome>> getSyndromeList(){
        List<Syndrome> syndromeList=template.findAll(Syndrome.class);
        if(syndromeList!=null){
            return R.success(syndromeList);
        }else return R.error("查找失败");
    }

    @GetMapping("/getSyndromeById")
    public R<Syndrome> getSyndromeById(@RequestParam String id){
        System.out.println("getSyndromeById");
        Syndrome syndrome=syndromeService.getSyndromeById(id);
        if(syndrome!=null)
            return R.success(syndrome);
        else return R.error("查找失败");
    }
    @PostMapping("/updateSyndrome")
    public R<String> updateSyndrome(@RequestBody Syndrome syndrome){
        Integer ok=syndromeService.updateSyndromeById(syndrome);
        if(ok==1){
            return R.success("修改成功");
        }else {
            return R.error("修改失败");
        }
    }
}
