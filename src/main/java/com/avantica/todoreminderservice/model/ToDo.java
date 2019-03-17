package com.avantica.todoreminderservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ToDo {
	
    @Id
    @GeneratedValue
	private Long id;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime time;
	private String email;
	private String status;
	
	public ToDo(){		
	}
	
	public ToDo(Long id, String name, LocalDateTime time, String email, String status) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.email = email;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", name=" + name + ", time=" + time + ", email=" + email + ", status=" + status + "]";
	}
	
	public static class Builder {
        private Long id;
		private String name;
        private LocalDateTime time;
        private String email;
        private String status;
        
        public Builder(){}
        
        public Builder setName(String name){
        	this.name = name;
        	return this;
        }
        
        public Builder setTime(LocalDateTime time){
        	this.time = time;
        	return this;
        }
        
        public Builder setEmail(String email){
        	this.email = email;
        	return this;
        }
        
        public Builder setStatus(String status){
        	this.status = status;
        	return this;
        }
        
        public ToDo build() {
            return new ToDo(id, name, time, email, status);
        }
	}
}
