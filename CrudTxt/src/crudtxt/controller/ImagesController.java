/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudtxt.controller;

import Sikuli.v7;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author carce
 */
public final class ImagesController {
File currentJavaJarFile = new File(v7.class.getProtectionDomain().getCodeSource().getLocation().getPath());   
String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
String currentRootDirectoryPath = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");
public String IMAGES_PATH=currentRootDirectoryPath;
public String newTab;
public String btnCompartir;
public String compartir;
public String compartirSeleccionado;
public String compartir2;
public String compartirEnPaginaQueAdministras;
public String compartirEnTuBiografia;
public String compartirEnUnGrupo;
public String nombreDelGrupo;
public String publicar;
public String comentario;
public String fbIcon;
public String fbMalCargado;


    public ImagesController() {
    setImagesPath();
    System.out.println("buscando imagenes en "+IMAGES_PATH);
    newTab=IMAGES_PATH+ "newTab.PNG";
    btnCompartir=IMAGES_PATH+"btnCompartir.PNG";
    compartir=IMAGES_PATH+"compartir.PNG";
    compartirSeleccionado=IMAGES_PATH+"compartirSeleccionado.PNG";
    compartir2=IMAGES_PATH+"compartir2.PNG";
    compartirEnPaginaQueAdministras=IMAGES_PATH+"compartirEnPaginaQueAdministras.PNG";
    compartirEnTuBiografia=IMAGES_PATH+"compartirEnTuBiografia.PNG";
    compartirEnUnGrupo=IMAGES_PATH+"compartirEnUnGrupo.PNG";
    nombreDelGrupo=IMAGES_PATH+"nombreDelGrupo.PNG";
    publicar=IMAGES_PATH+"publicar.PNG";
    comentario=IMAGES_PATH+"comentario.PNG";
    fbIcon=IMAGES_PATH+"fbIcon.PNG";
    fbMalCargado=IMAGES_PATH+"fbMalCargado.PNG";
    
    }

    public void setImagesPath() {
       //IMAGES_PATH += "images\\";
        IMAGES_PATH += "images\\";
   //     JOptionPane.showMessageDialog(null,IMAGES_PATH);
    }
}

    
            
            



