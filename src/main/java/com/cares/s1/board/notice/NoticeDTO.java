package com.cares.s1.board.notice;

import java.util.List;

import com.cares.s1.board.BoardDTO;

public class NoticeDTO extends BoardDTO {

	private List<NoticeFileDTO> fileDTOs;

	public List<NoticeFileDTO> getFileDTOs() {
		return fileDTOs;
	}

	public void setFileDTOs(List<NoticeFileDTO> fileDTOs) {
		this.fileDTOs = fileDTOs;
	}
}
