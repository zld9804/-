package com.zzxx.system.ui;

import com.zzxx.system.beans.Question;
import com.zzxx.system.beans.QuestionInfo;
import com.zzxx.system.beans.User;
import com.zzxx.system.service.Controller;
import com.zzxx.system.util.IdOrPwdException;

import javax.swing.*;
import java.util.List;

public class ClientContext {
    private ExamFrame examFrame;
    private LoginFrame loginFrame;
    private MenuFrame menuFrame;
    private Controller controller;

    //登录
    User login;
    public void login(){
        try {
             login = controller.login(loginFrame.getId(), loginFrame.getPassword());
            loginFrame.setVisible(false);
            menuFrame.setVisible(true);
            menuFrame.updateView(login.getName());
        } catch (IdOrPwdException e) {
            loginFrame.showMessage(e.getMessage());
        }catch (NumberFormatException e){
            loginFrame.showMessage("用户名不合法");
        }
    }
    //开始考试
    public void start(){
        controller.start();//创建试卷
        questionNumber=0;
        examFrame.updateInfo(login.getName(),login.getId());
        questionInfo = controller.getQuestion(0);
        questionInfo.setIndex(0);
        examFrame.updateQuestion(questionInfo);
        menuFrame.setVisible(false);
        examFrame.setVisible(true);
        examFrame.setFlag(true);
        examFrame.updateTime();
    }
    //得到考试规则
    public List<String> getRules(){
        return controller.getRules();
    }
    //下一题
    QuestionInfo questionInfo;//当前试题，包含题号，用户答案，
    private int questionNumber = 0;
    public void next(){
        if(questionNumber < 19) {
            questionNumber++;
            questionInfo = controller.getQuestion(questionNumber);
            examFrame.updateQuestion(questionInfo);
            List<Integer> userAnswer = examFrame.getUserAnswer();
            controller.saveUserAnswer(questionNumber,userAnswer);
 //           System.out.println(questionInfo.getUserAnswer());
//        questionInfo.setIndex(questionNumber);
//        System.out.println(questionInfo.getIndex());
        }
        else
            questionNumber = 19;
 //       return questionNumber;
    }
    //上一题
    public void last(){
        if(questionNumber == 0){
            questionNumber =0;
        }else {
            questionNumber--;
  //          System.out.println(questionInfo);
            questionInfo = controller.getQuestion(questionNumber);
            List<Integer> userAnswer = examFrame.getUserAnswer();
            controller.saveUserAnswer(questionNumber,userAnswer);
            examFrame.updateQuestion(questionInfo);
        }
 //       return questionNumber;
  //      questionInfo.setIndex(questionNumber);

    }
    public ExamFrame getExamFrame() {
        return examFrame;
    }
/*//得到用户答案
    public void userAnswer(){
        List<Integer> userAnswer = examFrame.getUserAnswer();
        controller.saveUserAnswer(questionNumber,userAnswer);
    }*/

    public void setExamFrame(ExamFrame examFrame) {
        this.examFrame = examFrame;
    }
//得到试卷分数
    public void getScore(){
        int score = controller.getScore();
        JOptionPane.showMessageDialog(examFrame,"分数为："+score);
        examFrame.setVisible(false);
        menuFrame.setVisible(true);
    }
    public LoginFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public MenuFrame getMenuFrame() {
        return menuFrame;
    }

    public void setMenuFrame(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
