package com.smartbank.services.impl;

import com.smartbank.entities.Status;
import com.smartbank.repositories.StatusRepository;
import com.smartbank.services.StatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class StatusServiceImpl implements StatusService {

    @Inject
    private StatusRepository statusRepository;

    public Status getStatusById(Long id) {
        return statusRepository.findById(id);
    }

    public Status getStatusByName(String name) {
        return statusRepository.findByName(name);
    }

    @Transactional
    public Status createStatus(String name) {
        Status status = new Status(name);
        return statusRepository.save(status);
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}