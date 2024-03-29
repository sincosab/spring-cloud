package com.bootdo.clouddoadmin.controller;

import com.bootdo.clouddoadmin.domain.RoleDO;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.service.RoleService;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bootdo
 * 角色
 */
@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

   // @PreAuthorize("hasAuthority('admin:role:role')")
    @GetMapping("/getRole")
    PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<RoleDO> roleDOS = roleService.list(query);
        int total = roleService.count(query);
        PageUtils pageUtil = new PageUtils(roleDOS, total);
        return pageUtil;
    }

    @GetMapping("/userId/{userId}")
    List<Long> roleIdByUserId(@PathVariable Long userId){
        return roleService.RoleIdsByUserId(userId);
    }

    @PostMapping("/addRole")
    R save(@RequestBody RoleDO roleDO){
        if(roleService.save(roleDO)>0){
            return R.ok();
        }
        return R.error();
    }

    @PutMapping("/editRole")
    R update(@RequestBody RoleDO roleDO){
        if(roleService.update(roleDO)>0){
            return R.ok();
        }
        return R.error();
    }

}
