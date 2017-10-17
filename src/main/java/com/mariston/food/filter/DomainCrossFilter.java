package com.mariston.food.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 允许跨域的过滤器
 * 名称：DomainCrossFilter.java<br>
 * 描述：<br>
 * 类型：JAVA<br>
 * 最近修改时间: 2017年5月5日 上午11:16:04 <br>
 * @since  2017年5月5日
 * @authour ChenRenhao
 */
//改为在web.xml中配置
//@WebFilter(urlPatterns="/*")
public class DomainCrossFilter implements Filter {
	private static Logger logger=LoggerFactory.getLogger(DomainCrossFilter.class);
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
//		logger.info("DomainCrossFilter in----------过滤器----");
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
//		System.out.println("coross----------");
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Max-Age", "1000");
		response.addHeader("Access-Control-Allow-Headers", "*");
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("DomainCrossFilter------init---过滤器初始化");
	}



}
