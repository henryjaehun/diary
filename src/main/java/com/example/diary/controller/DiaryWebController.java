package com.example.diary.controller;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.service.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/diary")
public class DiaryWebController {

    @Autowired
    DiaryEntryService diaryEntryService;

    // 웹페이지에서 일기 보여주기
    @GetMapping("/show")
    public String showAllDiaries(Model model) {
        List<DiaryEntry> entries = diaryEntryService.getAllDiaryEntries();
        model.addAttribute("entries", entries);
       // model.addAttribute("entries", "test");
        return "diary";
    }
}
