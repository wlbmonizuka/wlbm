package com.suhorukov.artamonovvv.stackcalculator.commands;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandField;
import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandQuery;

import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 19:50
 * To change this template use File | Settings | File Templates.
 */
public class CommandPush implements Command
{
    @CommandQuery(fields = CommandField.FIELD_STACK)
    private Stack<Double> stack = null;

    @CommandQuery(fields = CommandField.FIELD_DEFINE)
    private Map<String, Double> define = null;

    @Override
    public void execute(String[] args) throws IllegalArgumentException
    {
        if (args.length != 2)
        {
            throw new IllegalArgumentException("Неверный формат строки");
        }

        try
        {
            Double num = new Double(args[1]);
            stack.push(num);
        } catch (NumberFormatException ex)
        {
            Double num = define.get(args[1]);
            if (num != null)
            {
                stack.push(num);
            } else
            {
                throw new IllegalArgumentException("Неверный формат строки", ex);
            }
        }
    }
}