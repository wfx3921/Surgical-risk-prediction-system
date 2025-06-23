package com.ouc.riskprediction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ouc.riskprediction.entity.OutputData;
import com.ouc.riskprediction.mapper.OutputDataMapper;
import com.ouc.riskprediction.service.OutputDataService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class OutputDataServiceImpl extends ServiceImpl<OutputDataMapper, OutputData> implements OutputDataService {

    @Autowired
    private MongoTemplate template;
    @Override
    public OutputData getById(String id){
        OutputData outputData=template.findById(id,OutputData.class);
        return outputData;
    }
    @Override
    public OutputData saveOutput(OutputData outputData){
        OutputData ok =template.save(outputData);
        return ok;
    }

}
