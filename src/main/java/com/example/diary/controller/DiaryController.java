package com.example.diary.controller;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.repository.DiaryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    DiaryEntryRepository diaryEntryRepository;

    // post 로 일기 저장
    @PostMapping("/create/diary")
    public DiaryEntry createDiary(@RequestBody DiaryEntry diaryEntry) {
        return diaryEntryRepository.save(diaryEntry);
    }


    // get 으로 일기 조회
    @GetMapping("/read/diaries")
    public List<DiaryEntry> readAllDiaries() {
        return diaryEntryRepository.findAll();
    }

    // get 으로 일기 조회
    @GetMapping("/read/diary")
    public List<DiaryEntry> readDiaryByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return null;
    }


}
