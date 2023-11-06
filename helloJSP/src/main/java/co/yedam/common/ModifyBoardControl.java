package co.yedam.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.board.service.BoardService;
import co.yedam.board.service.BoardVO;
import co.yedam.board.serviceImpl.BoardServiceImpl;

public class ModifyBoardControl implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라메터활용 -> 데이터수정 -> 목록이동
		BoardVO vo = new BoardVO();
		BoardService svc = new BoardServiceImpl();
		
		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		System.out.println("bno=" + bno);
		vo.setBoardNo(Integer.parseInt(bno));
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		System.out.println(bno);
		
		if(svc.editBoard(vo)) {
			try {
				resp.sendRedirect("boardList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				resp.sendRedirect("modifyForm.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

	}// end execute
}// end class