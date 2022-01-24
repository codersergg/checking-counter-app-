package com.codersergg.checklimit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckLimitHistoryRepository extends JpaRepository<CheckLimitHistory, Integer> {
}
