package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class UpdateFormBoardController implements Controller{
	private BoardDAO boardDAO;
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int num =Integer.parseInt(req.getParameter("num"));
		BoardDTO dto = boardDAO.getBoard(num, "update");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/updateForm");
		mav.addObject("dto",dto);
		return mav;
	}
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
}
