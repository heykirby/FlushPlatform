package com.pku.edu.backend.model;

import lombok.Data;
@Data
public class FlushTemplate {
    private Long id;
    private String name;
    private String sqlText;
    private String kafkaCluster;
    private String kafkaTopic;
    private Integer rateLimit;
}
