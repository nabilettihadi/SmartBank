package com.smartbank.services;

import com.smartbank.entities.Status;
import java.util.List;

public interface StatusService {
    Status getStatusById(Long id);
    Status getStatusByName(String name);
    Status createStatus(String name);
    List<Status> getAllStatuses();
}