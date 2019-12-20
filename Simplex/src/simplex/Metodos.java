/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static simplex.Agregar.banTipo;
import static simplex.Agregar.func;
import static simplex.Agregar.nVar;
import static simplex.Agregar.signo;
import static simplex.Agregar.vIndep;

/**
 *
 * @author Mors
 */
public class Metodos {

    public static ArrayList<Double> funcion = new ArrayList<>();
    private double var;


    public void estandarizar(Fobjetivo funcionO,ArrayList<Frestriccion> Restricciones) {
        int nVar = 1;
        int nNuevas = 0;

        for (int i = 0; i < Restricciones.size(); i++) {
            if (Restricciones.get(i).nVariables > nVar) {
                nVar = Restricciones.get(i).nVariables;
            }
            if (Restricciones.get(i).signo != 1) {
                nNuevas++;
            }
        }
        
        int cont = nVar;
        int total = nVar + nNuevas;
        boolean banReg;
        
         double varTemp;
        double indepTemp;
        if (funcionO.mixMax == true) {
            ArrayList<Double> variables = funcionO.variables;
            int n = variables.size();
            for (int i = 0; i < total; i++) {
                if (i<n) {
                   varTemp = funcionO.variables.get(i) * -1;
                funcionO.variables.set(i, varTemp);                 
                }else{
                funcionO.variables.add(0.0);                
                }
                
            }
            indepTemp = funcionO.vIndep * -1;
            funcionO.setvIndep(indepTemp);
            funcionO.setMixMax(false);
        }else{
            for (int i = funcionO.variables.size(); i < total; i++) {
              funcionO.variables.add(0.0);   
            }
        }
        System.out.println("Min:    ");
        presentar(funcionO.variables, 1, funcionO.vIndep);
        System.out.println("");
        
        
        ArrayList<Double> tempVariables = new ArrayList<>();;
        for (int i = 0; i < Restricciones.size(); i++) {
            banReg = true;
            tempVariables = Restricciones.get(i).variables;
            for (int j = 1; j < total; j++) {

                if (tempVariables.size() == total) {
                    break;
                }
                if (tempVariables.size() < nVar) {
                    tempVariables.add(0.0);
                } else {
                    if (banReg) {
                        if (tempVariables.size() == cont) {
                            switch (Restricciones.get(i).signo) {
                                case 0:
                                    tempVariables.add(-1.0);
                                    banReg = false;
                                    break;
                                case 1:
                                    cont--;
                                    banReg = false;
                                    break;
                                case 2:
                                    tempVariables.add(1.0);
                                    banReg = false;
                                    break;
                            }
                        } else {
                            tempVariables.add(0.0);
                            if (tempVariables.size() == total) {
                                break;
                            }
                        }
                    } else {
                        tempVariables.add(0.0);

                    }
                }
            }

            cont++;
            Restricciones.get(i).setVariables(tempVariables);
            Restricciones.get(i).setSigno(1);
            presentar(Restricciones.get(i).variables, Restricciones.get(i).signo, Restricciones.get(i).vIndep);
        }

    }

    public void presentar(ArrayList variables, int signo, double indep) {
        String f = "|";
        for (int i = 0; i < variables.size(); i++) {
            f = f + "   "+variables.get(i) + "    |";
        }
       f = f + "|   "+indep + "  |";
       
        System.out.println(f);
    }

    public void agregar() {
        ArrayList<Double> funcionTemp = new ArrayList<>();
        for (int i = 0; i < nVar; i++) {
            if (i == nVar - 1) {
                vIndep = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor independiente"));
                func = func + " " + vIndep;
            } else {

                if (i == nVar - 2) {
                    if (banTipo == false) {
                        signo = JOptionPane.showOptionDialog(null, "Seleccione el operador",
                                "Ingresar funcion", JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, new Object[]{"≥", "=", "≤"}, "≥");
                        // System.out.println(signo);
                        switch (signo) {
                            case 0:
                                func = func + " ≥";
                                break;
                            case 1:
                                func = func + " =";
                                break;
                            case 2:
                                func = func + " ≤";
                                break;
                        }
                    } else {
                        signo = 1;
                        func = func + " =";
                    }

                } else {
                    var = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el coeficiente de x" + (i + 1)));
                    funcionTemp.add(var);
                    if (i == 0) {
                        func = "(" + var + "x" + (i + 1) + ")";
                    } else {
                        func = func + "+(" + var + "x" + (i + 1) + ")";
                    }
                }
            }
        }
        funcion = funcionTemp;
    }

    public void resolver(Fobjetivo Fobj, ArrayList<Frestriccion> Restricciones) {
        double varP = 0;
        int pos = -1;
        double pivote = -1;
        int posPiv = -1;
        for (int i = 0; i < Fobj.variables.size(); i++) {
            if (Fobj.variables.get(i) < varP) {
                varP = Fobj.variables.get(i);
                pos = i;
            }
        }
        if (pos == -1) {
            System.out.println("Fin del programa. No se puede minimizar mas");
        } else {
            for (int j = 0; j < Restricciones.size(); j++) {
                if (j == 0) {
                    pivote = Restricciones.get(j).vIndep / Restricciones.get(j).variables.get(pos);
                    posPiv = j;
                } else {
                    if (Restricciones.get(j).variables.get(pos) >= 0) {
                        if (pivote<0) {
                             pivote = Restricciones.get(j).vIndep / Restricciones.get(j).variables.get(pos);
                            posPiv = j;
                        }else{
                        if (Restricciones.get(j).vIndep / Restricciones.get(j).variables.get(pos) < pivote) {
                            pivote = Restricciones.get(j).vIndep / Restricciones.get(j).variables.get(pos);
                            posPiv = j;
                        }
                    }
                    }
                }
            }
            if (pivote < 0) {
                System.out.println("Fin de programa. No se puede pivotear");
            } else {
                System.out.println("Termino seleccionado X"+(pos+1));
                System.out.println("Pivote en R"+(posPiv+1));
                //REDUCIR A 1 PIVOTE
                ArrayList<Double> varT = new ArrayList<>();
                double temp=0;
                double tempInd = 0;
                for (int j = 0; j < Restricciones.get(posPiv).variables.size(); j++) {                 
                    temp =  Restricciones.get(posPiv).variables.get(j)/Restricciones.get(posPiv).variables.get(pos) ;
                    tempInd = Restricciones.get(posPiv).vIndep / Restricciones.get(posPiv).variables.get(pos);
                    varT.add(temp);
                }
                Restricciones.get(posPiv).setVariables(varT);
                Restricciones.get(posPiv).setvIndep(tempInd);
                //PIVOTEO
                
                for (int j = 0; j < Restricciones.size(); j++) {
                    ArrayList<Double> varT2 = new ArrayList<>();
                    if (j != posPiv) {
                        for (int k = 0; k < Restricciones.get(j).variables.size(); k++) {
                            temp = Restricciones.get(j).variables.get(k) + (-1 * Restricciones.get(j).variables.get(pos) * Restricciones.get(posPiv).variables.get(k));
                            varT2.add(temp);
                        }
                        tempInd = Restricciones.get(j).vIndep + (-1 * Restricciones.get(j).variables.get(pos) * Restricciones.get(posPiv).vIndep);
                        Restricciones.get(j).setVariables(varT2);
                        Restricciones.get(j).setvIndep(tempInd);
                    }

                }
                //CALCULO VAR OBJ
                ArrayList<Double> varT3 = new ArrayList<>();
                for (int j = 0; j < Fobj.variables.size(); j++) {
                    temp = Fobj.variables.get(j) + (-1 * Fobj.variables.get(pos) * Restricciones.get(posPiv).variables.get(j));
                    varT3.add(temp);
                }
                tempInd = Fobj.vIndep + (-1 * Fobj.variables.get(pos) * Restricciones.get(posPiv).vIndep);
                Fobj.setVariables(varT3);
                Fobj.setvIndep(tempInd);

                presentar(Fobj.variables, 1, Fobj.vIndep);
                System.out.println("");
                for (int i = 0; i < Restricciones.size(); i++) {
                     presentar(Restricciones.get(i).variables, Restricciones.get(i).signo, Restricciones.get(i).vIndep);
                }
               
            }
        }
    }

}
