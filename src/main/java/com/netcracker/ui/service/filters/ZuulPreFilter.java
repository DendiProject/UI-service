/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.filters;

import com.netcracker.ui.service.beans.factory.BeansFactory;
import com.netcracker.ui.service.exception.ExceptionHandler;
import com.netcracker.ui.service.security.SecurityTokenHandler;
import com.netcracker.ui.service.exception.beans.factory.NotFoundBean;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;
import org.springframework.stereotype.Component;

/**
 *
 * @author ArtemShevelyukhin
 */
@Component
public class ZuulPreFilter extends ZuulFilter {

  SecurityTokenHandler tokenHandler;
  BeansFactory<SecurityTokenHandler> bfTK = BeansFactory.getInstance();

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1; // run before PreDecoration
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    try {
      tokenHandler = bfTK.getBean(SecurityTokenHandler.class);
    } catch (Exception exception) {
      ExceptionHandler.getInstance().runExceptionhandling(exception);
    }
    RequestContext ctx = RequestContext.getCurrentContext();
    ctx.addZuulRequestHeader("secureToken", tokenHandler.getToken());
    return null;
  }

}
