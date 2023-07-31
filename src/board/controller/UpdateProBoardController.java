package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class UpdateProBoardController extends AbstractCommandController {
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	protected ModelAndView handle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		// TODO Auto-generated method stub
		BoardDTO dto = (BoardDTO) arg2;
		dto.setIp(arg0.getRemoteAddr());
		int res = boardDAO.updateBoard(dto);
		ModelAndView mav = new ModelAndView("forward:message.jsp");
		if(res>0) {
			mav.addObject("msg","게시글 수정 성공! 목록으로 이동합니다.");
			mav.addObject("url","list_board.do");
		}
		else  {
			mav.addObject("msg","게시글 수정 오류발생! 비밀번호를 확인하세요.");
			mav.addObject("url","list_board.do");
		}
		return mav;
	}

}
