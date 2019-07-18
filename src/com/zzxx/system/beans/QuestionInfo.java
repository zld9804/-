package com.zzxx.system.beans;

import java.util.ArrayList;
import java.util.List;

public class QuestionInfo {//试卷里的题目类，包含题号和用户答案
    private Question question;
    private List<Integer> userAnswer = new ArrayList<>();
    private int index;
    public QuestionInfo(){

    }
    public QuestionInfo(int index,Question question){
        this.index =index;
        this.question = question;
    }
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Integer> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<Integer> userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        index = index;
    }

    @Override
    public String toString() {
        return question.toString();
    }
}
