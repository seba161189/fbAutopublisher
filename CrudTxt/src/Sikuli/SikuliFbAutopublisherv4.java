/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sikuli;

import crudtxt.Views.View;
import crudtxt.controller.ImagesController;
import crudtxt.controller.ResolutionController;
import crudtxt.modelo.Grupo;
import crudtxt.modelo.Url;
import java.io.IOException;
import static java.lang.System.exit;
import javax.swing.JOptionPane;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 *
 * @author carce
 */

public class SikuliFbAutopublisherv4 {

    static Screen s=new Screen();
    static ImagesController i;
    static String chrome="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
    static String textoDelComentario="Disponible! 3434679702";
    
    static String linkActual;
    static String grupoActual;
    static int cantidadActual;
    
    private static int posicionDelGrupo;
    private static int reintentos=0;
    private static boolean firstTime=true;
    
    public static void main(String[] args){
        //Setea la ruta de las imagenes a utilizar segun la resolucion del monitor
        ResolutionController resolution=new ResolutionController();
        i=new ImagesController(resolution.getHeight());
        
        // Abre jframe View hasta que se clickee comenzar y luego comienza sikuli 
         //  pasar las variables urls y grupos al paso 6 solamente
        View ventana=new View();
        
        while(ventana.isVisible()){
            System.out.println("Ventana abierta");
        }
    
    for(Url u:ventana.urls){
        for(Grupo g:ventana.grupos){
            try {
                iniciarPrograma(u.getUrl(),g.getDescripcion(),g.getCantidad());
            } catch (InterruptedException | FindFailed | IOException ex) {
                JOptionPane.showMessageDialog(null,ex,"Programa finalizado por error inesperado",JOptionPane.WARNING_MESSAGE);
                exit(0);
              }
        }
    } 

    JOptionPane.showMessageDialog(null,"FINALIZADO CORRECTAMENTE");
        
    }

    
      static public void iniciarPrograma(String url,String grupo2,int cantidad2) throws InterruptedException, FindFailed, IOException{
        
        linkActual=url;
        grupoActual=grupo2;
        cantidadActual=cantidad2;
        
        if(firstTime){
            firstTime=false;
            paso1();//abriendo navegador
        }else{
            paso6();//bucle de compartir
          }

    }

 

    private static void bucleDesdeApretarCompartirHastaPublicar(String grupo, int cant) throws InterruptedException, FindFailed {
        
        for(int posGrupo=1;posGrupo<=cant;posGrupo++){
            posicionDelGrupo=posGrupo;
            paso7();
        }
    }
    
    
    public static void errorHandler(String message) throws InterruptedException{
           int popup = JOptionPane.showConfirmDialog(null, message+ "\n Click SI para arreglar el error y reanudar el programa. \n NO para finalizar programa", "Error al encontrar componente", JOptionPane.YES_NO_OPTION);
        if (popup == JOptionPane.NO_OPTION) {
           JOptionPane.showMessageDialog(null, "Programa finalizado");
           System.exit(0);
        }
    };

    private static void esperarQueAbraElNavegador() throws InterruptedException, IOException, FindFailed {
      //esperando que aparezca el navegador abierto

        if(s.exists(new Pattern(i.newTab).similar(0.8f),30)==null){
            errorHandler("el navegador no se abrio correctamente");
            paso1();
        }else{
           paso3();
        }

    }

    private static void esperarQueEntreFacebook() throws InterruptedException, FindFailed, IOException {
      
        if(s.exists(i.fbIcon)==null){
            errorHandler("El link al posteo no se abrio correctamente\n ");
            paso3();
        }else{
            paso5();
       }

  }

    private static void scrolearNavegadorHastaEncontrarBotonCompartir() throws InterruptedException, FindFailed, IOException {

        Thread.sleep(2000);
 
      //  s.type(Key.ESC); //cierra ventana negra facebook
        int c=0;
        while(s.exists(i.btnCompartir)==null){
            s.type(Key.PAGE_DOWN);
            if(c==2){
                errorHandler("No se encontró el boton compartir");
                paso4();
                break;
            }
            c++;
        }
            
        if(s.exists(i.btnCompartir)!=null){
          paso6();  
        }
        else{
          paso4();
        }
        
    }

    private static void irAUrlYMaximizar() throws InterruptedException, FindFailed, IOException {
        if(s.exists(new Pattern(i.newTab).similar(0.8f))!=null){
            Thread.sleep(200);
            s.type("d",KeyModifier.ALT);
            Thread.sleep(200);
            s.paste(linkActual);// ir a url
            s.type(Key.ENTER);
            Thread.sleep(500);
            s.type(Key.WIN,Key.UP);
            paso4();
        }
        else{
            errorHandler("no se encuentra el navegador abierto y maximizado");
            paso1();
        }
     
    }

    private static void clickBtnCompartir() throws InterruptedException, FindFailed{

        if(s.exists(i.btnCompartir)!=null){
          s.click(i.btnCompartir); 
          paso8();  
        }
        else{
         errorHandler("No se visualiza el boton compartir reintentando...");
         if(s.exists(i.btnCompartir)!=null){
            s.click(i.btnCompartir); 
          paso8();
         }
         else{
             JOptionPane.showMessageDialog(null,"El boton compartir no se encontró, reinicia el programa");
             finalizarPrograma();
         }
        }
       
 
    }

    private static void paso1() throws IOException, InterruptedException, FindFailed {
        Runtime.getRuntime().exec(chrome);
        paso2();
    }

    private static void paso2() throws InterruptedException, IOException, FindFailed {
       System.out.println("paso2");
        esperarQueAbraElNavegador();
    }

    private static void paso3() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso3");
        irAUrlYMaximizar();
    }

    private static void paso4() throws InterruptedException, FindFailed, IOException {
       System.out.println("paso4");
        esperarQueEntreFacebook();
    }

    private static void paso5() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso5");
        scrolearNavegadorHastaEncontrarBotonCompartir();
    }

    private static void paso6() throws InterruptedException, FindFailed {
        System.out.println("paso6");
        bucleDesdeApretarCompartirHastaPublicar(grupoActual,cantidadActual);
    }

    private static void paso7() throws InterruptedException, FindFailed {
        System.out.println("paso7");
        clickBtnCompartir();
    }

 
    private static void paso8() throws FindFailed, InterruptedException {
        System.out.println("paso8");
     clickCompartir();  
    }

    private static void paso9() throws InterruptedException, FindFailed {
        System.out.println("paso9");
         clickPublicarEnBiografiaOPagina();
        
    }
    
        private static void paso10() throws InterruptedException, FindFailed {
                System.out.println("paso10");
        clickCompartirEnUnGrupo();
     
    }
        
            private static void paso11() throws InterruptedException, FindFailed {
                System.out.println("paso11");
   escribirNombreDeGrupoSeleccionarloComentarYPublicar();
    }
        
        
    private static void clickCompartir() throws  InterruptedException, FindFailed {
        try{
        //compartir...
        if(s.exists(i.compartirSeleccionado)!=null){
            s.click(new Pattern(i.compartirSeleccionado).similar(0.8f));
            paso9();
        }
        else
        if(s.exists(i.compartir)!=null){
            s.click(new Pattern(i.compartir).similar(0.8f));   
            paso9();
         }
        else
        if(s.exists(i.publicar)!=null){
          paso9();
          }
        else{
            errorHandler("No se visualizó el boton compartir...");
            paso7();
        }
        }catch(FindFailed ex){
            errorHandler("No se visualizó el boton compartir...");
           paso7();
        }
         


}


    private static void clickPublicarEnBiografiaOPagina() throws FindFailed, InterruptedException {
    int reintento=reintentos;
   //en popup    
    
    if(s.exists(i.compartirEnTuBiografia)!=null){
            s.click(i.compartirEnTuBiografia);
            paso10();
        }
    else
    if(s.exists(i.compartirEnPaginaQueAdministras)!=null){
            s.click(i.compartirEnPaginaQueAdministras);
            paso10();
        }
    else
    if(reintento==1){
                errorHandler("No se abrio el popup correctamente al reintentar abrelo manualmente");
                
                reintentos=0;
        paso7();
    }
   

        
    
    }



    private static void escribirNombreDeGrupoSeleccionarloComentarYPublicar() throws InterruptedException, FindFailed {

        s.click(i.nombreDelGrupo);
        s.type(grupoActual);
        Thread.sleep(1500);
        int c=1;
        while(c<=posicionDelGrupo){
            s.type(Key.DOWN);
            c++;
        }
        s.type(Key.ENTER);
        
        s.click(i.comentario);
        s.paste(textoDelComentario);
        s.type(Key.PAGE_DOWN);
        Thread.sleep(500);
        s.click(i.publicar);
        Thread.sleep(5000);
        s.type(Key.ESC);
 
    }

    private static void clickCompartirEnUnGrupo() throws InterruptedException, FindFailed {
     Thread.sleep(500);
    if(s.exists(i.compartirEnUnGrupo)!=null){
        s.click(i.compartirEnUnGrupo);
        paso11();
    }
    else{
        JOptionPane.showConfirmDialog(null,"no se encuentra compartir en un grupo reanudando...");
        paso9();
        }
    }
   private static void finalizarPrograma() {
      JOptionPane.showMessageDialog(null,"Programa Finalizado");
        exit(0);
    }


}

