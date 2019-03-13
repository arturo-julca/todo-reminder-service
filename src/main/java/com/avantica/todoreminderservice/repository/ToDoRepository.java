package com.avantica.todoreminderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avantica.todoreminderservice.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

}
