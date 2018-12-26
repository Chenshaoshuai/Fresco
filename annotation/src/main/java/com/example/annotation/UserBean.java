package com.example.annotation;

public class UserBean {
    private String mStr = "not init";


    public String getStr() {
        return mStr;
    }

    public void setStr(String str) {
        changeStr(str);
    }


    private void changeStr(String str){
        mStr = str;
    }

    private String changeStr123(String str){
        mStr = str;
        return "aaaa";
    }

    private void initStr(){
        mStr = "init success";
    }
}
