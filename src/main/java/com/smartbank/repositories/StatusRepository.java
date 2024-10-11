package com.smartbank.repositories;

import com.smartbank.entities.Status;

public interface StatusRepository {
    Status findByName(String name);
    Status save(Status status);
}
