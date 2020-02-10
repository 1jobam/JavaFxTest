package board.service;

import java.util.List;

import board.vo.BoardVO;

public interface BoardService {
	
	public Object insertBoard(BoardVO bv);
	
	public int updateBoard(BoardVO bv);
	
	public int deleteBoard(BoardVO bv);
	
	public List<BoardVO> getAllBoard();
	
	public List<BoardVO> getSearchBoard(BoardVO bv);

}
