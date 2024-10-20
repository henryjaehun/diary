package com.example.diary.repository;

import com.example.diary.domain.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {
}
