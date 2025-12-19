package com.example.languageteacherbot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sentence")
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String words;

    @Column(name = "correct_sentence", columnDefinition = "TEXT")
    private String correctSentence;

    private String level;
    private String language;

    public Sentence() {}

    public Sentence(String words, String correctSentence, String level, String language) {
        this.words = words;
        this.correctSentence = correctSentence;
        this.level = level;
        this.language = language;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getWords() { return words; }
    public void setWords(String words) { this.words = words; }
    public String getCorrectSentence() { return correctSentence; }
    public void setCorrectSentence(String correctSentence) { this.correctSentence = correctSentence; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}