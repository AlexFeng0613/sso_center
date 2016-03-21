package com.hsjc.ssoCenter.core.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;

/**
 * @author : zga
 * @date : 2016-1-18
 *
 * 异常处理类
 *
 */
@ControllerAdvice
public class ExceptionAdvice {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws IOException, ParseException {
		StringWriter writer = new StringWriter();
		PrintWriter pw = new PrintWriter(writer);
		e.printStackTrace(pw);
		String exceptionTrace =  writer.toString();
		pw.close();
		writer.close();

		System.out.println(exceptionTrace);

		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/user/login.html"));

		mav.addObject("errorMessage", e.getMessage());
		mav.addObject("success", "false");
		return mav;
	}

}
