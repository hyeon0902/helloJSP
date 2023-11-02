package co.yedam.student.service;

import java.util.List;

public interface StudentService {
	// 등록, 수정, 삭제, 목록, 단건조회
	public boolean addStudent(StudentVO vo); // 등록
	public boolean editStudent(StudentVO vo); // 수정
	public boolean removeStudent(String sid); // 삭제
	public List<StudentVO> listStudent(); // 목록
	public StudentVO getStudent(String sid); // 단건조회
}
