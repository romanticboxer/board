package reboard.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import reboard.dto.ReBoardDTO;

public class ReBoardMapper {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			throw new RuntimeException("DB연동오류발생"+e.toString());
		}
	}
	public static List<ReBoardDTO> listBoard(int startRow,int endRow){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Map<String,Integer> param = new HashMap<>();
			param.put("startRow", startRow);
			param.put("endRow",endRow);
			List<ReBoardDTO> list = sqlSession.selectList("listReBoard",param);
			return list;
		}finally {
			sqlSession.close();
		}
	}
	public static ReBoardDTO getBoard(int num,String mode) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			if(mode.equals("content")) {
				sqlSession.update("setReadcount",num);
				sqlSession.commit();
			}
			ReBoardDTO dto = sqlSession.selectOne("getReBoard",num);
			return dto;
		}finally {
			sqlSession.close();
		}
	}
	public static int writeReBoard(ReBoardDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			if(dto.getNum()!=0) {
				dto.setRe_step(dto.getRe_step()+1);
				dto.setRe_level(dto.getRe_level()+1);
			}
			int res = sqlSession.insert("writeReBoard",dto);
			sqlSession.commit();
			return res;
		}finally {
			sqlSession.close();
		}
	}
	public static int getCount() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int res = sqlSession.selectOne("getCount");
			return res;
		}finally {
			sqlSession.close();
		}
	}
}
