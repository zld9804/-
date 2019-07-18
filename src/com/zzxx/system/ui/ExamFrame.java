package com.zzxx.system.ui;

import com.zzxx.system.beans.QuestionInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExamFrame extends JFrame{
    JLabel label[]  = new JLabel[6];
    JButton button[] = new JButton[3];
    JPanel panel;
    JCheckBox checkBox[] = new JCheckBox[4];
    private ClientContext clientContext;
    public ExamFrame() {
        panel = new JPanel();
        panel.setLayout(null);
        init();
    }
    public void init(){
        initLabel();
        newButton();
        newCheckBox();
        newPanel();
        newFrame();
    }
    public void initLabel() {
        for(int i = 0;i<6;i++) {
            label[i] = new JLabel();
        }
        label[0] = new JLabel("题目");
        label[0].setText("指针在线测评系统");
        label[0].setFont(new Font("楷体",Font.BOLD,32));
        label[0].setSize(400,35);
        label[0].setLocation(240,10);

        label[1].setText("姓名:     编号:     考试时间：          考试科目：       题目数量：20");
        label[1].setFont(new Font("楷体",Font.BOLD,18));
        label[1].setSize(700,100);
        label[1].setLocation(10,47);

        label[2].setText("");
        label[2].setOpaque(true);
        label[2].setBackground(Color.WHITE);
        label[2].setFont(new Font("楷体",Font.BOLD,16));
        label[2].setSize(700,210);
        label[2].setLocation(10,110);

        label[3].setText("A     B     C     D");
        label[3].setFont(new Font("楷体",Font.BOLD,20));
        label[3].setSize(600,100);
        label[3].setLocation(200,290);

        label[4].setText("题目："+1+"/20 ");//50个空格
        label[4].setFont(new Font("楷体",Font.BOLD,20));
        label[4].setSize(700,120);
        label[4].setLocation(10,310);

        label[5].setText("剩余时间："+1+"："+0);//50个空格
        label[5].setFont(new Font("楷体",Font.BOLD,20));
        label[5].setSize(700,120);
        label[5].setLocation(450,310);
    }
    public void newButton() {
        for(int i = 0;i<3;i++) {
            button[i] = new JButton();
        }
        int x = 130;
        button[0].setText("上一题");
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.last();

            }
        });
        button[1].setText("下一题");
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.next();

            }
        });
        button[2].setText("交卷");
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.getScore();
                flag = false;
            }
        });

        for(int i =0;i<3;i++) {
            button[i].setSize(80,30);
            button[i].setLocation(x, 400);
            x = x+120;
        }
    }
    public void newCheckBox() {
        int x=180;
        for(int i = 0;i<4;i++) {
            checkBox[i] = new JCheckBox();
            checkBox[i].setSize(20, 20);
            checkBox[i].setLocation(x,330);
            x =x + 66;
        }

    }
    public void newFrame() {

        this.add(panel);
        //设置窗体可见
        this.setVisible(false);
        //设置窗口大小
        this.setSize(750, 500);
        //设置窗口默认选项
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //设置窗体居中
        this.setLocationRelativeTo(null);
    }
    public void newPanel() {
        for(int i = 0;i<6;i++) {
            panel.add(label[i]);
        }
        for(int i =0;i<3;i++) {
            panel.add(button[i]);
        }
        for(int i =0;i<4;i++) {
            panel.add(checkBox[i]);
        }
    }
    public void updateInfo(String name,int id){
        label[1].setText("姓名："+ name+"  编号："+id+" 考试时间：      考试科目：       题目数量：20");
    }
    public void updateQuestion(QuestionInfo questionInfo){
     //   System.out.println(questionInfo.getQuestion().toString());
        label[2].setText("<html><budy>"+(questionInfo.getIndex()+1)+"."+questionInfo.getQuestion().getTitle()+"<br>"
                +questionInfo.getQuestion().getOption().get(0)+"<br>"
                +questionInfo.getQuestion().getOption().get(1)+"<br>"
                +questionInfo.getQuestion().getOption().get(2)+"<br>"
                +questionInfo.getQuestion().getOption().get(3)+"<br>");
        label[4].setText("题目："+(questionInfo.getIndex()+1)+"/20 ");
    }
    public List<Integer> getUserAnswer(){
        List<Integer> userAnswer = new ArrayList<>();

            if (checkBox[0].isSelected()) {
                userAnswer.add(0);
            }
            if (checkBox[1].isSelected()) {
                userAnswer.add(1);
            }
            if (checkBox[2].isSelected()) {
                userAnswer.add(2);
            }
            if (checkBox[3].isSelected()) {
                userAnswer.add(3);
            }

            return userAnswer;
    }
    private boolean flag;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void updateTime(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int min =1;
                int second = 0;
                while(flag){
                    if (second == 0) {
                        min--;
                        second = 60;
                        second--;
                    } else {
                        second--;
                    }
                    try {
                        label[5].setText("剩余时间："+min+":"+second);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
