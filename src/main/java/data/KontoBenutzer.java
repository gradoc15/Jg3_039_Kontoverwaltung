/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class KontoBenutzer extends Thread
{
    Random rand = new Random();
    
    private gui.ThreadState st;
    
    private String name = "";
    private Konto k;
    private JTextArea display;

    public KontoBenutzer(String name, Konto k, JTextArea display)
    {
        this.name = name;
        this.k = k;
        this.display = display;
        this.st = st;
    }
    
    public String getUsername()
    {
        return name;
    }

    @Override
    public void run()
    {
        
        int amount = -1;
        
        for(int i = 0; i < 10; i++)
        {
            st.setThreadRunning();
            System.out.println("in");
            try
            {
                st.setThreadSleeping();
                Thread.sleep(rand.nextInt(1000)+1);
                st.setThreadRunning();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(KontoBenutzer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
               if(amount == -1)
                    amount =  rand.nextInt(51)+10;
               
                if(rand.nextInt(2) == 1)
                {
                    //Minus
                    
                    System.out.println(amount);
                    synchronized(k)
                    {
                        if(amount > k.getAmount())
                            try {
                                st.setThreadWaiting();
                                k.wait();
                                i--;
                                continue;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(KontoBenutzer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        else
                        {
                            display.append(Thread.currentThread().getName()+" makes withdrawel: -"+amount+"\n");
                            k.withdraw(amount);
                            amount = -1;
                        }
                    }
                }
                else
                {
                    synchronized(k)
                    {
                        display.append(Thread.currentThread().getName()+" makes withdrawel: "+amount+"\n");
                        k.deposit(amount);
                        amount = -1;
                        k.notifyAll();
                    }
                    
                }
        }
        st.setThreadFinished();
        display.append(currentThread().getName()+" has finished");
    }
    
    public void setThreadState(gui.ThreadState st)
    {
        this.st = st;
    }
    
    public String toString()
    {
        return name;
    }
    
    
}
