package com.example.languageteacherbot.repository;

import com.example.languageteacherbot.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    
    List<Sentence> findByLevelAndLanguage(String level, String language);

    @Query("SELECT s FROM Sentence s WHERE s.language = :language AND " +
           "CASE WHEN :level = 'A1' THEN s.level = 'A1' " +
           "WHEN :level = 'A2' THEN s.level IN ('A1', 'A2') " +
           "WHEN :level = 'B1' THEN s.level IN ('A1', 'A2', 'B1') " +
           "WHEN :level = 'B2' THEN s.level IN ('A1', 'A2', 'B1', 'B2') " +
           "WHEN :level = 'C1' THEN s.level IN ('A1', 'A2', 'B1', 'B2', 'C1') " +
           "WHEN :level = 'C2' THEN s.level IN ('A1', 'A2', 'B1', 'B2', 'C1', 'C2') " +
           "ELSE s.level = 'A1' END")
    List<Sentence> findByLevelUpToAndLanguage(@Param("level") String level, @Param("language") String language);
}