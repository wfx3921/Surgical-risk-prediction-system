package com.ouc.riskprediction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ouc.riskprediction.entity.Syndrome;

public interface SyndromeService extends IService<Syndrome> {
    Integer insertSyndrome(Syndrome syndrome);
    Syndrome getSyndromeById(String id);
    Integer updateSyndromeById(Syndrome syndrome);
}
