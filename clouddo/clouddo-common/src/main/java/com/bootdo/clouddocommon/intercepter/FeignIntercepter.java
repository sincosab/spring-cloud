package com.bootdo.clouddocommon.intercepter;

import com.bootdo.clouddocommon.constants.CommonConstants;
import com.bootdo.clouddocommon.context.FilterContextHandler;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignIntercepter implements RequestInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public void apply(RequestTemplate requestTemplate) {
		logger.info("------feign设置token" + Thread.currentThread().getId());
		
		  Request request = requestTemplate.request();
		   String url = request.url();
		   System.out.println("ttttttttttttttttttttt=================="+url);
		 //  if(url.startsWith("/sec/apply"))
			   return;
		
		/*if (FilterContextHandler.getToken() != null) {
			requestTemplate.header(CommonConstants.CONTEXT_TOKEN, FilterContextHandler.getToken());
		} else {
			requestTemplate.header(CommonConstants.CONTEXT_TOKEN, Thread.currentThread().getId()+"");
			//throw new RuntimeException("error ");
		}*/
	}
}