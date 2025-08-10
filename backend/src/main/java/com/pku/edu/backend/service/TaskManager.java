package com.pku.edu.backend.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {
//    private final Map<String, TaskStatus> taskMap = new ConcurrentHashMap<>();
//    @Autowired
//    private WebSocketManager webSocketManager;
//
//    public void createTask(String taskId, String scriptName) {
//        taskMap.put(taskId, new TaskStatus(taskId, scriptName, 0, 0, "RUNNING"));
//    }
//
//    public void updateProgress(String taskId, int current, int total) {
//        TaskStatus task = taskMap.get(taskId);
//        if (task != null) {
//            task.setProgress(current);
//            task.setTotal(total);
//            webSocketManager.sendProgress(task);
//        }
//    }
//
//    public void finishTask(String taskId) {
//        TaskStatus task = taskMap.get(taskId);
//        if (task != null) {
//            task.setStatus("DONE");
//            webSocketManager.sendProgress(task);
//        }
//    }
//
//    public Collection<TaskStatus> listTasks() {
//        return taskMap.values();
//    }

}
