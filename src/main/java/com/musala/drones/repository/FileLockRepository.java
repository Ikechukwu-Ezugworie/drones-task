package com.musala.drones.repository;

import com.musala.drones.entities.FileLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileLockRepository extends JpaRepository<FileLock, Long> {

    FileLock findByFileName(String fileName);
}
