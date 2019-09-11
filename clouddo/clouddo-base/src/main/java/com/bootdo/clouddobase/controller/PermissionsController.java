package com.bootdo.clouddobase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.clouddobase.service.PermissionsService;
import com.bootdo.clouddocommon.dto.LogDO;
import com.bootdo.clouddocommon.dto.MenuDO;

@RequestMapping("/permissions")
@RestController
public class PermissionsController {
	@Autowired
	PermissionsService permissionsService;
	@PostMapping("/list")
	List<MenuDO> list(@RequestBody String userName) {   
		List<MenuDO> l=permissionsService.queryList(userName);
        return l;
    }

}
