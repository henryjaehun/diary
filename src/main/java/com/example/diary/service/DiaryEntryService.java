package com.example.diary.service;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.dto.DiaryEntryRequestDto;
import com.example.diary.repository.JpaDiaryEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class DiaryEntryService {
    private final JpaDiaryEntryRepository jpaDiaryEntryRepository;

    public DiaryEntryService(JpaDiaryEntryRepository jpaDiaryEntryRepository) {
        this.jpaDiaryEntryRepository = jpaDiaryEntryRepository;
    }

    //업로드( 내용, 사진)
    // 이미지 파일을 저장하고 특정 경로를 반환
    public String saveImage(MultipartFile image) {
        if (image.isEmpty() || image == null) {
            return null;
        }
        try {
            // 서버의 특정 경로에 이미지 저장
            String filePath = "/Users/jungjaehun/DEV/spring-study/diary_image/" + image.getOriginalFilename();
            File dest = new File(filePath);
            image.transferTo(dest);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DiaryEntry createDiaryEntry(DiaryEntryRequestDto diaryEntryRequestDto) {
        String imagePath = saveImage(diaryEntryRequestDto.getImage());

        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setDate(diaryEntryRequestDto.getDate());
        diaryEntry.setTitle(diaryEntryRequestDto.getTitle());
        diaryEntry.setContent(diaryEntryRequestDto.getContent());
        diaryEntry.setImagePath(imagePath);

        return jpaDiaryEntryRepository.save(diaryEntry);
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
