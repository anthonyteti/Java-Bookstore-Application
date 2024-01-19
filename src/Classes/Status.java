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
public abstract class Status{
    public abstract double[] buy(ArrayList<Book> cart, int points, boolean isPurchasingWithPoints);
}
