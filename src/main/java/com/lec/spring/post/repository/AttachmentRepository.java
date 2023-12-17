package com.lec.spring.post.repository;

import com.lec.spring.domain.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentRepository {
    int insert(List<Map<String, Object>> list, Long postId);

    // 처마부파일 DB예 저장
    int save(Attachment file);

    List<Attachment> findByPost(Long post_id);

    // 특정 첨부파일 하나 select
    Attachment findById(Long id);

    // 선택된 첨부파일들을 선택하여 글 수정에 사용
    List<Attachment> findByIds(Long [] ids);

    // 선택된 첨부파일을 삭제
    int deleteByIds(Long [] ids);

    // 특정 첨부파일을 DB에서 삭제
    int delete(Attachment file);
}
