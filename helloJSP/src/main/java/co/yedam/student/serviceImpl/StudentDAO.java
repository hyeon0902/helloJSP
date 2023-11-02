package co.yedam.student.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DataSource;
import co.yedam.student.service.StudentVO;

public class StudentDAO {
	DataSource ds = DataSource.getInstance();
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	void close() {
			try {
				if(conn != null)
					conn.close();
				if(psmt != null)
				psmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	// 추가: insert
	public int insert(StudentVO vo) {
		String sql = "INSERT INTO STUDENT(STUDENT_ID, STUDENT_NAME, STUDENT_DEPT, "
				+ "STUDENT_PASSWORD,STUDENT_BIRTHDAY) "
				+ "VALUES (?,?,?,?,?)";
		conn = ds.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudentId());
			psmt.setString(2, vo.getStudentName());
			psmt.setString(3, vo.getStudentPassword());
			psmt.setString(4, vo.getStudentDept());
			psmt.setString(5, sdf.format(vo.getStudentBirthday()));
			int r = psmt.executeUpdate();
			return r;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return 0; // 처리가 된 건수가 없음 : 에러.
	}

	// 수정: update
	public int update(StudentVO vo) {
		int cnt = 0;
		String sql = "UPDATE STUDENT SET STUDENT_NAME = ?, STUDENT_PASSWORD = ?, STUDENT_DEPT = ?, STUDENT_BIRTHDAY = ? "
				   + "WHERE STUDENT_ID = ?";
		conn = ds.getConnection();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(5, vo.getStudentId());
			psmt.setString(1, vo.getStudentName());
			psmt.setString(2, vo.getStudentPassword());
			psmt.setString(3, vo.getStudentDept());
			psmt.setString(4, sdf.format(vo.getStudentBirthday()));
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
		
	// 삭제: delete
	public int delete(String sid) {
		int cnt = 0;
		String sql = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
		conn = ds.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sid);
			cnt = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
	// 목록: list
	
	public List<StudentVO> list() {
		List<StudentVO> students = new ArrayList<>();

		StudentVO vo;

		String sql = "select * from student";

		conn = ds.getConnection();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new StudentVO();
				vo.setStudentId(rs.getString("student_id"));
				vo.setStudentName(rs.getString("student_name"));
				vo.setStudentPassword(rs.getString("student_Password"));
				vo.setStudentDept(rs.getString("student_dept"));
				vo.setStudentBirthday(rs.getDate("student_Birthday"));
				students.add(vo);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return students;
	}
	
	// 조회: select
	public StudentVO select(String sid) {
		
		StudentVO vo = new StudentVO();
		
		String sql = "SELECT * FROM STUDENT WHERE STUDENT_ID = ? ";
		
		conn = ds.getConnection();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setStudentId(rs.getString("student_id"));
				vo.setStudentName(rs.getString("student_name"));
				vo.setStudentPassword(rs.getString("student_password"));
				vo.setStudentDept(rs.getString("student_dept"));
				vo.setStudentBirthday(rs.getDate("student_Birthday"));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return vo;

	}
}
