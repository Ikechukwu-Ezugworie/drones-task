package com.musala.drones.service;

import com.musala.drones.entities.FileLock;
import com.musala.drones.repository.FileLockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Slf4j
@Service
public class FileLockService {
    @Autowired
    private FileLockRepository fileLockRepository;

    private Map<String, Semaphore> fileLocks = new ConcurrentHashMap<>();

    public void acquireLock(String fileName) {
        log.info("Acquiring lock for file {}", fileName);
        Semaphore semaphore = fileLocks.computeIfAbsent(fileName, k -> new Semaphore(1));
        try {
            semaphore.acquire();
            FileLock fileLock = fileLockRepository.findByFileName(fileName);
            if (fileLock == null) {
                fileLock = new FileLock(fileName);
            }
            fileLock.setLocked(true);
            fileLockRepository.save(fileLock);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void releaseLock(String fileName) {
        log.info("Releasing lock for file {}", fileName);
        Semaphore semaphore = fileLocks.get(fileName);
        if (semaphore != null) {
            FileLock fileLock = fileLockRepository.findByFileName(fileName);
            if (fileLock != null && fileLock.isLocked()) {
                fileLock.setLocked(false);
                fileLockRepository.save(fileLock);
                semaphore.release();
            }
        }
    }
}
