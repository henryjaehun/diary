package com.example.diary.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class DiaryEntryRequestDto {
    private LocalDate date;
    private String title;
    private String content;
    private MultipartFile image;  // 사진 파일 (선택사항)
}
