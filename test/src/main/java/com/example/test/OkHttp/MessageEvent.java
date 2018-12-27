package com.example.test.OkHttp;

public class MessageEvent {
    Object object;
    String name;

    public MessageEvent(Object object, String name) {
        this.object = object;
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
