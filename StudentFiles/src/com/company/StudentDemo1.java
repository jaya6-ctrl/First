package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StudentDemo1 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        ArrayList<User> userList = new ArrayList<>();
        noop:
        while (true) {
            System.out.println("---------欢迎来到学生管理系统----------");
            System.out.println("请选择操作：1，登录 2.注册 3.忘记密码");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> {
                    if (logIn(userList) == 1) {
                        break noop;
                    }
                }//登录
                case "2" -> addUser(userList);//注册账户
                case "3" -> forgetPasswd(userList);
                /*case "4" -> {
                    System.out.println("退出");
                    break noop;
                }*/
                default -> System.out.println("没有这个选择！！！");
            }
        }


        loop:
        while (true) {
            System.out.println("----------学生管理系统---------");
            System.out.println("1,添加学生");
            System.out.println("2,删除学生");
            System.out.println("3,修改学生");
            System.out.println("4,查询学生");
            System.out.println("5,退出");
            System.out.println("请输入你的选择：");
            Scanner sc = new Scanner(System.in);
            String number = sc.next();
            switch (number) {
                case "1" -> addStudent(list);
                case "2" -> deleteStudent(list);
                case "3" -> updateStudent(list);
                case "4" -> queryStudent(list);
                case "5" -> {
                    System.out.println("退出");
                    break loop;
                }
                default -> System.out.println("没有这个选项");
            }
        }

    }

    //注册功能(完成)
    public static void addUser(ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("注册账户");
        while (true) {
            System.out.println("请输入用户名：(用户名必须唯一，长度为3-15位，必须是数字加字母组合)");
            String username = sc.next();
            //判断唯一
            if (oneUsername(userList, username) && (username.length() >= 3 && username.length() <= 15) && (numAndworld(username))) {
                user.setUsername(username);
                break;
            } else {
                System.out.println("输入用户名不符合要求！");
            }
        }
        //设置密码
        while (true) {
            System.out.println("输入密码：");
            String passwd = sc.next();
            System.out.println("再次输入密码：");
            String nextpasswd = sc.next();
            if (passwd.equals(nextpasswd)) {
                user.setPasswd(passwd);
                System.out.println("密码设置成功");
                break;
            } else {
                System.out.println("两次密码输入不一致，请重新输入！！！");
            }
        }
        //输入身份证号码
        while (true) {
            System.out.println("输入身份证号码：");
            String bodynum = sc.next();
            if ((bodynum.length() == 18) && (bodynum.charAt(0) != 0) && (afterElevenNum(bodynum)) && (lastnum(bodynum))) {
                user.setBodyNumber(bodynum);
                System.out.println("身份证设置成功！");
                break;
            } else {
                System.out.println("输入有误，请重新输入！！");
            }

        }
        //设置手机号
        while (true) {
            System.out.println("输入手机号：");
            String phonenum = sc.next();
            if (phonenum.length() == 11 && phonenum.charAt(0) != 0 && allNum(phonenum)) {
                user.setPhoneNumber(phonenum);
                System.out.println("手机号设置成功！");
                break;
            } else {
                System.out.println("手机号输入有误，请重新输入！！！");
            }
        }
        //查看录入的信息
        System.out.println("username  passwd  bodyNumber  phoneNumber");
        System.out.println(user.getUsername() + "," + user.getPasswd() + "," + user.getBodyNumber() + "," + user.getPhoneNumber());
        userList.add(user);

    }

    //检查用户名是否唯一
    public static boolean oneUsername(ArrayList<User> userList, String userName) {
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (userName.equals(user.getUsername())) {
                return false;
            }

        }
        return true;
    }

    //判断用户名是不是数字加字母组合 0-9:48-57; A-Z:65-90; a-z:97-122;
    public static boolean numAndworld(String username) {
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {

            } else {
                return false;
            }
        }
        if (allNum(username)) {
            return false;
        }
        if (allWorld(username)) {
            return false;
        }
        return true;
    }

    //判断字符串是不是全是字母
    public static boolean allWorld(String username) {
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {

            } else {
                return false;
            }
        }
        return true;
    }

    //判断字符串是不是全是数字
    public static boolean allNum(String username) {
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 48 && c <= 57)) {

            } else {
                return false;
            }
        }
        return true;
    }

    //判断身份证号前17位是否都是数字
    public static boolean afterElevenNum(String bodynum) {
        for (int i = 0; i < bodynum.length() - 1; i++) {
            if ((bodynum.charAt(i) >= 48 && bodynum.charAt(i) <= 57)) {

            } else {
                return false;
            }
        }
        return true;
    }

    //判断身份证号码最后一位是否位数字或大小写x
    public static boolean lastnum(String bodynum) {
        char c = bodynum.charAt(bodynum.length() - 1);
        if ((c >= 48 && c <= 57) || (c == 88) || (c == 120)) {
            return true;
        } else {
            return false;
        }
    }

    //登录功能（完成）
    public static int logIn(ArrayList<User> userList) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        while (true) {
            for (int i = 3; i > 0; i--) {
                System.out.println("登录");
                System.out.println("请输入用户名：");
                String inuserName = sc.next();
                int index = index(userList, inuserName);
                if (index != -1) {
                } else {
                    System.out.println("用户名未注册，请先注册");
                    return -1;
                }
                User u = userList.get(index);
                System.out.println("请输入密码");
                String inpasswd = sc.next();
                System.out.println("验证码为：");
                String captcaha = captcha();
                System.out.println(captcaha);
                System.out.println("请输入验证码");
                String incaptcha = sc.next();
                if (captcaha.equals(incaptcha)) {
                    if (u.getUsername().equals(inuserName) && u.getPasswd().equals(inpasswd)) {
                        System.out.println("登录成功,进入学生管理系统！");
                        return 1;
                    } else {
                        System.out.println("账号或密码错误，还有" + (i - 1) + "次登录机会");
                    }
                } else {
                    System.out.println("验证码输入有误,请重新输入！还有" + (i - 1) + "次登录机会");
                }
            }
            break;

        }
        return -1;
    }

    //随机生成5位数验证码
    public static String captcha() {
        String captcha = "";
        Random r = new Random();
        String[] s = new String[5];
        char[] world = new char[52];
        for (int i = 0; i < 26; i++) {
            world[i] = (char) (65 + i);
        }
        for (int i = 26; i < 52; i++) {
            world[i] = (char) (97 + i - 26);
        }
        // StringBuilder sb=new StringBuilder();
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = world[r.nextInt(52)] + "";
        }
        int numb = r.nextInt(10);
        int index = r.nextInt(4);
        s[s.length - 1] = numb + "";
        /*sb.setCharAt(index,(char)numb);
        sb.setCharAt(4, sb.charAt(index));*/
        //captcha=sb.toString();
        String temp = s[index];
        s[index] = s[s.length - 1];
        s[s.length - 1] = temp;
        for (int i = 0; i < s.length; i++) {
            captcha = captcha + s[i];
        }
        return captcha;
    }

    //判断用户名是否注册(并返回集合里的index)
    public static int index(ArrayList<User> userList, String inuserName) {
        for (int i = 0; i < userList.size(); i++) {
            String nameList = userList.get(0).getUsername();
            if (nameList.equals(inuserName)) {
                return i;
            }
        }
        return -1;
    }

    //忘记密码（未完成）
    public static void forgetPasswd(ArrayList<User> userList) {
       woop:while (true){
        Scanner sc = new Scanner(System.in);

        System.out.println("——————修改账户——————");
        System.out.println("请输入用户名：");
        String inusername = sc.next();
        int index = index(userList, inusername);
        if (index == -1) {
            System.out.println("未注册");
            break;
        }
           User u =userList.get(index);

        while (true) {
            for (int i = 3; i > 0; i--) {

                System.out.println("请输入账户绑定的身份证号码：");
                String bodynum = sc.next();
                System.out.println("请输入账户绑定的手机号码：");
                String phone = sc.next();
                if (bodynum.equals(u.getBodyNumber()) && phone.equals(u.getPhoneNumber())) {
                    System.out.println("请输入新的密码");
                    String passwd=sc.next();
                    u.setPasswd(passwd);
                    userList.set(index,u);
                    break woop;
                } else {
                    System.out.println("账号信息不匹配，修改失败！还有"+(i-1)+"次修改机会");
                }
            }break;
        }
    }
    }

    //添加学生
    public static void addStudent(ArrayList<Student> list) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            Student s = new Student();
            System.out.println("添加学生");
            System.out.println("请输入学生id：");
            String id = sc.next();
            s.setId(id);
            //检查id是否重复
            if (list.size() > 0) {
                if (certain(list, id)) {
                    System.out.println("id重复，请重新输入");
                } else {
                    System.out.println("请输入学生name：");
                    //String name =sc.next();
                    s.setName(sc.next());
                    System.out.println("请输入学生age：");
                    //int age =sc.nextInt();
                    s.setAge(sc.nextInt());
                    System.out.println("请输入学生address：");
                    //String address=sc.next();
                    s.setAddress(sc.next());
                    list.add(s);
                    break;
                }
            } else {
                System.out.println("请输入学生name：");
                //String name =sc.next();
                s.setName(sc.next());
                System.out.println("请输入学生age：");
                //int age =sc.nextInt();
                s.setAge(sc.nextInt());
                System.out.println("请输入学生address：");
                //String address=sc.next();
                s.setAddress(sc.next());
                list.add(s);
                break;
            }
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {

                    if (id != list.get(i).getId()) {
                        System.out.println("请输入学生name：");
                        //String name =sc.next();
                        s.setName(sc.next());
                        System.out.println("请输入学生age：");
                        //int age =sc.nextInt();
                        s.setAge(sc.nextInt());
                        System.out.println("请输入学生address：");
                        //String address=sc.next();
                        s.setAddress(sc.next());
                        list.add(s);
                    } else {
                        System.out.println("输入id重复,请重新输入！");
                    }
                }
            } else {
                System.out.println("请输入学生name：");
                //String name =sc.next();
                s.setName(sc.next());
                System.out.println("请输入学生age：");
                //int age =sc.nextInt();
                s.setAge(sc.nextInt());
                System.out.println("请输入学生address：");
                //String address=sc.next();
                s.setAddress(sc.next());
                list.add(s);
            }
            break;
        }

    }

    //根据学号删除学生
    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("删除学生");
            System.out.println("请输入要删除的学号：");
            String id = sc.next();
            //boolean flag = certain(list, id);
            int num = arrList(list, id);
            if (num >= 0) {
                list.remove(num);
                break;
            } else {
                System.out.println("不存在");
            }
        }
    }

    //修改学生
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        Student s = new Student();
        System.out.println("修改学生");
        System.out.println("请输入需要修改的学生id:");
        String id = sc.next();
        int index = arrList(list, id);
        if (index == -1) {
            System.out.println("需要修改的id:" + id + "不存在，请重新输入！！！");
            return;
        }
        System.out.println("请输入要修改的学生name：");
        String newName = sc.next();
        s = list.get(index);
        s.setName(newName);
        System.out.println("请输入要修改的学生age：");
        int newAge = sc.nextInt();
        s.setAge(newAge);
        System.out.println("请输入要修改的学生address：");
        String newAddress = sc.next();
        s.setAddress(newAddress);
        //s.setName(newName);
    }

    //查询学生
    public static void queryStudent(ArrayList<Student> list) {
        System.out.println("查询学生");
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加后再查询");
        } else {
            System.out.println("id name age address");
            allList(list);
        }

    }

    //检查id是否存在
    public static boolean certain(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (id.equals(s.getId())) {
                return true;
            }

        }
        return false;
    }

    //遍历集合根据Student的id返回集合index，id不存在则返回-1；
    public static int arrList(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (id.equals(s.getId())) {
                return i;
            }
        }
        return -1;
    }

    //遍历集合
    public static void allList(ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getAddress());
        }
    }
}
