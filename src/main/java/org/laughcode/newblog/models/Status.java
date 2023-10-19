package org.laughcode.newblog.models;

public enum Status {

    HAPPY("Happy"),
    SAD("Sad"),
    CONFUSED("Confused"),
    ANGRY("Angry"),
    HANGRY("Hangry"),
    DENIAL("In denial");

    private final String text;

    Status(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
