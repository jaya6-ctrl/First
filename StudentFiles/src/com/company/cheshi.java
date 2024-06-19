package com.company;

import java.util.Random;

public class cheshi {
    public static void main(String[] args) {
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
        String temp=s[index];
        s[index]=s[s.length-1];
        s[s.length-1]=temp;
        for (int i = 0; i < s.length; i++) {
            captcha=captcha+s[i];
        }
        System.out.println(captcha);
    }
}
