package com.pku.edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pku.edu.backend.model.FlushTemplate;
import com.pku.edu.backend.service.FlushService;

@RestController
@RequestMapping("/templates")
public class FlushController {
    @Autowired
    private FlushService flushService;

    @GetMapping
    public List<FlushTemplate> findAll() {

        return flushService.listAllFlushTemplate();
    }

    @GetMapping("/{id}")
    public FlushTemplate findById(@PathVariable Long id) {
        return flushService.selectById(id);
    }

    @PostMapping
    public int insert(@RequestBody FlushTemplate flushTemplate) {
        return flushService.createFlushTemplate(flushTemplate);
    }

    @PutMapping
    public int update(@RequestBody FlushTemplate flushTemplate) {
        return flushService.updateFlushTemplate(flushTemplate);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        return flushService.deleteFlushTemplateByid(id);
    }


}
