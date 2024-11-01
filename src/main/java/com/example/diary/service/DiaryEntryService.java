package com.example.diary.service;

import com.example.diary.domain.DiaryEntry;
import com.example.diary.dto.DiaryEntryRequestDto;
import com.example.diary.repository.JpaDiaryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryEntryService {
    private final JpaDiaryEntryRepository jpaDiaryEntryRepository;

    @Autowired
    public DiaryEntryService(JpaDiaryEntryRepository jpaDiaryEntryRepository) {
        this.jpaDiaryEntryRepository = jpaDiaryEntryRepository;
    }

    //업로드( 내용, 사진)
    // 이미지 파일을 저장하고 특정 경로를 반환
    public String saveImage(MultipartFile image) {
        return Optional.ofNullable(image) // 옵셔널 함수로 널처리 하기
                // 이미지가 있을때만 필터링 하기
                .filter(img -> !img.isEmpty())
                .map(img -> {
                    // map 은 Optional 에서 값을 변환하거나 처리할 때 주로 사용하는 메서드이다.
                    // map 내부의 코드 블록은 Optional 내부의 값이 존재할 때만 실행된다.
                    // map 을 사용해 MultipartFile 을 이미지 경로(String)로 변환할 수 있습니다.
                    try {
                        String fileName = img.getOriginalFilename();
                        String filePath = "/images/" + fileName;  // 상대 경로 설정
                        File dest = new File("/Users/jungjaehun/DEV/spring-study/diary/src/main/resources/static" + filePath);
                        img.transferTo(dest);
                        return filePath;  // 상대 경로로 반환
                    }catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .orElse(null);

    }

    // 일기 생성 메소드
    public DiaryEntry createDiaryEntry(DiaryEntryRequestDto diaryEntryRequestDto) {
        String imagePath = saveImage(diaryEntryRequestDto.getImage());

        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setDate(diaryEntryRequestDto.getDate());
        diaryEntry.setTitle(diaryEntryRequestDto.getTitle());
        diaryEntry.setContent(diaryEntryRequestDto.getContent());
        diaryEntry.setImagePath(imagePath);

        return jpaDiaryEntryRepository.save(diaryEntry);
    }

    public void saveDiaryEntry(DiaryEntry diaryEntry) {
        // DiaryEntryRepository를 통해 DB에 저장
        jpaDiaryEntryRepository.save(diaryEntry);
    }


    // 웹페이지에서 다이어리 보여주기위한 모든 일기 조회
    public List<DiaryEntry> getAllDiaryEntries() {
        return jpaDiaryEntryRepository.findAll();
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
