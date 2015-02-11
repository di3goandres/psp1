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

    public String GestorRegresion(List<ParNumber> datosCalculos, double proxy) {

        Regresion re = new Regresion(datosCalculos, proxy);
        return re.gerResult();

    }

}
