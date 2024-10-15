package com.smartbank.repositories;

import com.smartbank.entities.History;

import java.util.List;

public interface StatusHistoryRepository {
    History save(History statusHistory);
    List<History> findByCreditRequestId(Long creditRequestId);
}