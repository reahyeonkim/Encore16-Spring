package com.freeflux.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.freeflux.biz.board.BoardVO;

/** DAO : Data Access Object **/

@Repository("boardDAO")
public class BoardDAO {

	// JDBC ���� ��� ����
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL ��ɾ� ��� ����
	private final String BOARD_INSERT = "insert into board (seq, title, writer, content) values ((select nvl(max(seq), 0)+1 from board), ?, ?, ?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";

	public BoardDAO() {
	}

	/** CRUD ��� �޼��� ���� **/
	// �� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC�� insertBaord() ��� ó�� : " + vo.toString());

		try {
			this.conn = JDBCUtil.getConnection();
			this.stmt = this.conn.prepareStatement(BOARD_INSERT);
			this.stmt.setString(1, vo.getTitle());
			this.stmt.setString(2, vo.getWriter());
			this.stmt.setString(3, vo.getContent());
			this.stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	} // insertBoard(BoardVO vo) END

	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC�� updateBoard() ��� ó��");

		try {
			this.conn = JDBCUtil.getConnection();
			this.stmt = this.conn.prepareStatement(BOARD_UPDATE);
			this.stmt.setString(1, vo.getTitle());
			this.stmt.setString(2, vo.getContent());
			this.stmt.setInt(3, vo.getSeq());
			this.stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	} // updateBoard(BoardVO vo) END

	// �� ����
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC�� deleteBoard() ��� ó��");

		try {
			this.conn = JDBCUtil.getConnection();
			this.stmt = this.conn.prepareStatement(BOARD_DELETE);
			this.stmt.setInt(1, vo.getSeq());
			this.stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	} // deleteBoard(BoardVO vo) END

	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC�� getBoard() ��� ó��");
		BoardVO board = null;

		try {
			this.conn = JDBCUtil.getConnection();
			this.stmt = this.conn.prepareStatement(BOARD_GET);
			this.stmt.setInt(1, vo.getSeq());
			this.rs = this.stmt.executeQuery();

			if (this.rs.next()) {
				board = new BoardVO();
				board.setSeq(this.rs.getInt("seq"));
				board.setTitle(this.rs.getString("title"));
				board.setWriter(this.rs.getString("writer"));
				board.setContent(this.rs.getString("content"));
				board.setRegDate(this.rs.getDate("regdate"));
				board.setCnt(this.rs.getInt("cnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	} // getBoard(BoardVO vo) END

	
	
	
	// �� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC�� getBoardList() ��� ó��");
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();

		try {
			this.conn = JDBCUtil.getConnection();
			this.stmt = this.conn.prepareStatement(BOARD_LIST);
			this.rs = this.stmt.executeQuery();

			while (this.rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(this.rs.getInt("seq"));
				board.setTitle(this.rs.getString("title"));
				board.setWriter(this.rs.getString("writer"));
				board.setContent(this.rs.getString("content"));
				board.setRegDate(this.rs.getDate("regdate"));
				board.setCnt(this.rs.getInt("cnt"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	} // getBoardList(BoardVO vo) END
}
