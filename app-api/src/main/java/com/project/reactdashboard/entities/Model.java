package com.project.reactdashboard.entities;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Model")
public class Model implements Serializable {

    private int id;

    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Model() {
    }

    private Model(ModelBuilder builder) {
        this.id = builder.id;
        this.value = builder.value;
    }

    public static class ModelBuilder{

        private int id;

        private String value;

        public ModelBuilder(){
        }

        public ModelBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ModelBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public Model build(){
            return new Model(this);
        }

    }
}
