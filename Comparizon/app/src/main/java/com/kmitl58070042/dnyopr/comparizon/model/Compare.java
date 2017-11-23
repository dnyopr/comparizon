package com.kmitl58070042.dnyopr.comparizon.model;


public class Compare {

    private String result;



    public String findCheaperItem(float costA, float sizeA, float costB, float sizeB) {
        float netA = costA / sizeA;
        float netB = costB / sizeB;

        if (netA<netB){
            result = "A";
        }else if (netA>netB){
            result = "B";
        }else {
            result = "same";
        }

        return result;
    }
}
