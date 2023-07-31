package reboard.controller;

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

import reboard.dao.ReBoardDAO;
import reboard.dto.ReBoardDTO;
import reboard.mybatis.ReBoardMapper;

@Controller
public class BoardController {
	@Autowired
	private ReBoardDAO reboardDAO;
	@RequestMapping("/list_reboard.re")
	public String list_reboard(HttpServletRequest req) {
		int pageSize = 5;
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int count = ReBoardMapper.getCount();// 게시글 총 글수를 받아오는 메소드
		int endRow = startRow + pageSize - 1;
		if (endRow > count)endRow = count;
		int pageCount = count/pageSize+(count%pageSize ==0 ?0:1);
		int pageBlock = 3;
		int startPage = (currentPage-1)/pageBlock*pageBlock+1 ;
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount) endPage = pageCount;
		//List<ReBoardDTO> list = reboardDAO.listReBoard(startRow, endRow);
		List<ReBoardDTO> list = ReBoardMapper.listBoard(startRow,endRow);
		req.setAttribute("listBoard",list);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageBlock", pageBlock);
		req.setAttribute("pageCount", pageCount);
		return "/list";
	}
	@RequestMapping(value = "/write_board.re",method = RequestMethod.POST)
	public String writeProReBoard(HttpServletRequest req,@ModelAttribute ReBoardDTO dto,BindingResult result) {
		if(result.hasErrors()) {
			dto.setNum(0);
			dto.setRe_level(0);
			dto.setRe_step(0);
		}
		dto.setIp(req.getRemoteAddr());
		int res = ReBoardMapper.writeReBoard(dto);
		if(res>0) {
			req.setAttribute("msg", "게시글등록 성공! 목록으로 이동합니다.");
			req.setAttribute("url", "list_reboard.re");
		}
		else {
			req.setAttribute("msg", "게시글 등록실패!");
			req.setAttribute("url", "list_reboard.re");
		}
		return "forward:message.jsp";
	}
	@RequestMapping(value = "/write_board.re" ,method = RequestMethod.GET)
	public String writeFormReBoard(HttpServletRequest req,@RequestParam Map<String,String> param) {
		req.setAttribute("re_level", param.get("re_level"));
		req.setAttribute("re_step", param.get("re_step"));
		req.setAttribute("num",param.get("num"));
		return "/writeForm";
	}
	@RequestMapping("/content_reboard.re")
	public ModelAndView contentReBoard(@RequestParam(required=false)int num) {
		ReBoardDTO dto = ReBoardMapper.getBoard(num, "content");
		return new ModelAndView("/content","dto",dto);
	}
}
