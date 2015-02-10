/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.model;

/**
 *
 * @author COCO
 */
public class ParNumber {

    public double numberX;
    public double numberY;

    public ParNumber(double xP, double yP) {

        this.numberX = xP;
        this.numberY = yP;

    }

    public double getX() {
        return this.numberX;
    }
    
    public double getY() {
        return this.numberY;
    }

}
