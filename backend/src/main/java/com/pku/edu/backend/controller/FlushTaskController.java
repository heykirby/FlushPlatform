package com.pku.edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pku.edu.backend.model.FlushTask;
import com.pku.edu.backend.service.ProgressService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/task")
@Slf4j
public class FlushTaskController {
    @Autowired
    private ProgressService progressService;

    @PostMapping()
    public ResponseEntity<?> executeFlushTask(@RequestBody Long templateId) {
        log.info("receive id: {}", templateId);
        progressService.startTask(templateId);
        return ResponseEntity.ok("started");
    }

    @GetMapping("/running")
    public List<FlushTask> getInProgress() {
        return progressService.getByStatus(FlushTask.Status.RUNNING);
    }

    @GetMapping("/finished")
    public List<FlushTask> getFinished() {
        return progressService.getByStatus(FlushTask.Status.SUCCESS);
    }
}
