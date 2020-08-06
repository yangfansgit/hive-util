package com.yangfan.util;


import java.util.ArrayList;

public class GroupSetsUtil {

    private static String[] paramList;
    private static int [] wayList;
    private static int count1 = 0;
    private static int count2 = 0;

    /**
     * 根据num来判断如何打印
     * @param num 需要打印的元素数
     */
    private static void print(int num){
        int sum =0;
        ArrayList<String> arrayList = new ArrayList<String>();//临时存放打印的字符串
        for (int i = 0; i < wayList.length; i++){
            if (wayList[i] == 1){
                sum += 1;
                arrayList.add(paramList[i]);
            }
        }

        if(sum == num){
            System.out.print("("+arrayList.get(0));
//            System.out.print("(imei, "+arrayList.get(0));
            for(int i = 1; i <arrayList.size(); i++){
                System.out.print(", " + arrayList.get(i));
            }
            System.out.print("), ");
            count1++;
            count2++;
        }
    }

    /**
     * 递归，遍历所有情况
     * @param now 当前下标 从0开始
     * @param num 需要打印的元素数
     */
    private static void ergodic(int now, int num){
        if (now >= paramList.length) {
            print(num);
            return;
        }
        wayList[now] = 1;
        ergodic(now+1,num);
        wayList[now] = 0;
        ergodic(now+1,num);
    }

    /**
     * 打印所有的组合
     * @param paramLine 入参字符串 如："imei,city1,cate3"
     */
    public static void getCombination(String paramLine){
        paramList = paramLine.split(",");
        wayList = new int [paramList.length];

        for(int i = 0; i < paramList.length; i++) {
            paramList[i] = paramList[i].trim(); //去除多余空格
            wayList[i] = 0; //初始化路径
        }
        System.out.println("(), --1种");
        count1++;
        count2++;

        for(int i = 0; i <paramList.length; i ++){
            count1 = 0;
            ergodic(0,i+1);
            System.out.println(" --"+count1+"种");
        }
        System.out.println("--共"+count2+"种");
    }

    public static void main(String[] args) {
        // 添加备注
        String line1= "region_id,province_id,city_id,manager_id,cate1_id,cate2_id";
        String line= "imei,city1,cate3";
        String line2= "city1, cate3, cate4, platform, source";
        GroupSetsUtil.getCombination(line1);

    }
}
