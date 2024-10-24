package com.example.diary.controller;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.dto.DiaryEntryRequestDto;
import com.example.diary.repository.JpaDiaryEntryRepository;
import com.example.diary.service.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    public DiaryEntry createDiary(@ModelAttribute DiaryEntryRequestDto diaryEntryRequestDto) {
        return diaryEntryService.createDiaryEntry(diaryEntryRequestDto);
    }


    // get 으로 모든 일기 조회
    @GetMapping
    public List<DiaryEntry> readAllDiaries() {
        return diaryEntryService.getAllDiaryEntries();
    }

    // get 으로 날짜별 일기 조회
    @GetMapping("/read-by-date")
    public List<DiaryEntry> readDiaryByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return jpaDiaryEntryRepository.findByDate(date);
    }

    // 특장닐짜의 일기 수정
    @PutMapping("/update")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody DiaryEntryRequestDto diaryEntryRequestDto) {
        diaryEntryService.updateDiaryEntry(date, diaryEntryRequestDto.getContent());
        System.out.println("일기가 수정되었습니다. ");
    }


    // 웹페이지에서 일기 보여주기
    @GetMapping("/show")
    public String showAllDiaries(Model model) {
        List<DiaryEntry> entries = diaryEntryService.getAllDiaryEntries();
        model.addAttribute("entries", entries);
        //model.addAttribute("entries", "test");
        return "diary";
    }



    // delete 일기 삭제
    @Transactional
    @DeleteMapping("/delete")
     void deleteDiaryEntry(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        diaryEntryService.deleteByDate(date);
        System.out.println(date + "날의 일기가 삭제 되었습니다. ");
    }



}
