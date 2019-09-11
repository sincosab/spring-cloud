package com.bootdo.clouddoadmin.controller;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootdo.clouddocommon.annotation.Log;
import com.bootdo.clouddocommon.intercepter.FeignIntercepter;
import com.bootdo.clouddocommon.utils.R;

import feign.Headers;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin", configuration = FeignIntercepter.class)
public interface IUserControlle {
	@Log("获取用户列表")
    @GetMapping()
    R listByPage(@RequestParam Map<String, Object> params) ;
}
