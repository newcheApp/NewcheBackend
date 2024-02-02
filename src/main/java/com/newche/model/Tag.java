package com.newche.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Tag {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String displayName;

    private static final Logger logger = LoggerFactory.getLogger(News.class);

    public Tag() {
    	// TODO Auto-generated constructor stub
    }
    
    public Tag(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
        logger.debug("Tag object created: {}", this.toString());
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
		this.id = id;
        logger.debug("ID set to: {}", id);
	}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        logger.debug("Tag name set to: {}", name);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        logger.debug("Tag displayName set to: {}", displayName);
    }
    
	@Override
	public String toString() {
		return "Tag [id= " + id + ", name= " + name + ", display Name= " + displayName + "]";
	}
    
}






