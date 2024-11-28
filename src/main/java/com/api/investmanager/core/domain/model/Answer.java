package com.api.investmanager.core.domain.model;

public class Answer {
    private boolean response;
    private Question question;

    public Answer() {
        this.response = false;
    }

    public Answer(boolean response) {
        this.response = response;
    }

    public boolean isResponse() {
        return response;
    }

    public void toogleResponse(){
        this.response = !this.isResponse();
    }


}
