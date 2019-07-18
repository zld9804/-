package com.zzxx.system.beans;

import java.util.List;

public class Question {
    private List<Integer> correctAnswer;
    private int score;
    private int level;
    private String title;
    private List<String> option;

    public List<Integer> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<Integer> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }
/*
    @Override
    public String toString() {
        return
               "<html><body>" +title+"<br>"+"A."+option.get(0)+"<br>"
                        +"B."+option.get(1)+"<br>"
                        +"C."+option.get(2)+"<br>"
                        +"D."+option.get(3)+"<br>";
    }*/
}
