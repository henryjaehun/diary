package com.example.diary.service;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.repository.JpaDiaryEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiaryEntryService {
    private final JpaDiaryEntryRepository jpaDiaryEntryRepository;

    public DiaryEntryService(JpaDiaryEntryRepository jpaDiaryEntryRepository) {
        this.jpaDiaryEntryRepository = jpaDiaryEntryRepository;
    }

    //업데이트
    public void updateDiaryEntry(LocalDate date, String content) {
        DiaryEntry nowDiaryEntry = jpaDiaryEntryRepository.getFirstByDate(date);
        nowDiaryEntry.setContent(content);
        jpaDiaryEntryRepository.save(nowDiaryEntry);
    }

    //삭제
    public void deleteByDate(LocalDate date) {
        jpaDiaryEntryRepository.deleteAllByDate(date);
    }

}
