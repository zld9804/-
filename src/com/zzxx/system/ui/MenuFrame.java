package com.zzxx.system.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuFrame extends JFrame{
    private JPanel generalLayout;
    private JPanel welcomeLayout;
    private JPanel buttonLayout;
    private JLabel welcomeLabel;
    private ClientContext clientContext;
    public MenuFrame(){
        generalLayout = new JPanel();
        welcomeLayout = new JPanel();
        buttonLayout = new JPanel();
        init();
    }
    public void init(){
        setGeneralLayout();
        setLayout();
        setWelcomeLayout();
        setWindow();
        setButtonLayout();
    }
    public void setLayout(){
        generalLayout.setLayout(new GridLayout(2,1));
        welcomeLayout.setLayout(new BorderLayout());
        buttonLayout.setLayout(new FlowLayout());
    }
    public void setGeneralLayout(){
        JLabel titleLabel = new JLabel("指针在线测评系统",JLabel.CENTER);
        titleLabel.setFont(new Font("楷体", Font.BOLD, 32));
        titleLabel.setSize(50, 35);
        generalLayout.add(titleLabel);
        generalLayout.add(welcomeLayout);
    }
    public void setWelcomeLayout(){
        welcomeLabel = new JLabel("同学你好",JLabel.CENTER);
        welcomeLabel.setSize(50,35);
        welcomeLabel.setFont(new Font("楷体", Font.BOLD, 20));


        welcomeLayout.add(welcomeLabel,BorderLayout.NORTH);
        welcomeLayout.add(buttonLayout,BorderLayout.CENTER);}
    public void setWindow(){
        this.add(generalLayout);
        //设置窗体可见
        this.setVisible(false);
        //设置窗口大小
        this.setSize(600, 400);
//        //设置窗口默认选项
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //设置窗体居中
        this.setLocationRelativeTo(null);
        //window.setIconImage(background);
        this.setResizable(true);}
    public void setButtonLayout(){
        JButton testButton = new JButton("开始考试");
        testButton.setPreferredSize(new Dimension(200,50));
        testButton.setFont(new Font("楷体", Font.BOLD, 20));JButton scoreButton = new JButton("分数");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.start();
            }
        });
        scoreButton.setPreferredSize(new Dimension(200,50));
        scoreButton.setFont(new Font("楷体", Font.BOLD, 20));

        JButton ruleButton = new JButton("考试规则");
        ruleButton.setPreferredSize(new Dimension(200,50));
        ruleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> strings = clientContext.getRules();
                JOptionPane.showMessageDialog(clientContext.getMenuFrame(),"<html><body>"+strings.get(0) + "<br>"
                        +strings.get(1)+"<br>"
                        +strings.get(2)+"<br>"
                        +strings.get(3)+"<br>"
                        +strings.get(4)+"<br>"
                        +strings.get(5)+"<br>"
                        +strings.get(6)+"<br>"
                        +strings.get(7)+"<br>"
                        +strings.get(8)+"<br>"
                        +strings.get(9)+"<br>");
            }
        });
        ruleButton.setFont(new Font("楷体", Font.BOLD, 20));
        JButton quitButton = new JButton("离开");
        quitButton.setPreferredSize(new Dimension(200,50));
        quitButton.setFont(new Font("楷体", Font.BOLD, 20));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(clientContext.getMenuFrame(), "确定离开吗？");
                if(option == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        buttonLayout.add(testButton);
        buttonLayout.add(scoreButton);
        buttonLayout.add(ruleButton);
        buttonLayout.add(quitButton);
    }
    public void updateView(String name){
        welcomeLabel.setText(name + "同学你好");
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
