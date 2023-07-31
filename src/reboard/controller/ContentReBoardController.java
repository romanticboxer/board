package reboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import reboard.dao.ReBoardDAO;
import reboard.dto.ReBoardDTO;

public class ContentReBoardController implements Controller {
	private ReBoardDAO	reboardDAO;

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		int num = ServletRequestUtils.getIntParameter(req, "num");
		ReBoardDTO dto = reboardDAO.getBoard(num, "content");
		ModelAndView mav = new ModelAndView("/content","dto",dto);
		return mav;
	}
	public void setReboardDAO(ReBoardDAO reboardDAO) {
		this.reboardDAO = reboardDAO;
	}
	
}
