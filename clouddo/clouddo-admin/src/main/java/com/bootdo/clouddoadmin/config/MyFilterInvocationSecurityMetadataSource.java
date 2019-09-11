package com.bootdo.clouddoadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.bootdo.clouddoadmin.dao.MenuDao;
import com.bootdo.clouddoadmin.domain.MenuDO;
import com.bootdo.clouddoadmin.domain.Permission;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private MenuDao permissionMapper;

	private HashMap<String, Collection<ConfigAttribute>> map = null;

	/**
	 * 加载权限表中所有权限
	 */
	public void loadResourceDefine() {
		map = new HashMap<String, Collection<ConfigAttribute>>();

		List<MenuDO> permissions = permissionMapper.list(new HashMap<>(16));
		for (MenuDO permission : permissions) {
			ConfigAttribute cfg = new SecurityConfig(permission.getName());
			List<ConfigAttribute> list = new ArrayList<>();
			list.add(cfg);
			map.put(permission.getUrl(), list);
		}

	}

	/**
	 * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法， 用来判定用户 是否有此权限。如果不在权限表中则放行。
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null) {
			loadResourceDefine();
		}
		// object 中包含用户请求的request的信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		for (Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
			String url = entry.getKey();
			if (!StringUtils.isNullOrEmpty(url)) {
				if (new AntPathRequestMatcher(url).matches(request)) {
					return map.get(url);
				}
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
