package com.musala.drones.entities;

import javax.persistence.*;

@Entity
@Table(name = "file_locks")
public class FileLock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", unique = true)
    private String fileName;

    @Column(name = "locked")
    private boolean locked;

    // Constructors, getters, and setters

    public FileLock(Long id, String fileName, boolean locked) {
        this.id = id;
        this.fileName = fileName;
        this.locked = locked;
    }

    public FileLock(String fileName) {
        this.fileName = fileName;
    }

    public FileLock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
