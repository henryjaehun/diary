package com.example.diary.controller;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.dto.DiaryEntryUpdateRequest;
import com.example.diary.repository.JpaDiaryEntryRepository;
import com.example.diary.service.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    JpaDiaryEntryRepository jpaDiaryEntryRepository;
    @Autowired
    private DiaryEntryService diaryEntryService;

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

    // 특장닐짜의 일기 수정
    @PutMapping("/update")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody DiaryEntryUpdateRequest diaryEntryUpdateRequest) {
        diaryEntryService.updateDiaryEntry(date, diaryEntryUpdateRequest.getText());
        System.out.println("일기가 수정되었습니다. ");
    }


    // delete 일기 삭제
    @Transactional
    @DeleteMapping("/delete")
     void deleteDiaryEntry(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryEntryService.deleteByDate(date);
        System.out.println(date + "날의 일기가 삭제 되었습니다. ");
    }



}
