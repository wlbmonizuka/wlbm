package com.suhorukov.artamonovvv.stackcalculator.commands;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class BinaryOperatorPlus extends BinaryOperator
{
    @Override
    protected double getResult(double num1, double num2)
    {
        return num1 + num2;
    }
}