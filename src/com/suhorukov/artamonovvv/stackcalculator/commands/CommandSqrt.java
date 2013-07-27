package com.suhorukov.artamonovvv.stackcalculator.commands;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandField;
import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandQuery;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
public class CommandSqrt implements Command
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

        try
        {
            Double num = stack.pop();
            num = Math.sqrt(num);
            stack.push(num);
        } catch (EmptyStackException ex)
        {
            throw ex;
        }
    }
}