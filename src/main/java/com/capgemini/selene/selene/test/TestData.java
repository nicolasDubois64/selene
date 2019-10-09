package com.capgemini.selene.selene.test;

import com.capgemini.selene.selene.model.SeleneData;

public class TestData {

    public static void main(String args[]){
        for(SeleneData data : SeleneData.values()){
            System.out.println(data.getTest());
        }
    }
}
