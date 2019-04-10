/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Arrays;
import obs.Observer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author User
 */
@RunWith(value = Parameterized.class)
public class KontoTest
{
    @Parameterized.Parameter(value = 0)
        public double amount;
    
    @Parameterized.Parameters(name = "")
    public static Iterable<Object[]> data1()
    {
        return Arrays.asList(new Object[][]
        {
            {5},
            {30},
            {10} 
        });
                 
    }  
    public KontoTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of withdraw method, of class Konto.
     */
    @Test
    public void testWithdraw()
    {
        System.out.println("withdraw");
        double amount = 0.0;
        Konto instance = new Konto();
        instance.withdraw(amount);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(instance.getAmount(), 50-amount, 0.1);
    }

    /**
     * Test of deposit method, of class Konto.
     */
    @Test
    public void testDeposit()
    {
        System.out.println("deposit");
        double amount = this.amount;
        Konto instance = new Konto();
        instance.deposit(amount);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(instance.getAmount(), 50+amount, 0.1);
    }
    
}
