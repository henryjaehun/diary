package com.example.diary.controller;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.repository.JpaDiaryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    JpaDiaryEntryRepository jpaDiaryEntryRepository;

    // post 로 일기 저장
    @PostMapping
    public DiaryEntry createDiary(@RequestBody DiaryEntry diaryEntry) {
        return jpaDiaryEntryRepository.save(diaryEntry);
    }


    // get 으로 일기 조회
    @GetMapping
    public List<DiaryEntry> readAllDiaries() {
        return jpaDiaryEntryRepository.findAll();
    }

    // get 으로 날짜별 일기 조회
    @GetMapping("/read-by-date")
    public List<DiaryEntry> readDiaryByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return jpaDiaryEntryRepository.findByDate(date);
    }


}
