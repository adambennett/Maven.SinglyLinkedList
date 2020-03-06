package com.zipcodewilmington.singlylinkedlist;

import java.util.ArrayList;

public class Model implements Comparable<Model> {

    private Integer someNumber;
    private String someWords;
    private Boolean someFlag;
    private ArrayList<FunctionHolder> functions;

    public Model(Integer someNumber, String someWords, Boolean someFlag) {
        this.someNumber = someNumber;
        this.someWords = someWords;
        this.someFlag = someFlag;
        this.functions = new ArrayList<>();
        this.functions.addAll(getFunctions());
    }


    public ArrayList<FunctionHolder> getFunctions() {
        ArrayList<FunctionHolder> toRet = new ArrayList<>();
        toRet.add((modelNum) -> (modelNum > this.someWords.length() && this.someFlag) ? true : false);
        return toRet;
    }

    public Boolean areAllFunctionsTrue() {
        return areAllFunctionsTrue(this.someNumber);
    }

    public Boolean areAllFunctionsTrue(Integer numToCheck) {
        Boolean ret = false;
        for (FunctionHolder fh : functions) {
            ret = fh.flagSet(numToCheck);
            if (!ret) { return ret; }
        }
        return ret;
    }

    @Override
    public int compareTo(Model o) {
        return (this.someNumber > o.getSomeNumber() && areAllFunctionsTrue()) ? -1 : (this.someNumber < o.getSomeNumber() || !areAllFunctionsTrue()) ? 1 : 0;
    }

    public Integer getSomeNumber() {
        return someNumber;
    }
}
