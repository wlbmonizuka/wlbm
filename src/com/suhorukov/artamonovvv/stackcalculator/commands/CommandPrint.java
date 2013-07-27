package com.suhorukov.artamonovvv.stackcalculator.commands;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandField;
import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandQuery;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class CommandPrint implements Command
{
    @CommandQuery(fields = CommandField.FIELD_STACK)
    private Stack<Double> stack = null;

    @Override
    public void execute(String[] args) throws IllegalArgumentException, EmptyStackException
    {
        if (args.length != 1)
        {
            throw new IllegalArgumentException("Неверный формат строки");
        }

        try
        {
            Double num = stack.peek();
            System.out.println(num);
        } catch (EmptyStackException ex)
        {
            throw new EmptyStackException()
            {
                @Override
                public String getMessage()
                {
                    return "Стeк пуст";
                }
            };
        }
    }
}