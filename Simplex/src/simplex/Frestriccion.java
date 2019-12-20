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
public class Frestriccion {
     public ArrayList<Double> variables = new ArrayList<>();
     public int signo;
     public double vIndep;
     public int nVariables;
     public String txt;

    public ArrayList<Double> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Double> variables) {
        this.variables = variables;
    }

    public int getSigno() {
        return signo;
    }

    public void setSigno(int signo) {
        this.signo = signo;
    }

    public double getvIndep() {
        return vIndep;
    }

    public void setvIndep(double vIndep) {
        this.vIndep = vIndep;
    }

    public int getnVariables() {
        return nVariables;
    }

    public void setnVariables(int nVariables) {
        this.nVariables = nVariables;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

   

   

   
    
}
