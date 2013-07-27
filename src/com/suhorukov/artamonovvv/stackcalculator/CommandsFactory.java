package com.suhorukov.artamonovvv.stackcalculator;

import com.suhorukov.artamonovvv.stackcalculator.annotations.CalculatorContext;
import com.suhorukov.artamonovvv.stackcalculator.annotations.CommandQuery;
import com.suhorukov.artamonovvv.stackcalculator.commands.Command;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class CommandsFactory
{
    private HashMap<String, Command> dic;

    public Command getCommandFromUserString(String comName)
    {
        return dic.get(comName);
    }

    public Command getCommand(String s) throws IllegalArgumentException
    {
        if (s.isEmpty())
        {
            throw new IllegalArgumentException("Строка пустая");
        }
        String[] args = s.split(" ");
        Command com = dic.get(args[0]);
        if (com == null)
        {
            throw new IllegalArgumentException("Неизвестная команда " + args[0]);
        }
        return com;
    }

    private final static Logger logger = Logger.getLogger(CommandsFactory.class);

    public CommandsFactory(final CalculatorContext context) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        Properties prop = new Properties();
        InputStream stream = getClass().getResourceAsStream("commands/commands.properties");
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        prop.load(reader);
        stream.close();
        System.out.println(prop.toString());
        dic = new HashMap<>();
        for (Object key : prop.keySet())
        {
            String keyStr = key.toString();
            String className = prop.getProperty(keyStr);
            Class cls = Class.forName(className);
            Object obj = cls.newInstance();
            if (obj instanceof Command)  //Если загрузен нужный класс
            {
                final Command realCommand = (Command) obj;
                InvocationHandler handler = new InvocationHandler()
                {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {
                        //if ("execute".equals(method.getName())){ }
                        logger.debug("-------------------------");
                        logger.debug("Вызван метод " + method.getName() + " объекта типа " + realCommand.getClass().getName());
                        logger.debug("Стек до вызова команды: " + context.stack());
                        Object rez = method.invoke(realCommand, args);
                        logger.debug("Стек после вызова команды: " + context.stack());
                        logger.debug("-------------------------");
                        return rez;
                    }
                };
                Command proxyCommand = (Command) Proxy.newProxyInstance
                        (
                                Command.class.getClassLoader(),
                                new Class[]{Command.class},
                                handler
                        );
                //тут добавить прокси
                dic.put(keyStr, proxyCommand);
            }
            while (cls != Object.class)
            {
                Field[] fields = cls.getDeclaredFields();
                for (Field f : fields)
                {
                    CommandQuery anno = f.getAnnotation(CommandQuery.class);
                    if (anno != null)
                    {
                        f.setAccessible(true);
                        switch (anno.fields())
                        {
                            case FIELD_DEFINE:
                                f.set(obj, context.define());
                                break;
                            case FIELD_STACK:
                                f.set(obj, context.stack());
                                break;
                        }
                    }
                }
                cls = cls.getSuperclass();
            }
        }
    }
}