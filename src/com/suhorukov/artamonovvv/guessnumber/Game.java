package com.suhorukov.artamonovvv.guessnumber;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 13.07.13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class Game
{
    Random rand;
    int countGuess;
    int currentGuess;
    int num;
    boolean endGame = false;

    public boolean checkGameStatus()
    {
        return endGame;
    }

    public Game(int countGuess, int maxNumber)
    {
        rand = new Random(System.currentTimeMillis());
        this.countGuess = countGuess;
        currentGuess = 0;
        num = rand.nextInt(maxNumber);
    }

    public String tryGuess(int tempNum)
    {
        String rez = "";
        if (currentGuess < countGuess)
        {
            currentGuess++;
            if (tempNum == num)
            {
                endGame = true;
                rez = "Вы угадали число!";
            } else
            {
                if (tempNum > num)
                {
                    rez = "Ваше число больше искомого";
                } else
                {
                    rez = "Ваше число меньше искомого";
                }
            }
        } else
        {
            endGame = true;
            rez = "Превышено количество попыток";
        }

        return rez;
    }
}