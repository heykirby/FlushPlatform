package com.pku.edu.backend.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FlushTask {
    public enum Status { RUNNING, SUCCESS, FAIL, CANCEL }
    private String id;
    private Long templateId;
    private Status status;
    private Long processRows;
    private Long totalRows;
    private String errorMsg;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
