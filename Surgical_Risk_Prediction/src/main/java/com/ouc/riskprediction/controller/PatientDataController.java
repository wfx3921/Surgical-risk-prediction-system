package com.ouc.riskprediction.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.entity.OutputData;
import com.ouc.riskprediction.entity.PatientData;
import com.ouc.riskprediction.mapper.OutputDataMapper;
import com.ouc.riskprediction.mapper.PatientDataMapper;
import com.ouc.riskprediction.service.OutputDataService;
import com.ouc.riskprediction.service.PatientDataService;
import lombok.Data;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/patientdata")
public class PatientDataController {


    @Autowired
    private PatientDataService patientDataService;


    @Autowired
    private OutputDataService outputDataService;

    @Autowired
    private PatientDataMapper patientDataMapper;


    @Autowired
    private OutputDataMapper outputDataMapper;


    /**
     * 查看历史评估记录列表
     */
    @GetMapping("/page")
    public R<org.springframework.data.domain.Page> page(int page, int pageSize){
        System.out.println("page:  "+page+"  "+pageSize);
        R<org.springframework.data.domain.Page> pageInfo=patientDataService.page(page,pageSize);
        return pageInfo;
    }
    @Data
    public static class SearchForm{
        String hospitalizationNumber=null;
        String patientName=null;
        String primarySurgicalSite;
        String patientGender;
        String alcoholHistory;
        String smokingHistory;
        String patientBmi;
        String patientBmi2;
        String coagulationDDimer;
        String coagulationDDimer2;
        String bloodRoutineHemoglobin;
        String bloodRoutineHemoglobin2;
        String liverFunctionDirectBilirubin;
        String liverFunctionDirectBilirubin2;
        String coagulationAntithrombinIii;
        String coagulationAntithrombinIii2;
        String creator;
        String startDate;  // 开始日期
         String endDate;    // 结束日期
        String staff;

        public String getHospitalizationNumber() {
            return hospitalizationNumber;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getPrimarySurgicalSite() {
            return primarySurgicalSite;
        }

        public String getPatientGender() {
            return patientGender;
        }

        public String getAlcoholHistory() {
            return alcoholHistory;
        }

        public String getSmokingHistory() {
            return smokingHistory;
        }

        public String getPatientBmi() {
            return patientBmi;
        }

        public String getPatientBmi2() {
            return patientBmi2;
        }

        public String getCoagulationDDimer() {
            return coagulationDDimer;
        }

        public String getCoagulationDDimer2() {
            return coagulationDDimer2;
        }

        public String getBloodRoutineHemoglobin() {
            return bloodRoutineHemoglobin;
        }

        public String getBloodRoutineHemoglobin2() {
            return bloodRoutineHemoglobin2;
        }

        public String getLiverFunctionDirectBilirubin() {
            return liverFunctionDirectBilirubin;
        }

        public String getLiverFunctionDirectBilirubin2() {
            return liverFunctionDirectBilirubin2;
        }

        public String getCoagulationAntithrombinIii() {
            return coagulationAntithrombinIii;
        }

        public String getCoagulationAntithrombinIii2() {
            return coagulationAntithrombinIii2;
        }

        public  String getCreator(){
            return  creator;
        }
    }

    @PostMapping("/getlist")
    public R<org.springframework.data.domain.Page> getList(@RequestParam int page, @RequestParam int pageSize, @RequestBody PatientDataController.SearchForm searchForm) {
        // 参数校验（可选）
        if (page <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("分页参数无效");
        }
        System.out.println("getList:  "+page+"  "+pageSize);
        R<org.springframework.data.domain.Page> pageInfo = patientDataService.getListByFields(page,pageSize,searchForm);
        // 打印分页对象的基本信息
        System.out.println("Total Pages: " + pageInfo.getData().getTotalPages());
        System.out.println("Total Elements: " + pageInfo.getData().getTotalElements());
// 使用 Jackson ObjectMapper 将对象转换为 JSON 字符串以便更直观地查看
        ObjectMapper objectMapper = new ObjectMapper();
// 遍历并打印每条记录的详细信息
        List<PatientData> patientData=pageInfo.getData().getContent();
        for (PatientData patient :patientData ) {
            try {
                // 将每个 PatientData 对象转换为 JSON 字符串
                String patientJson = objectMapper.writeValueAsString(patient);
                System.out.println("Patient Data: " + patientJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pageInfo;
    }


    /**
     * 根据id获取历史输出结果
     *
     *
     * url中的patientid必须与函数参数名patientid一样
     *
     *
     * @param patientid
     * @return
     */
    @GetMapping("/getOutputData/{patientid}")
    public R<OutputData> getOneOutputDataById(@PathVariable String patientid){

        OutputData outputData = outputDataService.getById(patientid);
        return R.success(outputData);
    }


    /**
     * 根据id获取历史输入值
     *
     *
     * url中的patientid必须与函数参数名patientid一样
     *
     *
     * @param patientid
     * @return
     */
    @GetMapping("/getPatientData/{patientid}")
    public R<PatientData> getOnePatientDataById(@PathVariable String patientid){
        System.out.println(patientid);

        PatientData patientData = patientDataService.getById(patientid);
        System.out.println(patientData);
        return R.success(patientData);
    }


    /**
     * 根据id删除数据,删除PatientData数据的同时删除OutputData中对应的数据
     *
     *
     * url中的patientid必须与函数参数名patientid一样
     *
     *
     * @param patientid
     * @return
     */
    @DeleteMapping("/delete/{patientid}")
    @Transactional
    public R<String> deleteById(@PathVariable String patientid){

        PatientData patientData =patientDataService.getById(patientid);

        boolean delete=patientDataService.updateById(patientData);
        if(delete==true){
            return R.success("删除成功");
        }
        else return R.error("删除失败");
//        OutputData outputData = outputDataService.getById(patientid);
//        outputData.setDeleteFlag(1);
//        outputDataService.updateById(outputData);

    }
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/last7days-stats")
    public Map<String, Object> getLast7DaysStatistics() {
        // 1. 计算日期范围（东八区时间，不包含今天）
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));

        // 结束日期设置为昨天23:59:59.999（东八区）
        calendar.add(Calendar.DAY_OF_MONTH, -1); // 昨天
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endDate = calendar.getTime();

        // 开始日期设置为7天前的00:00:00（东八区）
        calendar.add(Calendar.DAY_OF_MONTH, -6); // 总共减7天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();

        // 转换为UTC时间用于查询
        Instant startUTC = startDate.toInstant();
        Instant endUTC = endDate.toInstant();

        // 2. 构建MongoDB聚合查询（考虑时区转换）
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("create_time").gte(startUTC).lte(endUTC)),
                Aggregation.project()
                        .and(DateOperators.DateToString.dateOf("create_time")
                                .toString("%Y-%m-%d")
                                .withTimezone(DateOperators.Timezone.valueOf("Asia/Shanghai")))
                        .as("date"),
                Aggregation.group("date").count().as("count"),
                Aggregation.project("count").and("_id").as("date"),
                Aggregation.sort(Sort.Direction.ASC, "date")
        );

        // 3. 执行查询
        AggregationResults<Map> results = mongoTemplate.aggregate(
                aggregation,
                "PatientData",
                Map.class
        );

        // 4. 处理结果并补全缺失日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

        // 准备7天的日期列表（东八区）
        List<String> dateRange = new ArrayList<>();
        calendar.setTime(startDate);
        for (int i = 0; i < 7; i++) {
            dateRange.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // 将查询结果转为Map方便查找
        Map<String, Long> resultMap = new HashMap<>();
        for (Map map : results.getMappedResults()) {
            resultMap.put((String) map.get("date"), ((Number) map.get("count")).longValue());
        }

        // 构建最终结果，确保7天都有数据
        Map<String, Long> finalStats = new LinkedHashMap<>();
        for (String date : dateRange) {
            finalStats.put(date, resultMap.getOrDefault(date, 0L));
        }

        // 6. 返回统一格式
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("message", "success");
        response.put("data", finalStats);
        return response;
    }
}
