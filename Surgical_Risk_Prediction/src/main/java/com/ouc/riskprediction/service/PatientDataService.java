package com.ouc.riskprediction.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.controller.PatientDataController;
import com.ouc.riskprediction.entity.PatientData;

import javax.persistence.criteria.CriteriaBuilder;

public interface PatientDataService extends IService<PatientData> {

    R<org.springframework.data.domain.Page> page(int page, int pageSize);
    R<org.springframework.data.domain.Page> getListByFields(int page, int pageSize, PatientDataController.SearchForm patientData);
    Integer insert(PatientData patientData);
    PatientData getById(String id);
    boolean updateById(PatientData patientData);
}
