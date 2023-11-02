package co.yedam.common;

import java.text.SimpleDateFormat;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;


public class MainExe {
	public static void main(String[] args) {
		// 학생 아이디, 이름, 비밀번호, 학과, 생일 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 2012-03-05 Nov-23
		// 
		//
		
//		
		StudentVO vo = new StudentVO();
//		vo.setStudentId("newbie");
//		vo.setStudentName("신입생");
//		vo.setStudentPassword("1234");
//		vo.setStudentDept("영문학과");
//		try {
//			vo.setStudentBirthday(sdf.parse("2001-01-01"));			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		StudentService svc = new StudentServiceImpl();
//		if(svc.addStudent(vo)) {
//			System.out.println("정상등록.");
//		}
//		else {
//			System.out.println("실패.");
//		}
//	
	
		vo.setStudentId("coco");
		vo.setStudentName("졸업생");
		vo.setStudentPassword("1234");
		vo.setStudentDept("교육학과");
		try {
			vo.setStudentBirthday(sdf.parse("2011-01-01"));			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(svc.editStudent(vo)) {
			System.out.println("정상등록.");
		}
		else {
			System.out.println("실패.");
		}
	}
}
	
	
