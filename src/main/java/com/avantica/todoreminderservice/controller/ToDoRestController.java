package com.avantica.todoreminderservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avantica.todoreminderservice.model.ToDo;
import com.avantica.todoreminderservice.repository.ToDoRepository;


@RestController
@RequestMapping({"/"})
public class ToDoRestController {

	private ToDoRepository toDoRepository;
	
	ToDoRestController(ToDoRepository toDoRepository){
		this.toDoRepository = toDoRepository;
	}
	
	@GetMapping
    public List findAll(){
        return toDoRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ToDo> findById(@PathVariable long id){
    	return toDoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ToDo create(@RequestBody ToDo toDo){
        return toDoRepository.save(toDo);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<ToDo> update(@PathVariable("id") long id,
                                          @RequestBody ToDo todo){
      return toDoRepository.findById(id)
          .map(record -> {
              record.setName(todo.getName());
              record.setTime(todo.getTime());
              record.setEmail(todo.getEmail());
              ToDo updated = toDoRepository.save(record);
              return ResponseEntity.ok().body(updated);
          }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
      return toDoRepository.findById(id)
          .map(record -> {
        	  toDoRepository.deleteById(id);
              return ResponseEntity.ok().build();
          }).orElse(ResponseEntity.notFound().build());
    }

}
