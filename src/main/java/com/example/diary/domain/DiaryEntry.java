package com.example.diary.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "diary")
public class DiaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String title;
    private String content;
}
