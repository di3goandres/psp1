/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.controller;

import edu.uniandes.ecos.model.ParNumber;
import edu.uniandes.ecos.model.Regresion;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author COCO
 */
public class GestorRegresion {

    public String GestorRegresion(List<ParNumber> datosCalculos) {

        List<Double> numberX = new LinkedList<Double>();
        List<Double> numberY = new LinkedList<Double>();

        int tamanioLista = datosCalculos.size();
        for (ParNumber pares : datosCalculos) {

            numberX.add(pares.getX());
            numberY.add(pares.getY());

        }
        Regresion re = new Regresion(numberX, numberY, tamanioLista);
        return re.gerResult();

    }

}
