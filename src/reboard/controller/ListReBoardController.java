package reboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import reboard.dao.ReBoardDAO;
import reboard.dto.ReBoardDTO;
public class ListReBoardController implements Controller {
	private ReBoardDAO reboardDAO;
	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse arg1) throws Exception {
		int pageSize = 5;
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int count = reboardDAO.getCount();// 게시글 총 글수를 받아오는 메소드
		int endRow = startRow + pageSize - 1;
		if (endRow > count)endRow = count;
		int pageCount = count/pageSize+(count%pageSize ==0 ?0:1);
		int pageBlock = 3;
		int startPage = (currentPage-1)/pageBlock*pageBlock+1 ;
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount) endPage = pageCount;
		List<ReBoardDTO> list = reboardDAO.listReBoard(startRow,endRow);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/list");
		mav.addObject("listBoard",list);
		mav.addObject("startPage",startPage);
		mav.addObject("endPage",endPage);
		mav.addObject("pageBlock",pageBlock);
		mav.addObject("pageCount",pageCount);
		return mav;
	}
	public void setReboardDAO(ReBoardDAO reboardDAO) {
		this.reboardDAO = reboardDAO;
	}
}
