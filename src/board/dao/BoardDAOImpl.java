package board.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import board.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO{
	private JdbcTemplate jdbcTemplate;
	class MyRowMapper implements RowMapper<BoardDTO>{

		@Override
		public BoardDTO mapRow(ResultSet rs, int co) throws SQLException {
			BoardDTO dto = new BoardDTO();
			dto.setNum( rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent( rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			return dto;
		}
	}
	private MyRowMapper mapper = new MyRowMapper();
	@Override
	public List<BoardDTO> listBoard() {
		String sql = "select * from springBoard order by num desc";
		List<BoardDTO> list = jdbcTemplate.query(sql, mapper);
		return list;
	}

	@Override
	public BoardDTO getBoard(int num, String mode) {
		// TODO Auto-generated method stub
		String sql = null;
		if(mode.equals("content")) {
			sql = "update springBoard set readcount = readcount +1 where num = ?"; 
			jdbcTemplate.update(sql,num);
		}
		sql = "select * from springBoard where num = ?";
		BoardDTO dto = jdbcTemplate.queryForObject(sql, mapper,num);
		return dto;
	}
	@Override
	public int insertBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		String sql = "insert into springBoard values(springBoard_seq.nextval,?,?,?,?,sysdate,0,?,?)";
		Object[] values = new Object[] {
				dto.getWriter(),dto.getEmail(),dto.getSubject(),dto.getPasswd(),dto.getContent(),dto.getIp()
		};
		int res = jdbcTemplate.update(sql,values);
		return res;
	}

	@Override
	public int deleteBoard(int num, String passwd) {
		// TODO Auto-generated method stub
		String sql = "delete from springBoard where num = ? and passwd = ?";
		int res = jdbcTemplate.update(sql,num,passwd);
		return res;
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		String sql = "update springBoard set subject = ?, content = ? , email = ? where num = ? and passwd = ?";
		Object[] obj = new Object[] {
				dto.getSubject(),dto.getContent(),dto.getEmail(),dto.getNum(),dto.getPasswd()
		};
		int res = jdbcTemplate.update(sql,obj);
		return res;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
