package com.example.languageteacherbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "words",
    uniqueConstraints = @UniqueConstraint(columnNames = {"word", "lang"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String translation;

    @Column(nullable = false)
    private String level;

    @Column(nullable = false)
    private String lang;

    @Column(name = "transcription", nullable = true)
    private String transcription;
}