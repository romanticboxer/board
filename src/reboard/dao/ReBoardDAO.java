package reboard.dao;
import java.util.List;
import reboard.dto.*;
public interface ReBoardDAO {
	public List<ReBoardDTO> listReBoard(int start , int end);
	public int writeReBoard(ReBoardDTO dto);
	public ReBoardDTO getBoard(int num,String mode);
	public int getCount();
}
