package com.suhorukov.artamonovvv.stackcalculator.commands;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandField;
import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandQuery;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class CommandDefine implements Command
{
    @CommandQuery(fields = CommandField.FIELD_DEFINE)
    private Map<String, Double> define = null;

    @Override
    public void execute(String[] args)
    {
        if (args.length != 3)
        {
            throw new IllegalArgumentException("Неверный формат строки");
        }

        try
        {
            Double num = new Double(args[2]);
            define.put(args[1], num);
        } catch (NumberFormatException ex)
        {
            throw new IllegalArgumentException("Неверный формат строки", ex);
        }
    }
}