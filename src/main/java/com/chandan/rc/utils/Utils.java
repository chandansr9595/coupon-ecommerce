package com.chandan.rc.utils;

import com.chandan.rc.constants.DealsConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.chandan.rc.constants.DealsConstants.*;

public class Utils {

    private static Random r = new Random();

    public static boolean isBlank(String str){
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str){
        return str != null && str.trim().length() > 0;
    }

    public static int getDealPercentage(){
        return r.nextInt(MAX_DEAL_PERCENTAGE - MIN_DEAL_PERCENTAGE) + MIN_DEAL_PERCENTAGE;
    }

    public static long getDealAvailableTill(){
        return System.currentTimeMillis() + DealsConstants.DEAL_AVAILABLE_TILL;
    }

    public static List<Integer> getListOfRandomNumbers(int size){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) list.add(i % 2);
        Collections.shuffle(list);
        return list;
    }
}
