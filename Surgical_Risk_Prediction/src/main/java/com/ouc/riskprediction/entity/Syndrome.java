package com.ouc.riskprediction.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection="Syndrome")
public class Syndrome {
    private static final long serialVersionUID = 1L;

    private String id;
    private Integer ifMyocardialInfarct;//心肌梗死
    private Integer ifRespiratoryFailure;//呼吸衰竭
    private Integer ifBrainRelatedComplications;//脑类并发症
    private Integer ifPneumonia;//肺炎
    private Integer ifLowerExtremityDeepVeinThrombosis;//下肢深静脉血栓
    private Integer ifAnastomoticFistula;//吻合口瘘
    private Integer ifSuperficialIncisionInfection;//浅表切口感染
    private Integer ifInfectionAroundOrgansWithinTheBodyCavity;//体腔内器官周围感染
    private Integer ifUnplannedReoperation;//非计划再手术
    private Integer ifBleeding;//出血
    private Integer ifIntestinalAdhesionsAndObstruction;//肠粘连和肠梗阻
    private Integer ifDeath;//死亡
    @Field("np_problem")
    private Integer noProblem;//无
    @Field("actual_days")
    private Integer actualDays;

    public Syndrome(String id, Integer ifMyocardialInfarct, Integer ifRespiratoryFailure, Integer ifBrainRelatedComplications, Integer ifPneumonia, Integer ifLowerExtremityDeepVeinThrombosis, Integer ifAnastomoticFistula, Integer ifSuperficialIncisionInfection, Integer ifInfectionAroundOrgansWithinTheBodyCavity, Integer ifUnplannedReoperation, Integer ifBleeding, Integer ifIntestinalAdhesionsAndObstruction, Integer ifDeath, Integer noProblem, Integer actualDays) {
        this.id = id;
        this.ifMyocardialInfarct = ifMyocardialInfarct;
        this.ifRespiratoryFailure = ifRespiratoryFailure;
        this.ifBrainRelatedComplications = ifBrainRelatedComplications;
        this.ifPneumonia = ifPneumonia;
        this.ifLowerExtremityDeepVeinThrombosis = ifLowerExtremityDeepVeinThrombosis;
        this.ifAnastomoticFistula = ifAnastomoticFistula;
        this.ifSuperficialIncisionInfection = ifSuperficialIncisionInfection;
        this.ifInfectionAroundOrgansWithinTheBodyCavity = ifInfectionAroundOrgansWithinTheBodyCavity;
        this.ifUnplannedReoperation = ifUnplannedReoperation;
        this.ifBleeding = ifBleeding;
        this.ifIntestinalAdhesionsAndObstruction = ifIntestinalAdhesionsAndObstruction;
        this.ifDeath = ifDeath;
        this.noProblem = noProblem;
        this.actualDays = actualDays;
    }

    public Syndrome() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIfMyocardialInfarct() {
        return ifMyocardialInfarct;
    }

    public void setIfMyocardialInfarct(Integer ifMyocardialInfarct) {
        this.ifMyocardialInfarct = ifMyocardialInfarct;
    }

    public Integer getIfRespiratoryFailure() {
        return ifRespiratoryFailure;
    }

    public void setIfRespiratoryFailure(Integer ifRespiratoryFailure) {
        this.ifRespiratoryFailure = ifRespiratoryFailure;
    }

    public Integer getIfBrainRelatedComplications() {
        return ifBrainRelatedComplications;
    }

    public void setIfBrainRelatedComplications(Integer ifBrainRelatedComplications) {
        this.ifBrainRelatedComplications = ifBrainRelatedComplications;
    }

    public Integer getIfPneumonia() {
        return ifPneumonia;
    }

    public void setIfPneumonia(Integer ifPneumonia) {
        this.ifPneumonia = ifPneumonia;
    }

    public Integer getIfLowerExtremityDeepVeinThrombosis() {
        return ifLowerExtremityDeepVeinThrombosis;
    }

    public void setIfLowerExtremityDeepVeinThrombosis(Integer ifLowerExtremityDeepVeinThrombosis) {
        this.ifLowerExtremityDeepVeinThrombosis = ifLowerExtremityDeepVeinThrombosis;
    }

    public Integer getIfAnastomoticFistula() {
        return ifAnastomoticFistula;
    }

    public void setIfAnastomoticFistula(Integer ifAnastomoticFistula) {
        this.ifAnastomoticFistula = ifAnastomoticFistula;
    }

    public Integer getIfSuperficialIncisionInfection() {
        return ifSuperficialIncisionInfection;
    }

    public void setIfSuperficialIncisionInfection(Integer ifSuperficialIncisionInfection) {
        this.ifSuperficialIncisionInfection = ifSuperficialIncisionInfection;
    }

    public Integer getIfInfectionAroundOrgansWithinTheBodyCavity() {
        return ifInfectionAroundOrgansWithinTheBodyCavity;
    }

    public void setIfInfectionAroundOrgansWithinTheBodyCavity(Integer ifInfectionAroundOrgansWithinTheBodyCavity) {
        this.ifInfectionAroundOrgansWithinTheBodyCavity = ifInfectionAroundOrgansWithinTheBodyCavity;
    }

    public Integer getIfUnplannedReoperation() {
        return ifUnplannedReoperation;
    }

    public void setIfUnplannedReoperation(Integer ifUnplannedReoperation) {
        this.ifUnplannedReoperation = ifUnplannedReoperation;
    }

    public Integer getIfBleeding() {
        return ifBleeding;
    }

    public void setIfBleeding(Integer ifBleeding) {
        this.ifBleeding = ifBleeding;
    }

    public Integer getIfIntestinalAdhesionsAndObstruction() {
        return ifIntestinalAdhesionsAndObstruction;
    }

    public void setIfIntestinalAdhesionsAndObstruction(Integer ifIntestinalAdhesionsAndObstruction) {
        this.ifIntestinalAdhesionsAndObstruction = ifIntestinalAdhesionsAndObstruction;
    }

    public Integer getIfDeath() {
        return ifDeath;
    }

    public void setIfDeath(Integer ifDeath) {
        this.ifDeath = ifDeath;
    }

    public Integer getNoProblem() {
        return noProblem;
    }

    public void setNoProblem(Integer noProblem) {
        this.noProblem = noProblem;
    }

    public Integer getActualDays() {
        return actualDays;
    }

    public void setActualDays(Integer actualDays) {
        this.actualDays = actualDays;
    }
}
