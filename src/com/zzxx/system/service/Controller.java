package com.zzxx.system.service;

import com.zzxx.system.beans.*;
import com.zzxx.system.ui.ClientContext;
import com.zzxx.system.util.IdOrPwdException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private EntityContext entityContext;
    private ClientContext clientContext;
    //登录
    public User login(int id,String password) throws IdOrPwdException {
        User user = entityContext.getUserByid(id);
        if(user == null ){
            throw new IdOrPwdException("用户不存在");
        }else if(!user.getPassword().equals(password)){
            throw new IdOrPwdException("密码错误");
        }else
            return user;
    }
    public EntityContext getEntityContext() {
        return entityContext;
    }
//出试卷
    List<QuestionInfo> paper = new ArrayList<>();
    public List<QuestionInfo> getPaper(){
        int index = 0;
        for(int i = 1;i < 11 ; i++){
            List<Question> questions = entityContext.getQuestion().get(i);
            int random = (int)(Math.random()*questions.size());
            Question question1 = questions.remove(random);
            random = (int)(Math.random()*questions.size());
            Question question2 = questions.remove(random);
            QuestionInfo questionInfo1 = new QuestionInfo(index++,question1);
  //          questionInfo1.setIndex(index++);
  //      System.out.println(questionInfo1.getIndex());
 //           questionInfo1.setQuestion(question1);
            QuestionInfo questionInfo2 = new QuestionInfo(index++,question2);
 //           questionInfo2.setIndex(index++);
  //          System.out.println(questionInfo2.getIndex());
 //           questionInfo2.setQuestion(question2);
            paper.add(questionInfo1);
            paper.add(questionInfo2);
        }
        return paper;
    }
    public void setEntityContext(EntityContext entityContext) {
        this.entityContext = entityContext;
    }
    public QuestionInfo getQuestion(int num){//得到试卷中当前题号的题目信息
        return paper.get(num);
    }
    //得到考试规则
    public List<String> getRules(){
        List<String> rules = entityContext.getRules();
        return rules;
    }
    //存入用户答案
    public void saveUserAnswer(int num,List<Integer> userAnswer){
        QuestionInfo q = paper.get(num);
        q.getUserAnswer().clear();//回到上一题时清空上一题的答案
        q.getUserAnswer().addAll(userAnswer);
 //      System.out.println(paper.get(num).getUserAnswer());
    }
//开始考试
    public void start(){
        getPaper();
    }

//计算分数
    private int score = 0;
    public int getScore(){
        for(QuestionInfo questionInfo : paper){
            Question question = questionInfo.getQuestion();
            List<Integer> userAnswer = questionInfo.getUserAnswer();
//            System.out.println(userAnswer);
            if(userAnswer.equals(question.getCorrectAnswer())){
                score = score + question.getScore();
            }
        }
        return score;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
