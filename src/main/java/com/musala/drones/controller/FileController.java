package com.musala.drones.controller;

import com.musala.drones.pojo.DronePojo;
import com.musala.drones.service.FileLockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    private FileLockService fileLockService;

    @Operation(summary = "lock a file")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "500", description = "internal error - critical!")})
    @GetMapping
    public ResponseEntity<?> processFile() {
        try {
            Path file = Paths.get("C:\\Users\\ikechukwue\\Desktop\\publish - Copy\\opendock-client-application");
            fileLockService.acquireLock(file.toString());
            try {
                // Perform file processing operations
                File f = file.toFile();
                if(f.exists()){
                    System.out.println("Name: "+ f.getName());
                    System.out.println("Path: "+ f.getAbsolutePath());
                    System.out.println("Size: "+ f.length());
                    System.out.println("Writeable: "+ f.canWrite());
                    System.out.println("Readable: "+ f.canRead());
                }else{
                    System.out.println("The File does not exist");
                }
            } finally {
                Thread.sleep(10000);
                fileLockService.releaseLock(file.toString());
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
