/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import obs.Observer;

/**
 *
 * @author User
 */
public class Konto extends AbstractListModel<KontoBenutzer> implements obs.Subject
{
    private double amount;
    private ArrayList<KontoBenutzer> user = new ArrayList();
    private ArrayList<Observer> obs = new ArrayList();

    public Konto()
    {
        amount = 50;
    }
    
    public void addUser(KontoBenutzer u)
    {
        user.add(u);
        fireIntervalAdded(this, user.size()-1, user.size()-1);
    }
    
    public void deleteUser(KontoBenutzer u)
    {
        user.remove(u);
        fireIntervalRemoved(this, user.size()-1, user.size()-1);
    }
    
    public void withdraw(double amount)
    {
        this.amount -= amount;
        inform();
    }
    
    public void deposit(double diff)
    {
        this.amount -= diff;
        inform();
    }

    @Override
    public int getSize()
    {
        return user.size();
    }

    @Override
    public KontoBenutzer getElementAt(int index)
    {
        return user.get(index);
    }

    public double getAmount()
    {
        return amount;
    }

    public ArrayList<KontoBenutzer> getUser()
    {
        return user;
    }

    @Override
    public void register(Observer obs)
    {
        this.obs.add(obs);
    }

    @Override
    public void deregister(Observer obs)
    {
        this.obs.remove(obs);
    }

    @Override
    public void inform()
    {
        for(Observer o: obs)
        {
            System.out.println("Inform");
            o.update();
        }
    }
    
    
    
    
    
}
