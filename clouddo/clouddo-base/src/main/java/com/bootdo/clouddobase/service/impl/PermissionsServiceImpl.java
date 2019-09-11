package com.bootdo.clouddobase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.clouddobase.dao.PermissionsDao;
import com.bootdo.clouddobase.service.PermissionsService;
import com.bootdo.clouddocommon.dto.MenuDO;
import com.bootdo.clouddocommon.dto.MenuDTO;

@Service
public class PermissionsServiceImpl implements PermissionsService {
	@Autowired
	PermissionsDao permissionsMapper;

	@Override
	public List<MenuDO> queryList(String userName) {
		List<MenuDO> m = permissionsMapper.list(userName);
		return m;
	}


}
