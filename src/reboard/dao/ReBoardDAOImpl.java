package reboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import reboard.dto.ReBoardDTO;

public class ReBoardDAOImpl implements ReBoardDAO {
	private JdbcTemplate jdbcTemplate;
	class MyRowMapper implements RowMapper<ReBoardDTO>{

		@Override
		public ReBoardDTO mapRow(ResultSet rs, int co) throws SQLException {
			// TODO Auto-generated method stub
			ReBoardDTO dto = new ReBoardDTO();
			dto.setNum( rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setEmail(rs.getString("email"));
			dto.setSubject(rs.getString("subject"));
			dto.setPasswd(rs.getString("passwd"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setReadcount(rs.getInt("readcount"));
			dto.setContent( rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			dto.setRe_step(rs.getInt("re_step"));
			dto.setRe_level(rs.getInt("re_level"));
			return dto;
		}
		
	}
	private MyRowMapper mapper = new MyRowMapper();
	@Override
	public List<ReBoardDTO> listReBoard(int start , int end) {
		String sql = "select * from (select rownum rn,A.*from (select * from board order by re_step asc)A) where rn between ? and ?";
		List<ReBoardDTO> list = jdbcTemplate.query(sql,mapper,start,end);
		return list;
	}

	@Override
	public int writeReBoard(ReBoardDTO dto) {
		String sql = null;
		if(dto.getNum()==0) {//새글
			sql = "update board set re_step = re_step +1";
			jdbcTemplate.update(sql);
		}else {//답글
			sql = "update board set re_step = re_step + 1 where re_step > " + dto.getRe_step();
			dto.setRe_step(dto.getRe_step()+1);
			dto.setRe_level(dto.getRe_level()+1);
			jdbcTemplate.update(sql);
		}
		sql = "insert into board values (board_seq.nextval,?,?,?,?,sysdate,0,?,?,?,?)";
		Object[] values = new Object[] {
				dto.getWriter(),dto.getEmail(),dto.getSubject(),dto.getPasswd(),dto.getContent(),dto.getIp(),dto.getRe_level(),dto.getRe_step()
		};
		int res = jdbcTemplate.update(sql,values);
		return res;
	}

	@Override
	public ReBoardDTO getBoard(int num, String mode) {
		// TODO Auto-generated method stub
		String sql = null;
		if(mode.equals("content")) {
			sql = "update board set readcount = readcount + 1 where num = ?";
			jdbcTemplate.update(sql,num);
		}
		sql = "select * from board where num = ?";
		ReBoardDTO dto = jdbcTemplate.queryForObject(sql, mapper,num);
		return dto;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from board";
		int res = jdbcTemplate.queryForInt(sql);
		return res;
	}

}
