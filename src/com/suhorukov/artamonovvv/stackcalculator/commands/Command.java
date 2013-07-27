package com.suhorukov.artamonovvv.stackcalculator.commands;

import java.util.EmptyStackException;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 17.07.13
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 */
public interface Command
{
    public void execute(String[] args) throws IllegalArgumentException, EmptyStackException;
}