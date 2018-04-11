package Sikuli;

import crudtxt.Views.*;
import crudtxt.controller.*;
import crudtxt.modelo.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.sikuli.script.*;
import utils.TxtExistsOrCreateChecker;

/**
 *
 * @author carce
 */

public class SikuliFbAutopublisherv5 {

    static Screen s=new Screen();
    static ImagesController i;
    static View ventana;
    
    static String chrome="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
    static String textoDelComentario;
    
    static String linkActual;
    static String grupoActual;
    static int cantidadActual;
    
    private static int posicionDelGrupo;
    private static int reintentos=0;
    private static boolean firstTime=true;
    private static int timeout=60;
    static int cantTotalPublicados=0;

    private static void totalizar() {
               cantTotalPublicados++;
        System.out.println("Se publicó "+ cantTotalPublicados + " veces");}

    private static void checkControlDeSeguridad() {
   // if(s.exists(controlDeSeguridad,15)!=null){
        
  //  }
    }



 
    
    boolean navegadorAbierto=false;
    
    public static void main(String[] args) throws ParseException{
     
    ResolutionController resolution=new ResolutionController();   //Setea la ruta de las imagenes a utilizar segun la resolucion del monitor
    i=new ImagesController(resolution.getHeight());
    startLogin();
    startView();
    
    for(Url u:ventana.urls){
        for(Grupo g:ventana.grupos){
            textoDelComentario=u.getDescripcion();
            linkActual=u.getUrl();
            grupoActual=g.getDescripcion();
            cantidadActual=g.getCantidad();
            try {
                iniciarPrograma();
            } catch (InterruptedException | FindFailed | IOException ex) {
                JOptionPane.showMessageDialog(null,ex,"Programa finalizado por error inesperado",JOptionPane.WARNING_MESSAGE);
                System.exit(0);
              }
        }
    } 

    JOptionPane.showMessageDialog(null,"FINALIZADO CORRECTAMENTE");
        
    }

    
      static public void iniciarPrograma() throws InterruptedException, FindFailed, IOException{
        if(firstTime){
            firstTime=false;
            paso1();//abrir navegador
        }else{
            paso6();//bucle de compartir
          }

    }

 

    private static void bucleDesdeApretarCompartirHastaPublicar(String grupo, int cant) throws InterruptedException, FindFailed, IOException {
        
        for(int posGrupo=1;posGrupo<=cant;posGrupo++){
            posicionDelGrupo=posGrupo;
            paso7();
        }
    }
    
    


    private static void esperarQueAbraElNavegador() throws InterruptedException, IOException, FindFailed {
      //esperando que aparezca el navegador abierto
        if(isNavegadorAbierto()){
            paso3();
        }else{
           paso1();
        }

    }

    private static void esperarQueEntreFacebook() throws InterruptedException, FindFailed, IOException {
      if(isNavegadorAbierto()){
        if(s.exists(i.btnCompartir)!=null){
          paso7();
      }else
        if(s.exists(i.fbIcon,timeout)==null){
            paso3();
        }else{
            paso5();
       }
      }else{
          paso1();
      }

  }

    private static void scrolearNavegadorHastaEncontrarBotonCompartir() throws InterruptedException, FindFailed, IOException {

        Thread.sleep(2000);
        int c=0;
        while(s.exists(i.btnCompartir,10)==null){
            s.type(Key.PAGE_DOWN);
            if(c==2){
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
        if(isNavegadorAbierto()){
            Thread.sleep(200);
            s.type("d",KeyModifier.ALT);
            Thread.sleep(200);
            s.paste(linkActual);// ir a url
            s.type(Key.ENTER);
            Thread.sleep(500);
            s.type(Key.UP,KeyModifier.WIN);
            paso4();
        }
        else{
            paso1();
        }
     
    }

    private static void clickBtnCompartir() throws InterruptedException, FindFailed, IOException{

        if(s.exists(i.btnCompartir,timeout)!=null){
          s.click(i.btnCompartir); 
            paso8();  
        }
         else{
            paso5();
         }
        
       
 
    }

    private static void paso1() throws IOException, InterruptedException, FindFailed {
        Runtime.getRuntime().exec(chrome);
        if(isNavegadorAbierto())
        paso2();
    }

    private static void paso2() throws InterruptedException, IOException, FindFailed {
       System.out.println("paso2");
       if(isNavegadorAbierto())
        esperarQueAbraElNavegador();
    }

    private static void paso3() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso3");
        if(isNavegadorAbierto())
        irAUrlYMaximizar();
    }

    private static void paso4() throws InterruptedException, FindFailed, IOException {
       System.out.println("paso4");
       if(isNavegadorAbierto())
        esperarQueEntreFacebook();
    }

    private static void paso5() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso5");
        if(isNavegadorAbierto())
        scrolearNavegadorHastaEncontrarBotonCompartir();
    }

    private static void paso6() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso6");
        if(isNavegadorAbierto())
        bucleDesdeApretarCompartirHastaPublicar(grupoActual,cantidadActual);
    }

    private static void paso7() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso7");
        if(isNavegadorAbierto())
        clickBtnCompartir();
    }

 
    private static void paso8() throws FindFailed, InterruptedException, IOException {
        System.out.println("paso8");
        if(isNavegadorAbierto())
     clickCompartir();  
    }

    private static void paso9() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso9");
        if(isNavegadorAbierto())
         clickPublicarEnBiografiaOPagina();
        
    }
    
        private static void paso10() throws InterruptedException, FindFailed, IOException {
                System.out.println("paso10");
                if(isNavegadorAbierto())
        clickCompartirEnUnGrupo();
     
    }
        
            private static void paso11() throws InterruptedException, FindFailed {
                System.out.println("paso11");
                if(isNavegadorAbierto())
   escribirNombreDeGrupoSeleccionarloComentarYPublicar();
    }
            
private static void paso12() throws FindFailed, InterruptedException {
      recorrerLosGrupos();
    }

    private static void recorrerLosGrupos() throws FindFailed, FindFailed, InterruptedException {
        int c=1;
       
        while(c<=posicionDelGrupo){
            s.type(Key.DOWN);
            c++;
        }
        s.type(Key.ENTER);
        
        paso13();

    }
    
        private static void paso13() throws FindFailed, InterruptedException {
        escribirComentario();  }

    private static void escribirComentario() throws FindFailed, InterruptedException {
        if(s.exists(new Pattern(i.comentario).similar(0.9f),30)!=null){
            s.click(new Pattern(i.comentario).similar(0.9f));
            s.paste(textoDelComentario);
            Thread.sleep(500);
            s.click(i.publicar);
            totalizar();
            checkControlDeSeguridad();
            Thread.sleep(5000);
        }else{
            paso12();
        }
    }
        
        
    private static void clickCompartir() throws  InterruptedException, FindFailed, IOException {
        while(isNavegadorAbierto()){
        if(s.exists(new Pattern(i.compartir).similar(0.8f))!=null){
            s.click(new Pattern(i.compartir).similar(0.8f));   
                paso9();
                break;
        }else
        if(s.exists(new Pattern(i.compartirSeleccionado).similar(0.8f))!=null){
            s.click(new Pattern(i.compartirSeleccionado).similar(0.8f));
                paso9();
                break;
        }else
        if(s.exists(i.publicar)!=null){
                paso9();
                break;
        }else
        {
            paso7();
            break;
        }
        }

         


}


    private static void clickPublicarEnBiografiaOPagina() throws FindFailed, InterruptedException, IOException {
    if(s.exists(i.publicar,timeout)==null){
          paso7();//volver
          }else{

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
                reintentos=0;
        paso7();
    }
   
            }
        
    
    }



    private static void escribirNombreDeGrupoSeleccionarloComentarYPublicar() throws InterruptedException, FindFailed {

        s.click(i.nombreDelGrupo);
        s.type(grupoActual);
        Thread.sleep(1500);
         paso12();
    
     
 
    }

    private static void clickCompartirEnUnGrupo() throws InterruptedException, FindFailed, IOException {
     Thread.sleep(500);
    if(s.exists(i.compartirEnUnGrupo)!=null){
        s.click(i.compartirEnUnGrupo);
        paso11();
    }
    else{
  paso9();
        }
    }
   private static void finalizarPrograma() {
       s.type(Key.F4,KeyModifier.CTRL);
      JOptionPane.showMessageDialog(null,"Programa Finalizado");
        System.exit(0);
    }

    private static void startLogin() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
Date hoy = new Date();
Date fin = sdf.parse("2018-04-30 10:15:25");

if(hoy.after(fin)){
 JOptionPane.showMessageDialog(null,"Gracias por usar el programa de prueba\n Para la version full manda un mensaje a 3434679702");
  System.exit(0);
}
          //PRIMER USO DEL PROGRAMA
          TxtExistsOrCreateChecker f=new TxtExistsOrCreateChecker();
            if(!f.checkIfExist(new File("gruposFb.txt"))){
                Login login=new Login();
                while(login.isVisible()){
                  System.out.println("no estas logueado");  
                }
            }
    }

    private static void startView() {
        ventana=new View();

        while(ventana.isVisible()){
            System.out.println("Ventana abierta");
        }
    }
    private static boolean isNavegadorAbierto(){
        if(s.exists(new Pattern(i.newTab).similar(0.8f),timeout)!=null){
            return true;
        }else{
        JOptionPane.showMessageDialog(null,"Gracias por utilizar el programa");
        System.exit(0);
        return false;  
        }
     
        
    }

    public static void errorHandler(String message) throws InterruptedException{
        int popup = JOptionPane.showConfirmDialog(null, message+ "\n Click SI para reanudar el programa. \n NO para finalizar programa", "Error al encontrar componente", JOptionPane.YES_NO_OPTION);
        if (popup == JOptionPane.NO_OPTION) {
            finalizarPrograma();
        }
    };

}

