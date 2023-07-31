package board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.dao.BoardDAO;
import board.dto.BoardDTO;
import board.mybatis.BoardMapper;
import reboard.dao.ReBoardDAO;
import reboard.dto.ReBoardDTO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO boardDAO;
	//@RequestMapping("list_board.do")
	//public ModelAndView listBoard()
	//List<BoardDTO> list = boardDAO.listBoard();
	//return new ModelAndView("/list","listBoard",list);
	
	@RequestMapping("/list_board.do")
	public String list_board(HttpServletRequest req) {
		//List<BoardDTO> list = boardDAO.listBoard();
		List<BoardDTO> list = BoardMapper.listBoard();
		req.setAttribute("listBoard",list);
		return "/list";
	}
	@RequestMapping(value = "/write_board.do",method = RequestMethod.GET)
	//value 로 지정해주고 메소드 방식을 알려준다.
	public String writeFormBoard() {
		return "/writeForm";
	}
	@RequestMapping(value = "/write_board.do", method = RequestMethod.POST)
	public String writeProBoard(HttpServletRequest req,BoardDTO dto) {
		dto.setIp(req.getRemoteAddr());//ip 값은 넘어오지않으므로 지정해줌
		//int res = boardDAO.insertBoard(dto);
		int res = BoardMapper.insertBoard(dto);
		if(res>0) {
			req.setAttribute("msg", "게시글등록 성공! 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_board.do");
		}
		else {
			req.setAttribute("msg", "게시글등록 실패 !");
			req.setAttribute("url", "write_board.do");
		}
		return "forward:message.jsp";
	}
	
	@RequestMapping("/content_board.do")
	public ModelAndView contentBoard(@RequestParam(required=false)int num) {//값이 있다면 num 을 넣고 없다면 그냥 넘어감
		//BoardDTO dto = boardDAO.getBoard(num, "content");
		BoardDTO dto = BoardMapper.getBoard(num, "content");
		return new ModelAndView("/content","dto",dto);
	}
	@RequestMapping(value = "/delete_board.do",method = RequestMethod.GET)
	//value 로 지정해주고 메소드 방식을 알려준다.
	public String deleteFormBoard(HttpServletRequest req,int num) {
		req.setAttribute("num", num);
		return "/deleteForm";
	}
	@RequestMapping(value = "/delete_board.do", method = RequestMethod.POST)
	public String deleteProBoard(HttpServletRequest req,@RequestParam Map<String,String> params) {
//		int res = boardDAO.deleteBoard(Integer.parseInt(params.get("num")), params.get("passwd"));
		int res = BoardMapper.deleteBoard(Integer.parseInt(params.get("num")),params.get("passwd"));
		if(res>0) {
			req.setAttribute("msg", "게시글삭제 성공! 목록페이지로 이동합니다.");
			req.setAttribute("url", "list_board.do");
		}
		else if (res<0) {
			req.setAttribute("msg", "게시글삭제 실패 !비밀번호가 틀렸습니다.");
			req.setAttribute("url", "delete_board.do?num="+params.get("num"));
		}
		else {
			req.setAttribute("msg", "게시글 삭제 실패 ! ");
			req.setAttribute("url", "content_board.do?num="+params.get("num"));
		}
		return "forward:message.jsp";
	}
	@RequestMapping(value = "/update_board.do", method = RequestMethod.GET)
	public String updateFormBoard(HttpServletRequest req,int num) {
		BoardDTO dto = BoardMapper.getBoard(num, "update");
		req.setAttribute("dto", dto);
		return "/updateForm";
		
	}
	@RequestMapping(value = "/update_board.do" ,method = RequestMethod.POST)
	public String updateProBoard(HttpServletRequest req,@ModelAttribute BoardDTO dto,BindingResult result)
	//bindingException 예외처리 매개변수로 받아옴 
	{
		if(result.hasErrors()) {//hasErrors >> 에러가 발생하면 true 반환
			dto.setNum(0);
		}
//		int res = boardDAO.updateBoard(dto);
		
		int res = BoardMapper.updateBoard(dto);
		if(res>0) {
			req.setAttribute("msg", "게시글 수정 성공!");
			req.setAttribute("url", "list_board.do");
		}
		else if(res<0) {
			req.setAttribute("msg", "게시글 수정 실패! 비밀번호를 확인하세요.");
			req.setAttribute("url", "list_board.do");
		}
		else {
			req.setAttribute("msg", "게시글수정 실패");
			req.setAttribute("url", "update_board.do?num="+dto.getNum());
		}
		return "forward:message.jsp";
	}
	
}
