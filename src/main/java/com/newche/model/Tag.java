package com.newche.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tag {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;

    
    public Tag() {
    	// TODO Auto-generated constructor stub
    }
    
    public Tag(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
    
}






