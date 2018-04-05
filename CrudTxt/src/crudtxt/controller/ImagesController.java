/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudtxt.controller;
/**
 *
 * @author carce
 */
public final class ImagesController {
    
public String IMAGES_PATH=System.getProperty("user.dir");
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


    public ImagesController(double screenHeight) {
    setImagesPath(screenHeight);

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

    public void setImagesPath(double screenWidth) {
        switch ((int) screenWidth) {
            case 1080:  IMAGES_PATH += "\\src\\images\\imagesForResolution1920x1080px\\";
                     break;
            case 768:   IMAGES_PATH += "\\src\\images\\imagesForResolution1366x768px\\";
                     break;
            default:    IMAGES_PATH  = "\\src\\images\\imagesForResolution1920x1080px\\";
                     break;
        }
    }

    
            
            
}


