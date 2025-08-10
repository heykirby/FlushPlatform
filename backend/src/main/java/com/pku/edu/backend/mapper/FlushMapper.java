package com.pku.edu.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pku.edu.backend.model.FlushTask;
import com.pku.edu.backend.model.FlushTemplate;

@Mapper
public interface FlushMapper {
    @Select("SELECT * FROM flush_template")
    List<FlushTemplate> listAllFlushTemplate();
    @Insert("INSERT INTO flush_template(name, sql_text, kafka_cluster, kafka_topic, rate_limit) " +
            "VALUES(#{name}, #{sqlText}, #{kafkaCluster}, #{kafkaTopic}, #{rateLimit})")
    int createFlushTemplate(FlushTemplate template);

    @Update("UPDATE flush_template set name = #{name}, sql_text = #{sqlText}, kafka_cluster = #{kafkaCluster}, kafka_topic = #{kafkaTopic}, rate_limit = #{rateLimit} where id = #{id}")
    int updateFlushTemplate(FlushTemplate template);

    @Select("SELECT * FROM flush_template WHERE id = #{id}")
    FlushTemplate findFlushTemplateById(@Param("id") Long id);

    @Delete("DELETE FROM flush_template WHERE id = #{id}")
    int deleteFlushTemplateByid(@Param("id") Long id);

    @Insert("INSERT INTO flush_task(id, template_id, status, process_rows, total_rows, error_msg, start_time, end_time) " +
            "VALUES(#{id}, #{templateId}, #{status}, #{processRows}, #{totalRows}, #{errorMsg}, #{startTime}, #{endTime})")
    int createFlushTask(FlushTask template);

    @Update("UPDATE flush_task set template_id = #{templateId}, status = #{status}, process_rows = #{processRows}, total_rows = #{totalRows}, error_msg = #{errorMsg}, start_time = #{startTime}, end_time = #{endTime} where id = #{id}")
    int updateFlushTask(FlushTask flushTask);

    @Select("SELECT * FROM flush_task where status = #{status}")
    List<FlushTask> findFlushTaskByStatus(@Param("status")FlushTask.Status status);

    @Delete("DELETE FROM flush_task WHERE status = #{status}")
    int deleteFlushTaskByStatus(@Param("status") FlushTask.Status status);
}
