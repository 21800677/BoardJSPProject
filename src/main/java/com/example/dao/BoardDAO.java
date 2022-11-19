package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.vo.BoardVO;
import com.example.util.JDBCUtil;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private final String BOARD_INSERT = "insert into BOARD (category, title, writer, content, photo) values (?,?,?,?,?)";
	private final String BOARD_UPDATE = "update BOARD set category =?, title=?, writer=?, content=?, photo=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD  where seq=?";
	private final String BOARD_GET = "select * from BOARD  where seq=?";
	private final String BOARD_LIST = "select * from BOARD order by seq desc";




	public int insertBoard(BoardVO vo) {
		int result = 0;
		conn = JDBCUtil.getConnection();
		try {

			stmt = conn.prepareStatement(BOARD_INSERT);

			stmt.setString(1, vo.getCategory());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getWriter());
			stmt.setString(4, vo.getContent());
			stmt.setString(5, vo.getPhoto());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getCategory());
			stmt.setString(2, vo.getTitle());
			stmt.setString(3, vo.getWriter());
			stmt.setString(4, vo.getContent());
			stmt.setString(5, vo.getPhoto());
			stmt.setInt(6, vo.getSeq());
			
			
			System.out.println(vo.getCategory() + "-" + vo.getTitle() + "-" + vo.getWriter() + "-" + vo.getContent() + "-" + vo.getSeq());
			stmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public BoardVO getBoard(int seq) {
		BoardVO one = null;
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			if(rs.next()) {
				one = new BoardVO();
				one.setSeq(rs.getInt("seq"));
				one.setCategory(rs.getString("category"));
				one.setTitle(rs.getString("title"));
				one.setWriter(rs.getString("writer"));
				one.setContent(rs.getString("content"));
				one.setPhoto(rs.getString("photo"));
				one.setCnt(rs.getInt("cnt"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}
	
	public List<BoardVO> getBoardList(){
		List<BoardVO> list = null;
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			list = new ArrayList<BoardVO>();
			BoardVO one = new BoardVO();
			while(rs.next()) {
				one = new BoardVO();
				one.setSeq(rs.getInt("seq"));
				one.setCategory(rs.getString("category"));
				one.setTitle(rs.getString("title"));
				one.setWriter(rs.getString("writer"));
				one.setContent(rs.getString("content"));
				one.setPhoto(rs.getString("photo"));
				one.setRegdate(rs.getDate("regdate"));
				one.setModdate(rs.getDate("moddate"));
				one.setCnt(rs.getInt("cnt"));
				list.add(one);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public String getPhotoFilename(int seq) {
		String filename = null;
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1,seq);
			rs = stmt.executeQuery();
			if(rs.next()){
				filename = rs.getString("photo");
			}
			rs.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("===> JDBC로 getPhotoFilename() 기능처리");
		return filename;
	}
}