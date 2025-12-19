package com.example.languageteacherbot.repository;

import com.example.languageteacherbot.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByLevelAndLang(String level, String lang);
    List<Word> findByLang(String lang);
}