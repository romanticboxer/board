package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class DeleteProBoardController implements Controller{
	private BoardDAO boardDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int num = Integer.parseInt(req.getParameter("num"));
		String passwd = req.getParameter("passwd");
		int res = boardDAO.deleteBoard(num, passwd);
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if(res>0) {
			mav.addObject("msg","게시글 삭제 성공! 목록으로 이동합니다.");
			mav.addObject("url","list_board.do");
		}
		else  {
			mav.addObject("msg","게시글 삭제 오류발생! 목록으로 이동합니다.");
			mav.addObject("url","list_board.do");
		}
		return mav;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

}
