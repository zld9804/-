package com.zzxx.system.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame{
    private JPanel generalLayout;
    private JPanel centerLayout;
    private JPanel numLayout;
    private JPanel passwordLayout;
    private JPanel buttonLayout;
    private JTextField numTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private ClientContext clientContext;
    private  JLabel message;
    public LoginFrame(){
        generalLayout = new JPanel();
        centerLayout = new JPanel();
        numLayout = new JPanel();
        passwordLayout = new JPanel();
        buttonLayout = new JPanel();
        numTextField = new JTextField(20);
        init();

    }
    public void init(){
        setLayout ();
        setGeneralLayout ();
        setCenterLayout ();
        setNumLayout();
        setPasswordLayout();
        setButtonLayout();
        setWindow();
    }
    public void setLayout () {
        generalLayout.setLayout(new BorderLayout());
        centerLayout.setLayout(new GridLayout(3, 1));
        numLayout.setLayout(new FlowLayout());
        passwordLayout.setLayout(new FlowLayout());
        buttonLayout.setLayout(new FlowLayout());
    }
    public void setGeneralLayout () {
        JLabel titleLabel = new JLabel("指针测评登录系统",JLabel.CENTER);
        titleLabel.setFont(new Font("楷体", Font.BOLD, 32));
        titleLabel.setSize(50, 35);

        generalLayout.add(titleLabel, BorderLayout.NORTH);
        generalLayout.add(buttonLayout,BorderLayout.SOUTH);
        generalLayout.add(centerLayout, BorderLayout.CENTER);
    }
    public void setCenterLayout () {
        message = new JLabel("",JLabel.CENTER);
        centerLayout.add(numLayout);
        centerLayout.add(passwordLayout);
        centerLayout.add(message);
    }
    public void setNumLayout () {
        JLabel idLabel = new JLabel("帐号：");
        idLabel.setFont(new Font("楷体", Font.BOLD, 20));
        idLabel.setSize(100, 40);
        //         idLabel.setLocation(95, 80);


        numTextField.setPreferredSize(new Dimension(60,40));
        //        numTextField.setLocation(140, 120);
        numLayout.add(idLabel);
        numLayout.add(numTextField);
    }
    public void setPasswordLayout () {
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("楷体", Font.BOLD, 20));
        passwordLabel.setSize(50, 40);
        //           passwordLabel.setLocation(95, 80);
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(60,40));
        //          passwordField.setLocation(140, 120);
        passwordLayout.add(passwordLabel);
        passwordLayout.add(passwordField);
    }
    public void setButtonLayout(){
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        loginButton.setPreferredSize(new Dimension(150,40));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientContext.login();
            }
        });
        cancelButton.setPreferredSize(new Dimension(150,40)); buttonLayout.add(loginButton);
        buttonLayout.add(cancelButton);
    }
    public void setWindow () {
        this.add(generalLayout);
        //设置窗体可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(500, 400);
        //设置窗口默认选项
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //设置窗体居中
        this.setLocationRelativeTo(null);
        //window.setIconImage(background);
        this.setResizable(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int option = JOptionPane.showConfirmDialog(clientContext.getLoginFrame(),"确定离开吗？");
                if(option == JOptionPane.YES_OPTION){
                    System.exit(0);
                }

            }
        });
    }
    public int getId(){
        Integer id = Integer.valueOf(numTextField.getText());
        return id;
    }
    public String getPassword(){
        String password =new String( passwordField.getPassword());
        return password;

    }
    public void showMessage(String message){
        this.message.setText(message);
    }
    public JTextField getNumTextField() {
        return numTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }


    public JButton getLoginButton() {
        return loginButton;
    }


    public JButton getCancelButton() {
        return cancelButton;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

}
