package com.suhorukov.artamonovvv.stackcalculator.commands;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandField;
import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandQuery;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class BinaryOperator implements Command
{
    @CommandQuery(fields = CommandField.FIELD_STACK)
    private Stack<Double> stack = null;

    @Override
    public void execute(String[] args)
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Неверный формат строки");
        }

        if (stack.size() < 2)
        {
            throw new EmptyStackException();
        }

        Double num2 = stack.pop();
        Double num1 = stack.pop();
        stack.push(getResult(num1, num2));
    }

    protected abstract double getResult(double num1, double num2);
}