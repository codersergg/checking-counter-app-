package com.codersergg.checklimit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckLimitRepository extends JpaRepository<CheckLimitHistory, Integer> {

    @Query("SELECT c FROM CheckLimitHistory c WHERE c.customerId = ?1 AND c.createdAt >= ?2")
    List<CheckLimitHistory> findCheckLimitHistoriesByCustomerId(Integer customerId, LocalDateTime ldt);
}
