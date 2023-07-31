package board.mybatis;

import java.io.IOException;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import board.dto.BoardDTO;

public class BoardMapper {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException("DB연동 오류발생" + e.toString());
		}
	}

	public static List<BoardDTO> listBoard() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<BoardDTO> list = sqlSession.selectList("listBoard");
			return list;
		} finally {
			sqlSession.close();
		}
	}

	public static int insertBoard(BoardDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int res = sqlSession.insert("insertBoard", dto);
			sqlSession.commit();
			return res;
		} finally {
			sqlSession.close();
		}
	}

	public static void setContent(int num) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("setContent", num);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public static BoardDTO getBoard(int num, String mode) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			BoardDTO dto = sqlSession.selectOne("getBoard", num);
			if (mode.equals("content")) {
				sqlSession.update("setContent", num);
			}
			return dto;
		} finally {
			sqlSession.close();
		}
	}

	public static int deleteBoard(int num, String passwd) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			BoardDTO dto = getBoard(num, "passwd");
			if (dto.getPasswd().equals(passwd)) {
				int res = sqlSession.delete("deleteBoard", num);
				sqlSession.commit();
				return res;
			} else {
				return -1;
			}
		} finally {
			sqlSession.close();
		}
	}

	public static int updateBoard(BoardDTO dto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			BoardDTO dto2 = getBoard(dto.getNum(), "passwd");
			if (dto2.getPasswd().equals(dto.getPasswd())) {
				int res = sqlSession.update("updateBoard", dto);
				sqlSession.commit();
				return res;
			} else {
				return -1;
			}
		} finally {
			sqlSession.close();
		}
	}

}
