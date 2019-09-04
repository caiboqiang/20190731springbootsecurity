package com.cai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class test {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(10);
        user.setName("A");
        User userb = new User();
        userb.setAge(10);
        userb.setName("AA");
        User userc = new User();
        userc.setAge(103);
        userc.setName("AAA");
        User userD = new User();
        userD.setAge(103);
        userD.setName("AAAA");
        ArrayList<User> a=new ArrayList<User>();
        a.add(user);
        a.add(userb);
        a.add(userc);
        a.add(userD);
        Collections.sort(a, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if(o1.getAge() > o2.getAge()){
                    return -1;
                }
                else if(o1.getAge() > o2.getAge()){
                    return 1;
                }
                else{
                    return 0;
                }
            }

        });

        for (User user1 :a){
            System.out.println(user1.toString());
        }

    }


}
class User{
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}