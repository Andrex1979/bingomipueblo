package com.bingomipueblo.jsf.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Adsit
 */
@Named(value = "JuegoController")
@SessionScoped
public class JuegoController implements Serializable {

    private List<Integer> Vnumeros = null;

    private List<List<Integer>> tablas = null;

    public JuegoController() {

    }

    public List<List<Integer>> getTablas() {
        tablas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tablas.add(getVnumeros());

        }
        return tablas;
    }

    public List<Integer> getVnumeros() {
        Vnumeros = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int n;
                for (n = 15 * j + (int) (Math.random() * 15D) + 1; Vnumeros.contains(Integer.valueOf(n)); n = 15 * j + (int) (Math.random() * 15D) + 1);
                if (i == 2 && j == 2) {
                    Vnumeros.add(Integer.valueOf(0));
                } else {
                    Vnumeros.add(Integer.valueOf(n));
                }
            }
        }
        return Vnumeros;
    }
// private List<List<Integer>> tselecion =tablas;
//    public List<List<Integer>> getTselecion() {
//        tselecion = new ArrayList<>();
//          for (int i = 0; i < 3; i++) {
//              tselecion.add(getVnumeros());
//              
//              
//          }
//        return tselecion;
//    }
     

    List<Integer> miLista = Arrays.asList(1, 2, 3, 41);

    public List<Integer> getMiLista() {
        return miLista;
    }

}
