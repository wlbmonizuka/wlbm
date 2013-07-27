package com.suhorukov.artamonovvv.stackcalculator;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: WLBM_Onizuka
 * Date: 24.07.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class CalcTest extends TestCase
{
    static
    {
        DOMConfigurator.configure(CalcTest.class.getResource("log4j.xml"));
    }

    private double simpleCalcFromFile(String fileName)
    {
        InputStream stream = getClass().getResourceAsStream(fileName);
        StackBasedCalculator calc = new StackBasedCalculator();
        calc.run(stream);
        return calc.getRezult();
    }

    public void test1() throws UnsupportedEncodingException
    {
        Assert.assertEquals(simpleCalcFromFile("test1.txt"), 10.0, 1.e-6);
    }

    public void test2() throws UnsupportedEncodingException
    {
        Assert.assertEquals(simpleCalcFromFile("test2.txt"), 20.0, 1.e-6);
    }

    public void testQuadraticEquation1() throws IOException
    {
        quadraticEquation(1, 0, -9);
        quadraticEquation(3, 1, -20);
        quadraticEquation(2, 2, -6);
        quadraticEquation(1, 1, -6);
    }

    private void quadraticEquation(double a, double b, double c) throws IOException
    {
        StackBasedCalculator calc = new StackBasedCalculator();
        calc.executeCommand("DEFINE a " + a);
        calc.executeCommand("DEFINE b " + b);
        calc.executeCommand("DEFINE c " + c);
        try (InputStream stream = getClass().getResourceAsStream("X1.txt"))
        {
            calc.run(stream);
        }
        double x1 = calc.getRezult();
        try (InputStream stream = getClass().getResourceAsStream("X2.txt"))
        {
            calc.run(stream);
        }
        double x2 = calc.getRezult();
        Assert.assertEquals(a * x1 * x1 + b * x1 + c, 0.0, 1.e-6);
        Assert.assertEquals(a * x2 * x2 + b * x2 + c, 0.0, 1.e-6);
    }
}