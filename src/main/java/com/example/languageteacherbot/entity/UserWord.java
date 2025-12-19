package com.example.languageteacherbot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "user_words",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_chat_id", "word_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_chat_id", nullable = false)
    private Long userChatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", nullable = false)
    private Word word;

    @Column(name = "marked_as_unknown", nullable = false)
    private boolean markedAsUnknown = true;
}