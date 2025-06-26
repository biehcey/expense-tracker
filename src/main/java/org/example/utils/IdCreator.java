package org.example.utils;

import org.springframework.stereotype.Component;

@Component
public class IdCreator {
    private int id;
    public IdCreator(){
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int generateId(){
        this.id = getId() + 1;
        return this.id;
    }
}
