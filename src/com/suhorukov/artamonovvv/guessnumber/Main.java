package com.suhorukov.artamonovvv.guessnumber;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 13.07.13
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Отгадайте случайное число в диапазоне 0..10");
        Game myGame = new Game(5, 11);
        do
        {
            String msg = myGame.tryGuess(scanner.nextInt());
            System.out.println(msg);
        }
        while (!myGame.checkGameStatus());
    }
}