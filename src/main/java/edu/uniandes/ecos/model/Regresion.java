/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.model;

import edu.uniandes.ecos.model.DesviacionEstandar;
import java.util.List;

/**
 * XX
 *
 * @author COCO
 */
public class Regresion {

    public List<Double> listasNumerosX;
    public List<Double> listasNumerosY;
    public double mediaX;
    public double mediaY;
    public double SumXY;
    public double powX;
    public int nDat;
    public double R2;

    /**
     *
     * @param numX
     * @param numY
     */
    public Regresion(List<Double> numX, List<Double> numY, int totaldatos) {

        this.listasNumerosX = numX;
        this.listasNumerosY = numY;

        mediaX = getmediaXorY(listasNumerosX);
        mediaY = getmediaXorY(listasNumerosY);
        this.nDat = totaldatos;
        this.SumXY = getSumXY();
        this.powX = getPowXorY(this.listasNumerosX);
    }

    public double getSumXY() {
        double total = 0;
        for (int i = 0; i < listasNumerosX.size(); i++) {
            total = total + (listasNumerosX.get(i) * listasNumerosY.get(i));
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

        this.R2 = parameterRegresionXY * parameterRegresionXY;
        return parameterRegresionXY;
    }

    public double getR2() {

        return this.R2;
    }

    public double getmediaXorY(List<Double> num) {
        DesviacionEstandar calculos = new DesviacionEstandar();
        calculos.DesviacionEstandarAdd(num);
        return calculos.MediaAritmetica();

    }

    public String gerResult() {

        String retorno = "Bo= " + getParameterBCero()+ '\n';
        retorno = retorno + "B1 =" + getParameterBOne() + '\n';;
        retorno = retorno + "r(x,y) =" + getRegresionXY() + '\n';;
        retorno = retorno + "r*r =" + this.R2 + '\n';;
        retorno = retorno + "Yk =" + getParameterYk(386) + '\n';;
        
        return retorno;

    }
}
