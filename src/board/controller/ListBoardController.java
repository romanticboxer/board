package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class ListBoardController implements Controller{
	private BoardDAO boardDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<BoardDTO> list = boardDAO.listBoard();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/list");
		mav.addObject("listBoard",list);
		return mav;
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
}
/* construct
 * -ModelAndView() - default
 * -ModelAndView(String)
 * -ModelAndView(String,Object(Key),Object(value)) 하나의 값 전달
 * method
 * -setViewName(String)
 * -addObject(Object(key),Object(value)) 여러개의 값 전달
 */
