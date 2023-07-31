package reboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import reboard.dao.ReBoardDAO;
import reboard.dto.ReBoardDTO;

public class WriteProBoardController extends AbstractCommandController  {
	private ReBoardDAO reboardDAO;
	public void setReboardDAO(ReBoardDAO reboardDAO) {
		this.reboardDAO = reboardDAO;
	}
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse arg1, Object obj, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		ReBoardDTO dto = (ReBoardDTO) obj;
		dto.setIp(req.getRemoteAddr());
		int res = reboardDAO.writeReBoard(dto);
		if(res>0) {
			mav.addObject("msg","글 등록 성공 ! 목록으로 이동합니다.");
			mav.addObject("url" , "list_reboard.re");
		}
		else {
			mav.addObject("msg", "글등록시 오류발생");
			mav.addObject("url", "list_reboard.re");
		}
		return mav;
	}

}
