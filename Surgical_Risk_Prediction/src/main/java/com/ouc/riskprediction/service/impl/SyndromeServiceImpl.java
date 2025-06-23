package com.ouc.riskprediction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mongodb.client.result.UpdateResult;
import com.ouc.riskprediction.entity.Syndrome;
import com.ouc.riskprediction.mapper.SyndromeMapper;
import com.ouc.riskprediction.service.SyndromeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SyndromeServiceImpl extends ServiceImpl<SyndromeMapper, Syndrome> implements SyndromeService {

    @Autowired
    private MongoTemplate template;

    @Override
    public Integer insertSyndrome(Syndrome syndrome){
        Syndrome newSyndrome= template.save(syndrome);
        if(newSyndrome!=null)
            return 1;
        else return 0;
    }
    @Override
    public Syndrome getSyndromeById(String id){
        Syndrome syndrome=template.findById(id,Syndrome.class);
        return syndrome;
    }
    @Override
    public Integer updateSyndromeById(Syndrome syndrome){
        Query query=new Query(Criteria.where("id").is(syndrome.getId()));
        Update update=new Update().set("if_myocardial_infarct",syndrome.getIfMyocardialInfarct())
                .set("if_respiratory_failure", syndrome.getIfRespiratoryFailure())
                .set("if_brain_related_complications", syndrome.getIfBrainRelatedComplications())
                .set("if_pneumonia",syndrome.getIfPneumonia())
                .set("if_lower_extremity_deep_vein_thrombosis", syndrome.getIfLowerExtremityDeepVeinThrombosis())
                .set("if_anastomotic_fistula",syndrome.getIfAnastomoticFistula())
                .set("if_superficial_incision_infection",syndrome.getIfSuperficialIncisionInfection())
                .set("if_infection_around_organs_within_the_body_cavity",syndrome.getIfInfectionAroundOrgansWithinTheBodyCavity())
                .set("if_unplanned_reoperation",syndrome.getIfUnplannedReoperation())
                .set("if_bleeding",syndrome.getIfBleeding())
                .set("if_intestinal_adhesions_and_obstruction",syndrome.getIfIntestinalAdhesionsAndObstruction())
                .set("if_death",syndrome.getIfDeath())
                .set("no_problem",syndrome.getNoProblem())
                .set("actual_days",syndrome.getActualDays());
        UpdateResult result=template.updateFirst(query,update,Syndrome.class);
        if(result!=null) return 1;
        else return 0;


    }
}
