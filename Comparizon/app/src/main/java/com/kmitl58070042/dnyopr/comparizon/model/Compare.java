package com.kmitl58070042.dnyopr.comparizon.model;


public class Compare {

    private String result;



    public String findCheaperItem(float costA, float sizeA, float costB, float sizeB) {
        float netA = costA / sizeA;
        float netB = costB / sizeB;

        if (netA<netB){
            result = "a item on the left is cheaper!";
        }else if (netA>netB){
            result = "a item on the right is cheaper!";
        }else {
            result = "same!!";
        }

        return result;
    }
}
