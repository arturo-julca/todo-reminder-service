package com.avantica.todoreminderservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avantica.todoreminderservice.model.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

	@Query("SELECT t FROM ToDo t WHERE t.status = 'NOT SENT' AND t.time <= ?1")
	List<ToDo> findToSent(LocalDateTime time);
}