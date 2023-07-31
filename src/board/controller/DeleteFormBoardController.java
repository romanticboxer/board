package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DeleteFormBoardController implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(req.getParameter("num"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/deleteForm");
		mav.addObject("num",num);
		return mav;
	}
}
