package day7;

import org.junit.jupiter.api.Test;

public class WhatIsVarArgs {

    public static void addAllNumbers(int [] num){

        int sum =0;

        for(int eachNumber: num){
            sum +=eachNumber;
        }
        System.out.println(sum);
    }

    public static void addAllNumbersVarArgs(int...nums){

        int sum =0;

        for(int eachNumber: nums){
            sum +=eachNumber;
        }
        System.out.println(sum);

    }
    @Test
    public void testAdd(){

        addAllNumbers(new int []{1,2,3,5,6});

        addAllNumbersVarArgs(1,2,3,4,5,7);


    }


}
