/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.model;

import edu.uniandes.ecos.model.DesviacionEstandar;
import java.util.LinkedList;
import java.util.List;

/**
 * XX
 *
 * @author COCO
 */
public class Regresion {

    public List<Double> listasNumerosX = new LinkedList<Double>();
    public List<Double> listasNumerosY = new LinkedList<Double>();
    public double mediaX;
    public double mediaY;
    public double SumXY;
    public double powX;
    public int nDat;
    public double proxy;

    /**
     *
     * @param numX
     * @param numY
     */
    public Regresion(List<ParNumber> datosCalculos, double proxyP) {

        this.proxy = proxyP;
        this.nDat = datosCalculos.size();
        for (ParNumber pares : datosCalculos) {
            this.listasNumerosX.add(pares.getX());
            this.listasNumerosY.add(pares.getY());
        }
        this.SumXY = getSumXY(datosCalculos);
        this.powX = getPowXorY(this.listasNumerosX);
        mediaX = getmediaXorY(listasNumerosX);
        mediaY = getmediaXorY(listasNumerosY);
    }

    public double getSumXY(List<ParNumber> datosCalculosP) {
        double total = 0;
        for (ParNumber pares : datosCalculosP) {
            total = total + (pares.getX() * pares.getY());
        }
        return total;
    }

    public double getPowXorY(List<Double> num) {
        double sqrX = 0;
        for (int i = 0; i < num.size(); i++) {
            sqrX = sqrX + (num.get(i) * num.get(i));
        }
        return sqrX;
    }

    public double getSumXorY(List<Double> num) {
        double sum = 0;
        for (int i = 0; i < num.size(); i++) {
            sum = sum + (num.get(i));
        }
        return sum;
    }

    public double getParameterBOne() {
        double parameterB = 0;
        parameterB = ((SumXY) - (nDat * mediaX * mediaY)) / ((powX) - (nDat * (mediaX * mediaX)));
        return parameterB;
    }

    public double getParameterBCero() {
        double parameterBOne = 0;
        parameterBOne = this.mediaY - (getParameterBOne() * this.mediaX);
        return parameterBOne;

    }

    public double getParameterYk(double numXk) {

        double parameterYK = 0;
        parameterYK = getParameterBCero() + (numXk) * getParameterBOne();
        return parameterYK;
    }

    public double getRegresionXY() {
        double parameterRegresionXY = 0;
        double sumX = getSumXorY(listasNumerosX);
        double sumY = getSumXorY(listasNumerosY);

        double powX = (((nDat * (getPowXorY(listasNumerosX))) - Math.pow(sumX, 2)));
        double powY = (((nDat * (getPowXorY(listasNumerosY))) - Math.pow(sumY, 2)));

        parameterRegresionXY = ((nDat * SumXY) - (sumX * sumY)) / Math.sqrt(powX * powY);

        return parameterRegresionXY;
    }

    public double getR2() {

        double Rcuadrado = getRegresionXY();
        return Math.pow(Rcuadrado, 2);
    }

    public double getmediaXorY(List<Double> num) {
        DesviacionEstandar calculos = new DesviacionEstandar();
        calculos.DesviacionEstandarAdd(num);
        return calculos.MediaAritmetica();

    }

    public String gerResult() {

        String retorno = "Bo= " + getParameterBCero() + "<br>";
        retorno = retorno + "B1 =" + getParameterBOne() + "<br>";
        retorno = retorno + "r(x,y) =" + getRegresionXY() + "<br>";
        retorno = retorno + "r*r =" + getR2() + "<br>";
        retorno = retorno + "Yk =" + getParameterYk(this.proxy) + "<br>";

        return retorno;

    }
}
