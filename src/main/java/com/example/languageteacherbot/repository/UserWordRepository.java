package com.example.languageteacherbot.repository;

import com.example.languageteacherbot.entity.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    List<UserWord> findByUserChatId(Long userChatId);

    @Query("SELECT uw FROM UserWord uw JOIN FETCH uw.word WHERE uw.userChatId = :chatId")
    List<UserWord> findByUserChatIdWithWord(@Param("chatId") Long chatId);
    
    Optional<UserWord> findByUserChatIdAndWordId(Long userChatId, Long wordId);
    void deleteByUserChatIdAndWordId(Long userChatId, Long wordId);
}