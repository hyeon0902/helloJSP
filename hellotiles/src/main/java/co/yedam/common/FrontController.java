package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.admin.web.memberListControl;
import co.yedam.board.web.BoardListControl;
//@WebServlet("/FirstServlet.do")
public class FrontController extends HttpServlet{
	
	// init -> service 
	Map<String, Command> map = new HashMap<>();
	@Override
	public void init(ServletConfig config) throws ServletException {

		map.put("/boardList.do", new BoardListControl());
		
		map.put("/memberList.do", new memberListControl());
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청정보의 한글 인코딩 방식
		req.setCharacterEncoding("UTF-8");
		
		resp.setContentType("text/html; charset=utf-8");
		System.out.println("FrontController");
		String uri = req.getRequestURI();  //http://localhost:8080/helloJSP/??.do
		String context = req.getServletContext().getContextPath(); //helloJSP
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command controller = map.get(page);
		controller.execute(req, resp);
		
		
		
//		if(page.equals("/second.do")) {
//			
//		}else if(page.equals("/FirstServlet.do")) {
//			
//		}
	}
	
}
