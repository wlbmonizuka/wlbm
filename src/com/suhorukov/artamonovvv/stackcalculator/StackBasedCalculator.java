package com.suhorukov.artamonovvv.stackcalculator;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CalculatorContext;
import com.suhorukov.artamonovvv.stackcalculator.commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
public class StackBasedCalculator
{
    CommandsFactory factory;
    Stack<Double> stack = new Stack<>();

    HashMap<String, Double> define = new HashMap<>();

    public CalculatorContext getContext()
    {
        return new CalculatorContext()
        {
            @Override
            public Stack<Double> stack()
            {
                return stack;
            }

            @Override
            public HashMap<String, Double> define()
            {
                return define;
            }
        };
    }

    public StackBasedCalculator()
    {
        try
        {
            factory = new CommandsFactory(getContext());
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e)
        {
            e.printStackTrace();
        }
    }

    public void run(InputStream in)
    {
        if (factory == null)
        {
            System.out.println("Не получается загрузить команды");
            return;
        }
        Scanner scanner = new Scanner(in);
        while (true)
        {
            System.out.println("Введите команду:");
            String userString = scanner.nextLine();
            if ("EXIT".equals(userString))
            {
                System.out.println("Выход");
                return;
            }
            executeCommand(userString);
        }
    }

    public void executeCommand(String userString)
    {
        System.out.println("Выполняем: " + userString);
        String[] args = userString.split(" ");
        if (args.length == 0)
        {
            return;
        }
        Command com = factory.getCommandFromUserString(args[0]);
        if (com != null)
        {
            try
            {
                com.execute(args);
            } catch (Exception excep)
            {
                System.out.println(excep.getMessage());
            }
        } else
        {
            System.out.println("Неизвестная команда");
        }
    }

    public double getRezult()
    {
        return stack.peek();
    }
}