/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.ArrayList;

/**
 *
 * @author Mors
 */
public class Fobjetivo {
    public ArrayList<Double> variables = new ArrayList<>();
    public boolean mixMax;
    public double vIndep;
    public String txt;

    public ArrayList<Double> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Double> variables) {
        this.variables = variables;
    }

    public boolean isMixMax() {
        return mixMax;
    }

    public void setMixMax(boolean mixMax) {
        this.mixMax = mixMax;
    }

    public double getvIndep() {
        return vIndep;
    }

    public void setvIndep(double vIndep) {
        this.vIndep = vIndep;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

   
  

   
}
