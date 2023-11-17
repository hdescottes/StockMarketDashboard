package com.project.reactdashboard.entities;

public class ModelDto {

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

    public ModelDto() {
    }

    private ModelDto(ModelDtoBuilder builder) {
        this.id = builder.id;
        this.value = builder.value;
    }

    public static class ModelDtoBuilder{

        private int id;

        private String value;

        public ModelDtoBuilder(){
        }

        public ModelDtoBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ModelDtoBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public ModelDto build(){
            return new ModelDto(this);
        }

    }
}
