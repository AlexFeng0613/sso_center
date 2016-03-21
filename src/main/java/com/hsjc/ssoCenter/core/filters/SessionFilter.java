package com.hsjc.ssoCenter.core.filters;

import com.hsjc.ssoCenter.core.domain.UserMain;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author : zga
 * @date : 2016-03-21
 */
public class SessionFilter extends AccessControlFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            return false;
        }
        HttpSession session = ((HttpServletRequest)request).getSession();
        UserMain current_user = (UserMain) session.getAttribute("user");
        //判断session是否失效，若失效刷新之
        if(current_user == null ){
            System.out.println("zga:session invlid");
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }
}
