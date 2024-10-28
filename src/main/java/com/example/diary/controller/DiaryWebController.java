package com.example.diary.controller;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.dto.DiaryEntryRequestDto;
import com.example.diary.service.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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

    @PostMapping("/show/confirm")
    public String showConfirm(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              RedirectAttributes redirectAttributes) {
        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setDate(date);
        diaryEntry.setTitle(title);
        diaryEntry.setContent(content);

        diaryEntryService.saveDiaryEntry(diaryEntry);

        // 확인 메시지를 보여주기 위해 리다이렉트 시 속성 추가
        redirectAttributes.addFlashAttribute("message", "일기가 저장되었습니다!");

        // 저장 후 다시 일기 목록을 보여주는 페이지로 이동
        return "redirect:/diary/show";
    }

}
