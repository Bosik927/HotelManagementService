package com.github.bosik927.model.repository.boundary;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Integer id, String entityName){
        super("Could not find " + entityName + " " + id);
    }
}