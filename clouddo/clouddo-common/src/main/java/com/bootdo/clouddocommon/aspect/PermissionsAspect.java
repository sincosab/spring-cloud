package com.bootdo.clouddocommon.aspect;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bootdo.clouddocommon.dto.MenuDO;
import com.bootdo.clouddocommon.service.PermissionsRpcService;

@Aspect
@Component
public class PermissionsAspect {
	private static final Logger logger = LoggerFactory.getLogger(PermissionsAspect.class);
	@Autowired
	PermissionsRpcService permissionsRpcService;
	@Pointcut("execution( * com.bootdo..controller.*.*(..))") // 两个..代表所有子目录，最后括号里的两个..代表所有参数
	public void permissionsPointCut() {
	}

	@Before("permissionsPointCut()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String url = request.getRequestURI().toString();
		String userName = request.getUserPrincipal().getName();
		logger.info("权限请求地址 : " + url);
		Set<String> perms = new HashSet<String>();
		Set<String> ignoredPerms = new HashSet<String>();
		ignoredPerms.add("/user/currentUser");
		ignoredPerms.add("/router");
		ignoredPerms.add("/menu");
		List<MenuDO> l = permissionsRpcService.list(userName);
		int m = -1;
		for (int i = 0; i < l.size(); i++) {
			String url1 = l.get(i).getUrl();
			m = url.indexOf(url1);
			if (m == 0) {
				break;
			}
		}
		perms.addAll(ignoredPerms);
		if (!perms.contains(url)) {
			if (m == -1) {
			   throw new RuntimeException("请求地址: " + url + " 无权限 ");
			}
		}
	}
}
