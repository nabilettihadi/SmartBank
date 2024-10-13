package com.smartbank.repositories;

import com.smartbank.entities.Status;

import java.util.List;

public interface StatusRepository {
    Status findById(Long id);
    Status findByName(String name);
    Status save(Status status);
    List<Status> findAll();
}