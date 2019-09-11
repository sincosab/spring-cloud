package com.bootdo.clouddobase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootdo.clouddocommon.dto.MenuDO;

@Service
public interface PermissionsService {

	List<MenuDO> queryList(String userName);

}
