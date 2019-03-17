package com.avantica.todoreminderservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.avantica.todoreminderservice.model.ToDo;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private ToDoRepository clientRepository;
    
    ToDo todoToSave;
    
    @Before 
    public void setUpTodo(){
    	todoToSave = new ToDo.Builder()
    			.setName("name")
    			.setEmail("arturo@test.com")
    			.setStatus("NOT SENT")
    			.build();
    }
    @Test
    public void testFindToSentFuture() {
    	LocalDateTime currentTime = LocalDateTime.now();
    	LocalDateTime futureTime = currentTime.plusHours(1L);
    	todoToSave.setTime(futureTime);
    	clientRepository.save(todoToSave);
        List<ToDo> listTodoFounded = clientRepository.findToSent(currentTime);
        Assert.assertTrue(listTodoFounded.isEmpty());
    }
    
    @Test
    public void testFindToSentPast() {
    	LocalDateTime currentTime = LocalDateTime.now();
    	LocalDateTime pastTime = currentTime.minusHours(1L);
    	todoToSave.setTime(pastTime);
    	todoToSave = clientRepository.save(todoToSave);
        List<ToDo> listTodoFounded = clientRepository.findToSent(currentTime);
        Assert.assertTrue(!listTodoFounded.isEmpty());
        Assert.assertEquals(listTodoFounded.get(0).getId(), todoToSave.getId());
    }
}
