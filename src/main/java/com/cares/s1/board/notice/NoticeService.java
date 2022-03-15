package com.cares.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cares.s1.board.BoardDTO;
import com.cares.s1.board.BoardService;
import com.cares.s1.util.FileManager;
import com.cares.s1.util.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;

	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.makeRow();
		pager.makeNum(noticeDAO.total(pager));
		
		return noticeDAO.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return noticeDAO.detail(boardDTO);
	}

	@Override // Overriding : 상속받은 선언부와 동일해야함, 따라서 인터페이스를 바꿔주어야 함 (Service를 수정하면 안됨)
	public int add(BoardDTO boardDTO, MultipartFile[] files) throws Exception {
//		Long seqNum = noticeDAO.seqNum();
//		boardDTO.setNum(seqNum);
		int result = noticeDAO.add(boardDTO);
		for(int i=0;i<files.length;i++) {
			if(files[i].isEmpty()) {
				// 파일을 업로드하지 않으면 DB에 INSERT되면 안됨
				continue; // 몇번째에 안넣는지 모르기때문에 continue, break는 아예 반복문이 종료되기 때문
			}
			// 1. HDD에 저장
			String fileName = fileManager.save(files[i], "resources/upload/notice/");
			// 파일 하나씩을 보내야 하니까
			// 2. DB에 저장
			// 한번 저장할 때마다 fileName 하나씩 나오니까 반복문이 돌 때마다 DB에 저장 한번씩 해야함
			NoticeFileDTO noticeFileDTO = new NoticeFileDTO();
			noticeFileDTO.setFileName(fileName);
			noticeFileDTO.setOriName(files[i].getOriginalFilename());
			// 글이 INSERT가 되어 있어야 num을 구해오는데, 지금 INSERT하려고 하는 것임
			// Member의 경우에는 회원이 직접 ID를 입력하기 때문에 파라미터값으로 넘어왔었음
			// 근데 Board는, 시퀀스로 자동생성이라 글번호를 파라미터값으로 받지 않음
			// 즉, BoardDTO에 num은 없음..
			// 그래서 시퀀스번호를 미리 조회를 해서 값을 받아온 다음에 그것을 boardDTO에 넣은 다음에 add를 해야함
			noticeFileDTO.setNum(boardDTO.getNum());
			
			noticeDAO.addFile(noticeFileDTO);
		}
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		// 글 번호로 하드디스크에 저장된 파일명을 조회
		List<NoticeFileDTO> ar = noticeDAO.listFile(boardDTO);
		
		// 우선 지우는 작업을 한 다음에
		int result = noticeDAO.delete(boardDTO); 
		
		// 삭제가 됐을 경우만 파일을 지워야 함
		if(result > 0) {
//			for(int i=0;i<ar.size();i++) {
//				fileManager.remove("resources/upload/notice/", ar.get(i).getFileName());				
//			}
						
			//for(Collection에서꺼낼타입명 변수명 : Collection의 변수명) {}
			for(NoticeFileDTO noticeFileDTO : ar) {
				boolean check = fileManager.remove("resources/upload/notice/", noticeFileDTO.getFileName());
			}
		}
		
		return result;
	}
	
	public NoticeFileDTO detailFile(NoticeFileDTO noticeFileDTO) throws Exception {
		return noticeDAO.detailFile(noticeFileDTO);
	}
	
}
