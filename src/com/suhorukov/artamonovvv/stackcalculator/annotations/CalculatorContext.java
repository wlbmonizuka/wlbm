package com.suhorukov.artamonovvv.stackcalculator.annotations;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */
public interface CalculatorContext
{
    Stack<Double> stack();

    Map<String, Double> define();
}