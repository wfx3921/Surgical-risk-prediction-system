package com.ouc.riskprediction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.controller.UserController;
import com.ouc.riskprediction.entity.PatientData;
import com.ouc.riskprediction.entity.User;
import com.ouc.riskprediction.mapper.UserMapper;
import com.ouc.riskprediction.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    public R<Page> getListByFields(int page, int pageSize, UserController.SearchForm searchForm) {

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (searchForm.getLoginName() != null&&searchForm.getLoginName() !="") {
            userLambdaQueryWrapper.eq(User::getLoginName, searchForm.getLoginName());
        }
        if (searchForm.getPhoneNum() != null&&searchForm.getPhoneNum() != "") {
            userLambdaQueryWrapper.eq(User::getPhoneNum, searchForm.getPhoneNum());
        }
        if (searchForm.getStatus() != null &&searchForm.getStatus() !="") {
            userLambdaQueryWrapper.eq(User::getStatus, searchForm.getStatus());
        }
        if (searchForm.getCreateTimeStart() != null&&searchForm.getCreateTimeEnd()!=null) {
            userLambdaQueryWrapper.between(User::getCreateTime, searchForm.getCreateTimeStart(),searchForm.getCreateTimeEnd());
        }
        System.out.println(searchForm.getUserType()+"-----------------------");
        if(searchForm.getUserType()!=null&&searchForm.getUserType()!=""){
            if("sup_admin".equals(searchForm.getUserType())) {
                System.out.println("11111111");
                userLambdaQueryWrapper.eq(User::getUserType,"admin");
            }
            else if("admin".equals(searchForm.getUserType())) {
                System.out.println("22222222");
                userLambdaQueryWrapper.eq(User::getUserType,"normal");
            }
        }
        if(searchForm.getDeptName()!=null&&searchForm.getDeptName()!=""){
            if("sup_admin".equals(searchForm.getUserType())) {
                System.out.println("33333333");
                userLambdaQueryWrapper.eq(User::getDeptName,"");
            }
            else if("admin".equals(searchForm.getUserType())) {
                System.out.println("44444444");
                userLambdaQueryWrapper.eq(User::getDeptName,searchForm.getDeptName());
            }
        }
        userLambdaQueryWrapper.eq(User::getDeleteFlag,0);
        Page<User> pageInfo = new Page<>(page, pageSize);
        this.page(pageInfo, userLambdaQueryWrapper);
        return R.success(pageInfo);
    }

}
