package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class WriteProBoardAbstractCommandController extends AbstractCommandController {
	private BoardDAO boardDAO;
	
	@Override
	protected ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object obj, BindException result)
			throws Exception {
		// TODO Auto-generated method stub
		BoardDTO dto = (BoardDTO) obj;
		dto.setIp(req.getRemoteAddr());
		int res = boardDAO.insertBoard(dto);
		
		return new ModelAndView("redirect:list_board.do");
	}

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

}
