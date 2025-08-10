package com.pku.edu.backend.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pku.edu.backend.mapper.FlushMapper;
import com.pku.edu.backend.model.FlushTemplate;

@Component
public class FlushService {
    @Autowired
    private FlushMapper flushMapper;

    public List<FlushTemplate> listAllFlushTemplate() {
        return flushMapper.listAllFlushTemplate();
    }

    public FlushTemplate selectById(long id) {
        return flushMapper.findFlushTemplateById(id);
    }
    public int createFlushTemplate(FlushTemplate flushTemplate) {
        return flushMapper.createFlushTemplate(flushTemplate);
    }

    public int updateFlushTemplate(FlushTemplate flushTemplate) {
        return flushMapper.updateFlushTemplate(flushTemplate);
    }
    public int deleteFlushTemplateByid(long id) {
        return flushMapper.deleteFlushTemplateByid(id);
    }

    public void startTask(String taskId, String script, Map<String, String> params) {
        new Thread(() -> {
//            List<User> users = userMapper.findUsersByKeyword(params.get("keyword"));
//            int total = users.size();
//            for (int i = 0; i < users.size(); i++) {
//                kafkaService.sendToKafka("flush-topic", users.get(i));
//                taskManager.updateProgress(taskId, i + 1, total);
//                try { Thread.sleep(100); } catch (Exception ignored) {}
//            }
//            taskManager.finishTask(taskId);
        }).start();
    }
}
