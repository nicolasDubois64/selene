package com.capgemini.selene.test;

import com.capgemini.selene.model.SeleneDataEnum;

public class TestData {

    public static void main(String args[]){
        for(SeleneDataEnum data : SeleneDataEnum.values()){
            System.out.println(data.toString());
        }
    }
}
