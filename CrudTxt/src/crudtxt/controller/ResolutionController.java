/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudtxt.controller;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author carce
 */
public final class ResolutionController {
   static private double width,height;

    public ResolutionController() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setWidth(screenSize.getWidth());
        setHeight(screenSize.getHeight());
    }
 
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    
    





}
