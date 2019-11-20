package com.cai.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 轮询获取ip
 */
@Service
public class PollingIp {
    static int i = 0;
    public String getPollingIp(){
        //1.获取ip
        //缓存到数据库,如果缓存不存在去数据库查，存在直接获取
        //
        String ip =null;
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("ip0");
        arrayList.add("ip1");
        arrayList.add("ip2");
        arrayList.add("ip3");
        if(arrayList.size()>0){
            int nextIndex = (i + 1) % arrayList.size();
            i = nextIndex;
            System.out.println(arrayList.get(i) + " ,index=" + i);
            ip = arrayList.get(i);
        }
        /*for (int j = 0; j < arrayList.size(); j++) {
            if(arrayList.size() == i){
                i = 0;
            }
            ip = arrayList.get(i);
            i++;
            break;
        }*/
        return ip;
    }

/*    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            System.out.println(getPollingIp());
        }

    }*/
}
