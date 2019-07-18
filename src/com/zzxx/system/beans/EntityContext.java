package com.zzxx.system.beans;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityContext {
    private Map<Integer,List<Question>> questions = new HashMap<Integer, List<Question>>();
    private Map<Integer,User> users =new HashMap<Integer, User>();
    public EntityContext(){
        getUser();
        getQuestion();
        getRules();
    }
    //试题解析 Level-Question
    public  Map<Integer, List<Question>> getQuestion(){
        String path = EntityContext.class.getClassLoader().getResource("com/zzxx/system/util/corejava.txt").getPath();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"GBK"));

            String len =null;
            while((len = br.readLine())!=null){
                Question q = new Question();
                String []info = len.split(",");
                String answer = info[0].substring(info[0].indexOf("=")+1);
                String score = info[1].substring(info[1].indexOf("=")+1);
                String level = info[2].substring(info[2].indexOf("=")+1);
                List<Integer> correctAnswers = new ArrayList<Integer>();
                if(answer.contains("/")){
                    String []answers = answer.split("/");
                    for(int i = 0;i<answers.length;i++){
                        correctAnswers.add(Integer.valueOf(answers[i]));
                    }
                }else{
                    correctAnswers.add(Integer.valueOf(answer));
                }
                q.setCorrectAnswer(correctAnswers);
                q.setScore(Integer.valueOf(score));
                q.setLevel(Integer.valueOf(level));
                q.setTitle(br.readLine());

                List<String> options = new ArrayList<String>();
                options.add(br.readLine());
                options.add(br.readLine());
                options.add(br.readLine());
                options.add(br.readLine());
                q.setOption(options);

                List list = questions.get(q.getLevel());
                if(list == null){
                    list = new ArrayList();
                    list.add(q);
                }else {
                    list.add(q);
                }
            questions.put(q.getLevel(),list);
            }
            br.close();
 //           System.out.println(questions);
            return questions;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //用户解析
    public Map<Integer,User> getUser(){
        String path = EntityContext.class.getClassLoader().getResource("com/zzxx/system/util/user.txt").getPath();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"GBK"));
        String len = null;
        while((len = br.readLine())!=null){
            if(len.startsWith("#")||len.equals("")){
                continue;
            }
            String[] data = len.split(":");
            User user = new User();
            user.setId(Integer.valueOf(data[0]));
            user.setName(data[1]);
            user.setPassword(data[2]);
            user.setTel(data[3]);
            user.setEmail(data[4]);
            users.put(user.getId(), user);
        }
//            System.out.println(users);
        return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //根据id获得用户
    public User getUserByid(int id){
        return users.get(id);
    }
    //规则解析
    private List<String> rules = new ArrayList<>();
    public List<String> getRules(){
        String path = EntityContext.class.getClassLoader().getResource("com/zzxx/system/util/rule.txt").getPath();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"GBK"));
        String len = null;
        while((len = br.readLine())!=null){
            rules.add(len);
        }
        return rules;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
