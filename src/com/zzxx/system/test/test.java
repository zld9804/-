package com.zzxx.system.test;

import com.zzxx.system.beans.EntityContext;
import com.zzxx.system.service.Controller;
import com.zzxx.system.ui.ClientContext;
import com.zzxx.system.ui.ExamFrame;
import com.zzxx.system.ui.LoginFrame;
import com.zzxx.system.ui.MenuFrame;

public class test {
    public static void main(String []args){
        Controller controller = new Controller();
        EntityContext entityContext = new EntityContext();
        ClientContext clientContext = new ClientContext();
        LoginFrame loginFrame = new LoginFrame();
        MenuFrame menuFrame = new MenuFrame();
        ExamFrame examFrame = new ExamFrame();

        controller.setEntityContext(entityContext);

        controller.setClientContext(clientContext);
        clientContext.setLoginFrame(loginFrame);
        clientContext.setMenuFrame(menuFrame);
        clientContext.setExamFrame(examFrame);
        clientContext.setController(controller);
        loginFrame.setClientContext(clientContext);
        menuFrame.setClientContext(clientContext);
        examFrame.setClientContext(clientContext);
//        EntityContext entityContext = new EntityContext();
//        entityContext.getUser();
//        System.out.println(entityContext.getUserByid(1000));
    }
}
