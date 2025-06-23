package com.ouc.riskprediction.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ouc.riskprediction.common.R;
import com.ouc.riskprediction.controller.UserController;
import com.ouc.riskprediction.entity.PatientData;
import com.ouc.riskprediction.entity.User;

public interface UserService extends IService<User> {

    R<Page> getListByFields(int page, int pageSize, UserController.SearchForm searchform) ;
}
