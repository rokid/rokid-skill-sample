package com.rokid.skill.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import com.rokid.skill.constants.ReqAttrName;

/**
 * 过滤器，允许跨域请求以及记录请求服务名称和请求开始时间
 * 
 * @author Bassam
 *
 */
public class RequestParamFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(RequestParamFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long beginTime = System.currentTimeMillis();
		request.setAttribute(ReqAttrName.REQ_BEGIN_TIME, beginTime);// 设置请求时间
		String serviceName = request.getRequestURI().substring(request.getContextPath().length());
		if (StringUtils.isBlank(serviceName)) {
			if (logger.isErrorEnabled()) {
				logger.error("serviceNameError:serviceName is blank");
			}
			throw new ServletException("ServiceNameError");
		}
		String method = request.getMethod();
		if (!HttpMethod.POST.name().equals(method)&&!HttpMethod.PUT.name().equals(method)
				&&!HttpMethod.GET.name().equals(method)&&!HttpMethod.DELETE.name().equals(method)) {
			if (logger.isErrorEnabled()) {
				logger.error("MethodNotAllowed:method="+method);
			}
			throw new ServletException("MethodNotAllowed");
		}
		request.setAttribute(ReqAttrName.SERVICE_NAME, serviceName);// 设置服务名称
		/** 允许跨域qi **/
		response.setHeader("Access-Control-Allow-Origin", "*");// 允许跨域
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
		response.setHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,appcode,appversion,languagetype,devicetype,devicemodel,sys,sysversion,deviceidentifier,service,sign,noncestr,timestamp,action,page,pagesize,token");
		filterChain.doFilter(request, response);
	}

}
