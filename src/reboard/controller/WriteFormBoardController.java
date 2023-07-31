package reboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import reboard.dto.ReBoardDTO;

public class WriteFormBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int num=0,re_step=0,re_level=0; // 새글일 경우 0
		String snum = req.getParameter("num");
		ReBoardDTO dto = new ReBoardDTO();
		if(snum !=null && !snum.trim().equals("")){ // 답글쓰기에서 넘어왔을경우
			num = Integer.parseInt(snum);
			re_step = Integer.parseInt(req.getParameter("re_step"));
			re_level = Integer.parseInt(req.getParameter("re_level"));
			dto.setNum(num);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("re_step",re_step);
		mav.addObject("re_level",re_level);
		mav.addObject("num",dto.getNum());
		mav.setViewName("/writeForm");
		return mav;
	}

}
