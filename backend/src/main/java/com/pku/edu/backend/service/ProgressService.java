package com.pku.edu.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.pku.edu.backend.mapper.FlushMapper;
import com.pku.edu.backend.model.FlushTask;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProgressService {
    @Autowired
    private FlushMapper flushMapper;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostConstruct
    public void init() {
        // 重启时删除所有正在运行的刷数任务
        flushMapper.deleteFlushTaskByStatus(FlushTask.Status.RUNNING);
    }

    public void startTask(long templateId) {
        long flushTotalCount = getFlushTotalCount(templateId);

        String taskId = UUID.randomUUID().toString();
        FlushTask task = new FlushTask();
        task.setId(taskId);
        task.setTemplateId(templateId);
        task.setStatus(FlushTask.Status.RUNNING);
        task.setTotalRows(flushTotalCount);
        flushMapper.createFlushTask(task);
        log.info("create flush task {}", task);
        new Thread(() -> {
            for (long i = 1; i <= flushTotalCount; i++) {
                task.setProcessRows(i);
                messagingTemplate.convertAndSend("/topic/progress", task);
                log.info("send to /topic/progress, {}", task);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ignored) {
                }
            }
            task.setProcessRows(flushTotalCount);
            task.setStatus(FlushTask.Status.SUCCESS);
            // 更新数据库状态
            updateTask(task);
            messagingTemplate.convertAndSend("/topic/progress", task);
        }).start();
    }

    private long getFlushTotalCount(long templateId) {
        return 10;
    }

    public void updateTask(FlushTask flushTask) {
        flushMapper.updateFlushTask(flushTask);
    }

    public List<FlushTask> getByStatus(FlushTask.Status status) {
        return flushMapper.findFlushTaskByStatus(status);
    }
}