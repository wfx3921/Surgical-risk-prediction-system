package com.ouc.riskprediction.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.controller.PatientDataController;
import com.ouc.riskprediction.entity.OutputData;
import org.bson.types.ObjectId;
import org.hibernate.result.Output;

public interface OutputDataService extends IService<OutputData> {
    OutputData getById(String id);
    OutputData saveOutput(OutputData outputData);
}
