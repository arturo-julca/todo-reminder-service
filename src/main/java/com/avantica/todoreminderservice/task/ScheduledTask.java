package com.avantica.todoreminderservice.task;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.avantica.todoreminderservice.model.ToDo;
import com.avantica.todoreminderservice.repository.ToDoRepository;

@Component
public class ScheduledTask {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

	@Autowired
	private ToDoRepository toDoRepository;
	
	//cannot be outsourced to application.properties due to restriction of @sechudled
	private static final long TASK_FIXED_RATE_IN_MS = 10000;
	
	@Scheduled(fixedRate = TASK_FIXED_RATE_IN_MS)
    public void finTodosToSent() {
        LOGGER.info("Todo update scheduled task running at "+ new Date());
        List<ToDo> listToDo = toDoRepository.findToSent(LocalDateTime.now());
        for(ToDo todo : listToDo){
        	LOGGER.info("Todo to update: "+ todo);
        	todo.setStatus("SENT");
        	toDoRepository.save(todo);
        	LOGGER.info("Todo updated: "+ todo);
        }
    }
}
