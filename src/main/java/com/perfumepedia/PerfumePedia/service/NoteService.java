package com.perfumepedia.PerfumePedia.service;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Note;
import com.perfumepedia.PerfumePedia.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    /**
     * Create or Nothing
     * <p> Note 엔티티를 매개변수로 받아, 데이터베이스에 없는 엔티티라면 데이터베이스에 저장.
     * <p> 데이터베이스에 있다면 nothing
     * @param note Note 엔티티
     * @return 저장한(save or nothing) note의 id
     */
    @Transactional(readOnly = false)
    public Long saveNote(Note note){
        try {
            validateDuplicateNote(note);
        } catch (IllegalStateException e) {
            // 데이터베이스에 값이 존재하는 경우
            // 처리하지 않음
        }
        // 데이터베이스에 값이 존재하지 않는 경우
        // NoteRepository #save 이용 저장
        noteRepository.save(note);  // 중복이 없을 경우에만 저장
        return note.getId();  // 저장한 경우 note의 id return

    }

    private void validateDuplicateNote(Note note) {
        noteRepository.findByName(note.getName())
                .ifPresent(n->{
                    throw new IllegalStateException("이미 존재하는 노트입니다.");
                });
    }

}