package com.example.languageteacherbot.repository;

import com.example.languageteacherbot.entity.UserFavoriteWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserFavoriteWordRepository extends JpaRepository<UserFavoriteWord, Long> {
    List<UserFavoriteWord> findByUserChatId(Long userChatId);
    
    @Query("SELECT fw FROM UserFavoriteWord fw JOIN FETCH fw.word WHERE fw.userChatId = :chatId")
    List<UserFavoriteWord> findByUserChatIdWithWord(@Param("chatId") Long chatId);
    
    Optional<UserFavoriteWord> findByUserChatIdAndWordId(Long userChatId, Long wordId);
    void deleteByUserChatIdAndWordId(Long userChatId, Long wordId);
}