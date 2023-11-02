package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	// 싱글톤 클래스 DAO 객체
	private static DataSource dataSource = new DataSource();
	
	private DataSource() {};
	// 외부에서 객체생성 막기

		
	//}
	//오라클 드라이버 이름
	private static String driver = "oracle.jdbc.OracleDriver";
	//DB 접속정보, jdbc:oracle:thin:@[서버IP]:[포트]:[SID]
	private static String url = "jdbc:oracle:thin:@192.168.0.39:1521:xe";
	// UserName 정보 입력
	private static String user = "hr";
	// UserPassword 정보 입력
	private static String password = "1234";
	
	private static Connection conn;
	
	public static DataSource getInstance() {
		return dataSource;
	}
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println("DB연결");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
		

}
