package com.ouc.riskprediction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mongodb.client.result.UpdateResult;
import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.controller.PatientDataController;
import com.ouc.riskprediction.entity.PatientData;
import com.ouc.riskprediction.mapper.PatientDataMapper;
import com.ouc.riskprediction.service.PatientDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageImpl;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PatientDataServiceImpl extends ServiceImpl<PatientDataMapper, PatientData> implements PatientDataService {


    @Autowired
    private MongoTemplate template;

    @Override
    public R<org.springframework.data.domain.Page> page(int page, int pageSize) {
        // 验证参数合法性，省略...

        // 创建分页请求对象，指定当前页码（page - 1是因为PageRequest是从0开始的），每页大小，以及排序规则
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));

        // 构建查询条件
        Query query = new Query();

        // 执行分页查询
        List<PatientData> content = template.find(query.with(pageable), PatientData.class);

        // 获取总记录数
        long total = template.count(query, PatientData.class);

        // 创建Page对象
        org.springframework.data.domain.Page<PatientData> pageInfo = new PageImpl<>(content, pageable, total);

        return R.success(pageInfo);
    }

    @Override
    public R<org.springframework.data.domain.Page> getListByFields(int page, int pageSize, PatientDataController.SearchForm patientData) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "id"));


        Criteria criteria = new Criteria();
        criteria.and("delete_flag").is(0);

        if (patientData.getHospitalizationNumber() != null && !patientData.getHospitalizationNumber().isEmpty()) {
            criteria.and("hospitalization_number").is(patientData.getHospitalizationNumber());
        }
        if (patientData.getPatientName() != null && !patientData.getPatientName().isEmpty()) {
            criteria.and("patient_name").is(patientData.getPatientName());
        }
        if (patientData.getStaff() != null && !patientData.getStaff().isEmpty()) {
            criteria.and("staff").is(patientData.getStaff());
        }
        if (patientData.getPrimarySurgicalSite() != null && !patientData.getPrimarySurgicalSite().isEmpty()) {
            criteria.and("primary_surgical_site").is(patientData.getPrimarySurgicalSite());
        }
        if (patientData.getPatientGender() != null && !patientData.getPatientGender().isEmpty()) {
            criteria.and("patient_gender").is(patientData.getPatientGender());
        }
        if (patientData.getAlcoholHistory() != null && !patientData.getAlcoholHistory().isEmpty()) {
            criteria.and("alcohol_history").is(patientData.getAlcoholHistory());
        }
        if (patientData.getSmokingHistory() != null && !patientData.getSmokingHistory().isEmpty()) {
            criteria.and("smoking_history").is(patientData.getSmokingHistory());
        }
        if (patientData.getPatientBmi() != null && patientData.getPatientBmi2() != null&&!patientData.getPatientBmi().isEmpty()&&!patientData.getPatientBmi2().isEmpty()) {
            criteria.and("patient_bmi").gte(patientData.getPatientBmi()).lte(patientData.getPatientBmi2());
        }
        if (patientData.getCoagulationDDimer() != null && patientData.getCoagulationDDimer2() != null&&!patientData.getCoagulationDDimer().isEmpty()&&!patientData.getCoagulationDDimer2().isEmpty()) {
            criteria.and("coagulation_d_dimer").gte(patientData.getCoagulationDDimer()).lte(patientData.getCoagulationDDimer2());
        }
        if (patientData.getBloodRoutineHemoglobin() != null && patientData.getBloodRoutineHemoglobin2() != null &&!patientData.getBloodRoutineHemoglobin().isEmpty()&&!patientData.getBloodRoutineHemoglobin2().isEmpty()) {
            criteria.and("blood_routine_hemoglobin").gte(patientData.getBloodRoutineHemoglobin()).lte(patientData.getBloodRoutineHemoglobin2());
        }

        if (patientData.getLiverFunctionDirectBilirubin() != null && patientData.getLiverFunctionDirectBilirubin2() != null&&!patientData.getLiverFunctionDirectBilirubin().isEmpty()&&!patientData.getLiverFunctionDirectBilirubin2().isEmpty()) {
            criteria.and("liver_function_direct_bilirubin").gte(patientData.getLiverFunctionDirectBilirubin()).lte(patientData.getLiverFunctionDirectBilirubin2());
        }
        if (patientData.getCoagulationAntithrombinIii() != null && patientData.getCoagulationAntithrombinIii2() != null&&!patientData.getCoagulationAntithrombinIii().isEmpty()&&!patientData.getCoagulationAntithrombinIii2().isEmpty()) {
            criteria.and("coagulation_antithrombin_iii").gte(patientData.getCoagulationAntithrombinIii()).lte(patientData.getCoagulationAntithrombinIii2());
        }
        if (patientData.getCreator() != null&&!patientData.getCreator().isEmpty()) {
            long creator = Long.parseLong(patientData.getCreator());
            criteria.and("create_by").is(creator);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");



        if (patientData.getStartDate() != null && !patientData.getStartDate().isEmpty() &&
                patientData.getEndDate() != null && !patientData.getEndDate().isEmpty()) {

            // 开始时间：本地日期转UTC（减去8小时）
            LocalDateTime startLocal = LocalDate.parse(patientData.getStartDate(), formatter)
                    .atStartOfDay();


            // 结束时间：本地日期转UTC（减去8小时）
            LocalDateTime endLocal = LocalDate.parse(patientData.getEndDate(), formatter)
                    .atTime(23, 59, 59);


            // 关键点：必须在一个and()调用链中完成
            criteria.and("create_time")
                    .gte(startLocal)
                    .lte(endLocal);

        } else if (patientData.getStartDate() != null && !patientData.getStartDate().isEmpty()) {
            LocalDateTime startUTC = LocalDate.parse(patientData.getStartDate(), formatter)
                    .atStartOfDay()
                    .minusHours(8);
            criteria.and("create_time").gte(startUTC);

        } else if (patientData.getEndDate() != null && !patientData.getEndDate().isEmpty()) {
            LocalDateTime endUTC = LocalDate.parse(patientData.getEndDate(), formatter)
                    .atTime(23, 59, 59)
                    .minusHours(8);
            criteria.and("create_time").lte(endUTC);
        }
        System.out.println("Query Criteria: " + criteria);
        Query query = new Query(criteria);

        long total = template.count(query, PatientData.class);
        System.out.println("total+ "+total);
        List<PatientData> content = template.find(query.with(pageable), PatientData.class);
        for(PatientData patientData1 : content) {
            System.out.println(
                    patientData1.getCreateTime()
            );
        }

        org.springframework.data.domain.Page<PatientData> pageInfo = new PageImpl<>(content, pageable, total);

        return R.success(pageInfo);
    }

    @Override
    public Integer insert(PatientData patientData){
        template.save(patientData);
        return 1;
    }
    @Override
    public PatientData getById(String id){
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        Query query = new Query(criteria);
        PatientData patientData =template.findById(id,PatientData.class);
        return patientData;

    }
    @Override
    public boolean updateById(PatientData patientData){
        Query query=new Query(Criteria.where("id").is(patientData.getId()));
        Update update=new Update().set("delete_flag",1);
        UpdateResult result=template.updateFirst(query,update,PatientData.class);
        if(result!=null){
            return true;
        }else return false;

    }
}
