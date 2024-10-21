package com.example.diary.repository;

import com.example.diary.domain.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JpaDiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
    // 날짜 별 일기조회 쿼리 추가
    List<DiaryEntry> findByDate(LocalDate date);
}
