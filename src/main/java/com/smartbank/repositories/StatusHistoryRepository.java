package com.smartbank.repositories;

import com.smartbank.entities.History;

public interface StatusHistoryRepository {
    History save(History statusHistory);
}