package com.bootdo.clouddocommon.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.bootdo.clouddocommon.dto.MenuDO;


@FeignClient(name = "api-base")
public interface PermissionsRpcService {	
	@PostMapping("permissions/list")
	List<MenuDO> list(String userName);
}
