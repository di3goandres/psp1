/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.view;

import edu.uniandes.ecos.controller.GestorRegresion;
import edu.uniandes.ecos.model.Regresion;
import edu.uniandes.ecos.model.DesviacionEstandar;
import edu.uniandes.ecos.model.ParNumber;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author COCO
 */
public class PrettyPrinting {


    public static List<ParNumber> test1 = new LinkedList<ParNumber>();
    public static List<ParNumber> test2 = new LinkedList<ParNumber>();
    public static List<ParNumber> test3 = new LinkedList<ParNumber>();
    public static List<ParNumber> test4 = new LinkedList<ParNumber>();

    public static void main(String[] args) {
        try {
            CargarDatosIniciales();
            GestorRegresion gRegresion = new GestorRegresion();
            System.out.println("Test 1");
            System.out.println(gRegresion.GestorRegresion(test1, 386));
            System.out.println("Test 2");
            System.out.println(gRegresion.GestorRegresion(test2, 386));
            System.out.println("Test 3");
            System.out.println(gRegresion.GestorRegresion(test3, 386));
            System.out.println("Test 4");
            System.out.println(gRegresion.GestorRegresion(test4,386));


            
        } catch (NumberFormatException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void CargarDatosIniciales() {

        test1.add(new ParNumber(130, 186));
        test1.add(new ParNumber(650, 699));
        test1.add(new ParNumber(99, 132));
        test1.add(new ParNumber(150, 272));
        test1.add(new ParNumber(128, 291));
        test1.add(new ParNumber(302, 331));
        test1.add(new ParNumber(95, 199));
        test1.add(new ParNumber(945, 1890));
        test1.add(new ParNumber(368, 788));
        test1.add(new ParNumber(961, 1601));

        test2.add(new ParNumber(130, 15.0));
        test2.add(new ParNumber(650, 69.9));
        test2.add(new ParNumber(99, 6.5));
        test2.add(new ParNumber(150, 22.4));
        test2.add(new ParNumber(128, 28.4));
        test2.add(new ParNumber(302, 65.9));
        test2.add(new ParNumber(95, 19.4));
        test2.add(new ParNumber(945, 198.7));
        test2.add(new ParNumber(368, 38.8));
        test2.add(new ParNumber(961, 138.2));

        test3.add(new ParNumber(163, 186));
        test3.add(new ParNumber(765, 699));
        test3.add(new ParNumber(141, 132));
        test3.add(new ParNumber(166, 272));
        test3.add(new ParNumber(137, 291));
        test3.add(new ParNumber(355, 331));
        test3.add(new ParNumber(136, 199));
        test3.add(new ParNumber(1206, 1890));
        test3.add(new ParNumber(433, 788));
        test3.add(new ParNumber(1130, 1601));

        test4.add(new ParNumber(163, 15.0));
        test4.add(new ParNumber(765, 69.9));
        test4.add(new ParNumber(141, 6.5));
        test4.add(new ParNumber(166, 22.4));
        test4.add(new ParNumber(137, 28.4));
        test4.add(new ParNumber(355, 65.9));
        test4.add(new ParNumber(136, 19.4));
        test4.add(new ParNumber(1206, 198.7));
        test4.add(new ParNumber(433, 38.8));
        test4.add(new ParNumber(1130, 138.2));
    }
}
