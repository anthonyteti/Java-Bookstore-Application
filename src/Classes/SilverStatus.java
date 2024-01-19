/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author arian
 */
public class SilverStatus extends Status implements Serializable
{
    
    public double[] buy(ArrayList<Book> cart, int points, boolean isPurchasingWithPoints)
    {
        double totalPrice = 0.0;
        double deltaPoints = 0.0;
        double finalPrice = totalPrice;
            
        
        for (Book book : cart) 
        {
            totalPrice += book.getPrice();
        }
        
        if (isPurchasingWithPoints) {
            deltaPoints -= Math.min(points, totalPrice * 100);
            finalPrice = Math.max(totalPrice - (points / 100.0), 0);
        } else {
            deltaPoints += totalPrice * 10.0;
            finalPrice = totalPrice;
        }

        return new double[] {finalPrice, deltaPoints};
    }
}
