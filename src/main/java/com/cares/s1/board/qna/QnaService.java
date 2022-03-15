package com.cares.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cares.s1.board.BoardDTO;
import com.cares.s1.board.BoardService;
import com.cares.s1.util.FileManager;
import com.cares.s1.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.makeRow();
		pager.makeNum(qnaDAO.total(pager));
		
		return qnaDAO.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.detail(boardDTO);
	}

	@Override
	public int add(BoardDTO boardDTO, MultipartFile files[]) throws Exception {
		
		int result = qnaDAO.add(boardDTO);
		
		for(int i=0;i<files.length;i++) {
			if(files[i].isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.save(files[i], "resources/upload/qna/");
			
			QnaFileDTO qnaFileDTO = new QnaFileDTO();
			qnaFileDTO.setFileName(fileName);
			qnaFileDTO.setOriName(files[i].getOriginalFilename());
			qnaFileDTO.setNum(boardDTO.getNum());
			
			qnaDAO.addFile(qnaFileDTO);
		}
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		List<QnaFileDTO> ar = qnaDAO.fileList(boardDTO);
		
		int result = qnaDAO.delete(boardDTO); 
		
		if(result > 0) {
			for(QnaFileDTO qnaFileDTO : ar) {
				fileManager.remove("resources/upload/qna", qnaFileDTO.getFileName());
			}
		}
		
		return result;
	}
	
	public int reply(QnaDTO qnaDTO) throws Exception {
		// QnaDTO.num		: 부모글의 번호
		// QnaDTO.title		: 폼에서 입력한 제목
		// QnaDTO.writer	: 폼에서 입력한 작성자
		// QnaDTO.contents	: 폼에서 입력한 내용
		// QnaDTO.regDate	: null
		// QnaDTO.hit		: null
		// QnaDTO.ref		: null
		// QnaDTO.step		: null
		// QnaDTO.depth		: null
		
		// 1. 부모글의 정보를 조회 (REF, STEP, DEPTH)
		BoardDTO boardDTO = qnaDAO.detail(qnaDTO);
		
		// 1번에서 실제 만들어진건 QnaDTO임, 근데 리턴은 BoardDTO임 (운반하기 위해서)
		// 그래서 REF, STEP, DEPTH를 쓰고 싶으면 QnaDTO로 형변환 해주어야 함
		QnaDTO parent = (QnaDTO) boardDTO;

		// 2. 답글의 ref는 부모의 ref값
		qnaDTO.setRef(parent.getRef());
		
		// 3. 답글의 step은 부모의 step + 1
		qnaDTO.setStep(parent.getStep()+1);
		
		// 4. 답글의 depth는 부모의 depth + 1
		qnaDTO.setDepth(parent.getDepth()+1);
		
		// 5. 테이블에서 ref가 부모의 ref와 같고, step이 부모의 step보다 큰것들을 각자의 step에 +1 한 값으로 업데이트
		int result = qnaDAO.stepUpdate(parent);
		// 부모글의 REF, STEP이 조건에 들어가야 하기 때문에 부모의 DTO를 보내야 함
		
		// 6. 답글 Insert
		result = qnaDAO.reply(qnaDTO);
		// 최종적으로 REF, STEP, DEPTH가 계산된 QndDTO를 보내면 됨
		
		return 0;
	}
	
	public QnaFileDTO detailFile(QnaFileDTO qnaFileDTO) throws Exception {
		return qnaDAO.detailFile(qnaFileDTO);
	}

}
