/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sikuli;

import crudtxt.Views.View;
import crudtxt.modelo.Grupo;
import crudtxt.modelo.Url;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 *
 * @author seba1
 */
public class SikuliFbAutopublisher {

final static String imagesPath="C:\\Users\\seba1\\Desktop.sikuli\\";
static Screen s=new Screen();
static int timeout=15;
static String ruta="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
static String link="https://www.facebook.com/seba161189/posts/1625939114189066/";
static String newTab=imagesPath+ "newTab.PNG";
static String btnCompartir=imagesPath+"btnCompartir.PNG";
static String compartir=imagesPath+"compartir.PNG";
static String compartirSeleccionado=imagesPath+"compartirSeleccionado.PNG";
static String compartir2=imagesPath+"compartir2.PNG";
static String compartirEnPaginaQueAdministras=imagesPath+"compartirEnPaginaQueAdministras.PNG";
static String compartirEnTuBiografia=imagesPath+"compartirEnTuBiografia.PNG";
static String compartirEnUnGrupo=imagesPath+"compartirEnUnGrupo.PNG";
static String nombreDelGrupo=imagesPath+"nombreDelGrupo.PNG";
static String publicar=imagesPath+"publicar.PNG";
static String comentario=imagesPath+"comentario.PNG";
static String fbIcon=imagesPath+"fbIcon.PNG";
static String textoDelComentario="\"Vendo autopublicador de facebook\\n\" +\n" +
"                \"Permite compartir cualquier publicación en todos los grupos de facebook que quieras automáticamente.\\n\" +\n" +
"                \"Ideal para promociones , ventas, eventos, etc.\\n\" +\n" +
"                \"3434679702\"";

    public static void main(String[] args){
 
    // Abre el jframe hasta que se clickee comenzar y luego comienza sikuli 
       
    View ventana=new View();
    while(ventana.isVisible()){
     System.out.println("Ventana abierta");
 }
 for(Url url:ventana.urls){
     for(Grupo grupo:ventana.grupos){
         try {
             startProgram(url.getUrl(),grupo.getDescripcion(),grupo.getCantidad());
         } catch (InterruptedException | FindFailed | IOException ex) {
             JOptionPane.showMessageDialog(null,ex,"Error inesperado",JOptionPane.WARNING_MESSAGE);
             JOptionPane.showMessageDialog(null, "Programa finalizado");
             exit(0);
         }
     }
 }
        
 JOptionPane.showMessageDialog(null,"FINALIZADO CORRECTAMENTE");
        
    }

    
      static public void startProgram(String url,String grupo,int cantidad) throws InterruptedException, FindFailed, IOException{
        
//abriendo navegador
        Runtime.getRuntime().exec(ruta);
        
//esperando que aparezca el navegador abierto
        if(s.exists(newTab,30)==null){
            errorHandler("el navegador no se abrio correctamente");
        };

//ir a url
        s.paste(url);
        s.type(Key.ENTER);
        s.type(Key.WIN,Key.UP);
        if(s.exists(fbIcon,timeout)==null){
            errorHandler("El link al posteo no se abrio correctamente\n ");
        };
//bajar en caso que no aparezca

        s.type(Key.PAGE_DOWN);
        Thread.sleep(6000);
        
//COMPARTIR
        compartirEn(grupo,cantidad);
        
   
    }

 

    private static void compartirEn(String grupo, int cant) throws InterruptedException, FindFailed {
        for(int total=1;total<=cant;total++){
            
            compartir();
        
        s.type(grupo);
        Thread.sleep(2000);
        int i=1;
        while(i<=total){
            s.type(Key.DOWN);
            i++;
        };
        s.type(Key.ENTER);
        
        s.click(comentario,timeout);
        s.paste("Disponible! 3434679702");
        s.type(Key.PAGE_DOWN);
        Thread.sleep(500);
        s.click(publicar,timeout);
        Thread.sleep(5000);
        s.type(Key.ESC);
        Thread.sleep(1000);
        }
    }
    
       private static void compartir() throws FindFailed, InterruptedException {
   //compartir
   
   //encontrarbtnCompartir 20 ciclos
int i=0;
        while(s.exists(btnCompartir,2)==null){
            s.type(Key.DOWN);
            Thread.sleep(500);
            if(i==20){
                errorHandler("Muevete en la pagina hasta visualizar el boton compartir");
                break;
            }
        };
        s.click(btnCompartir,timeout);

   //compartir...     
        if(s.exists(compartirSeleccionado,timeout)!=null){
        s.click(new Pattern(compartirSeleccionado).similar(0.8f));
        }else{
        s.click(new Pattern(compartir).similar(0.8f));
        }

   //en popup     
   if(s.exists(publicar,timeout)!=null){
       Thread.sleep(1000);
        if(s.exists(compartirEnTuBiografia)!=null){
        s.click(compartirEnTuBiografia);
        }else{
           s.click(compartirEnPaginaQueAdministras);
        }
   }else{
       errorHandler("No se abrio el popup correctamente, abrelo manualmente");
   }

        s.click(compartirEnUnGrupo,timeout);
        s.click(nombreDelGrupo,timeout);
        Thread.sleep(1000);
    }
    
    public static void errorHandler(String message) throws InterruptedException{
           int popup = JOptionPane.showConfirmDialog(null, message+ "\n Click SI para arreglar el error y reanudar el programa. \n NO para finalizar programa", "Error al encontrar componente", JOptionPane.YES_NO_OPTION);
        if (popup == JOptionPane.NO_OPTION) {
           JOptionPane.showMessageDialog(null, "Programa finalizado");
           System.exit(0);
        }
        Thread.sleep(5000);
    };


 
    
}

