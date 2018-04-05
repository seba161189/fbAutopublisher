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
import javax.swing.JOptionPane;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 *
 * @author seba1
 */
public class SikuliFbAutopublisherv3 {

final static String IMAGES_PATH="C:\\Users\\seba1\\Desktop.sikuli\\";
static Screen s=new Screen();
static String ruta="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
static String link;
static String grupo;
static int cantidad;
static String newTab=IMAGES_PATH+ "newTab.PNG";
static String btnCompartir=IMAGES_PATH+"btnCompartir.PNG";
static String compartir=IMAGES_PATH+"compartir.PNG";
static String compartirSeleccionado=IMAGES_PATH+"compartirSeleccionado.PNG";
static String compartir2=IMAGES_PATH+"compartir2.PNG";
static String compartirEnPaginaQueAdministras=IMAGES_PATH+"compartirEnPaginaQueAdministras.PNG";
static String compartirEnTuBiografia=IMAGES_PATH+"compartirEnTuBiografia.PNG";
static String compartirEnUnGrupo=IMAGES_PATH+"compartirEnUnGrupo.PNG";
static String nombreDelGrupo=IMAGES_PATH+"nombreDelGrupo.PNG";
static String publicar=IMAGES_PATH+"publicar.PNG";
static String comentario=IMAGES_PATH+"comentario.PNG";
static String fbIcon=IMAGES_PATH+"fbIcon.PNG";
static String fbMalCargado=IMAGES_PATH+"fbMalCargado.PNG";
static String textoDelComentario="\"Vendo autopublicador de facebook\\n\" +\n" +
"                \"Permite compartir cualquier publicación en todos los grupos de facebook que quieras automáticamente.\\n\" +\n" +
"                \"Ideal para promociones , ventas, eventos, etc.\\n\" +\n" +
"                \"3434679702\"";
    private static int posicionDelGrupo;
    private static int reintentos=0;
private static boolean firstTime=true;
    public static void main(String[] args){
 
    // Abre el jframe hasta que se clickee comenzar y luego comienza sikuli 
     //  pasar las variables urls y grupos al paso 6 solamente
    View ventana=new View();
    while(ventana.isVisible()){
     System.out.println("Ventana abierta");
 }
    
 for(Url url:ventana.urls){
     for(Grupo grupo:ventana.grupos){
         try {
             iniciarPrograma(url.getUrl(),grupo.getDescripcion(),grupo.getCantidad());
         } catch (InterruptedException | FindFailed | IOException ex) {
             JOptionPane.showMessageDialog(null,ex,"Error inesperado",JOptionPane.WARNING_MESSAGE);
             JOptionPane.showMessageDialog(null, "Programa finalizado");
             exit(0);
         }
     }
 } 
 JOptionPane.showMessageDialog(null,"FINALIZADO CORRECTAMENTE");
        
    }

    
      static public void iniciarPrograma(String url,String grupo2,int cantidad2) throws InterruptedException, FindFailed, IOException{
        
        link=url;
        grupo=grupo2;
        cantidad=cantidad2;
        
if(firstTime){
    firstTime=false;
       paso1();//abriendo navegador
}
else{
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
        if(s.exists(new Pattern(newTab).similar(0.8f),30)==null){
            errorHandler("el navegador no se abrio correctamente");
            paso1();
        }else{
           paso3();
        }

    }

    private static void esperarQueEntreFacebook() throws InterruptedException, FindFailed, IOException {
      
        if(s.exists(fbIcon)==null){
            errorHandler("El link al posteo no se abrio correctamente\n ");
            paso3();
        }else{
            paso5();
       }

  }

    private static void scrolearNavegadorHastaEncontrarBotonCompartir() throws InterruptedException, FindFailed, IOException {

        Thread.sleep(2000);
 
      //  s.type(Key.ESC); //cierra ventana negra facebook
        int i=0;
        while(s.exists(btnCompartir)==null){
            s.type(Key.PAGE_DOWN);
            if(i==2){
                errorHandler("No se encontró el boton compartir");
                paso4();
                break;
            }
            i++;
        }
            
        if(s.exists(btnCompartir)!=null){
          paso6();  
        }
        else{
          paso4();
        }
        
    }

    private static void irAUrlYMaximizar() throws InterruptedException, FindFailed, IOException {
        if(s.exists(new Pattern(newTab).similar(0.8f))!=null){
            Thread.sleep(200);
            s.type("d",KeyModifier.ALT);
            Thread.sleep(200);
            s.paste(link);// ir a url
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

        if(s.exists(btnCompartir)!=null){
          s.click(btnCompartir); 
          paso8();  
        }
        else{
         errorHandler("No se visualiza el boton compartir reintentando...");
         if(s.exists(btnCompartir)!=null){
            s.click(btnCompartir); 
          paso8();
         }
         else{
             JOptionPane.showMessageDialog(null,"El boton compartir no se encontró, reinicia el programa");
             finalizarPrograma();
         }
        }
       
 
    }

    private static void paso1() throws IOException, InterruptedException, FindFailed {
        Runtime.getRuntime().exec(ruta);
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
        bucleDesdeApretarCompartirHastaPublicar(grupo,cantidad);
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
        if(s.exists(compartirSeleccionado)!=null){
            s.click(new Pattern(compartirSeleccionado).similar(0.8f));
            paso9();
        }
        else
        if(s.exists(compartir)!=null){
            s.click(new Pattern(compartir).similar(0.8f));   
            paso9();
         }
        else
        if(s.exists(publicar)!=null){
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
    
    if(s.exists(compartirEnTuBiografia)!=null){
            s.click(compartirEnTuBiografia);
            paso10();
        }
    else
    if(s.exists(compartirEnPaginaQueAdministras)!=null){
            s.click(compartirEnPaginaQueAdministras);
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

        s.click(nombreDelGrupo);
        s.type(grupo);
        Thread.sleep(1500);
        int i=1;
        while(i<=posicionDelGrupo){
            s.type(Key.DOWN);
            i++;
        }
        s.type(Key.ENTER);
        
        s.click(comentario);
        s.paste("Disponible! 3434679702");
        s.type(Key.PAGE_DOWN);
        Thread.sleep(500);
        s.click(publicar);
        Thread.sleep(5000);
        s.type(Key.ESC);
 
    }

    private static void clickCompartirEnUnGrupo() throws InterruptedException, FindFailed {
     Thread.sleep(500);
    if(s.exists(compartirEnUnGrupo)!=null){
        s.click(compartirEnUnGrupo);
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

