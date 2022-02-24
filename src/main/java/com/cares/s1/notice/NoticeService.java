package com.cares.s1.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cares.s1.util.NoticePager;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	public List<NoticeDTO> list(NoticePager noticePager) throws Exception {
		
		noticePager.makeRow();
		Long totalCount = noticeDAO.getTotal();
		noticePager.makeNum(totalCount);
		return noticeDAO.list(noticePager);
	}
	
	public NoticeDTO detail(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.detail(noticeDTO);
	}
	
	public int add(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.add(noticeDTO);
	}
	
	public int update(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.update(noticeDTO);
	}

	public int delete(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.delete(noticeDTO);
	}
}
