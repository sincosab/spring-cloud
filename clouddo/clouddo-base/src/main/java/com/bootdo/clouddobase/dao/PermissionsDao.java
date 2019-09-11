package com.bootdo.clouddobase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bootdo.clouddocommon.dto.MenuDO;

@Mapper
public interface PermissionsDao {

	List<MenuDO> list(@Param("userName")String userName);

}
