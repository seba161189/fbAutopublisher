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

public class v6 {

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
    private static final int timeout=30;
    static int cantTotalPublicados=0;
    private static boolean newUrl;
    boolean navegadorAbierto=false;
    static String errores;
    
    public static void main(String[] args) throws ParseException{
     
    i=new ImagesController();
    startLogin();
    startView();
    
    for(Url u:ventana.urls){
        System.out.println("Empezando ciclo Nueva url: "+ u);
        newUrl=true;
        linkActual=u.getUrl();
        textoDelComentario=u.getDescripcion();
        for(Grupo g:ventana.grupos){
            System.out.println("Empezando ciclo Nuevo grupo: "+ g);
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

    JOptionPane.showMessageDialog(null,"FINALIZADO CORRECTAMENTE"+ errores);
        
    }

    
      static public void iniciarPrograma() throws InterruptedException, FindFailed, IOException{
        System.out.println("Main: iniciando programa...");
        if(firstTime){
            firstTime=false;
            paso1();//abrir navegador
        }else if(newUrl){
            newUrl=false;
            paso3();//nueva url
          }else{
            paso6();
        }

    }

 

    private static void bucleDesdeApretarCompartirHastaPublicar(String grupo, int cant) throws InterruptedException, FindFailed, IOException {
        
        for(int posGrupo=1;posGrupo<=cant;posGrupo++){
            posicionDelGrupo=posGrupo;
            paso7();
        }
    }
    
    


    private static void esperarQueAbraElNavegador() throws InterruptedException, IOException, FindFailed {
             System.out.println("Navegador : Esperando que abra el navegador...");
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
      }

  }

    private static void scrolearNavegadorHastaEncontrarBotonCompartir() throws InterruptedException, FindFailed, IOException {
      if(isNavegadorAbierto()){
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
Thread.sleep(1000);
        if(s.exists(i.btnCompartir,timeout)!=null){
          s.click(i.btnCompartir); 
            paso8();  
        }
         else{
            paso5();
         }
        
       
 
    }

    private static void paso1() throws IOException, InterruptedException, FindFailed {
        System.out.println("Navegador: abriendo navegador...");
        Runtime.getRuntime().exec(chrome);
        paso2();
    }

    private static void paso2() throws InterruptedException, IOException, FindFailed {
       if(isNavegadorAbierto())
        esperarQueAbraElNavegador();
    }

    private static void paso3() throws InterruptedException, FindFailed, IOException {
        System.out.println("Paso 3: yendo a url y maximizando...");
        if(isNavegadorAbierto())
        irAUrlYMaximizar();
    }

    private static void paso4() throws InterruptedException, FindFailed, IOException {
       System.out.println("paso4: espeando que entre facebook");
       if(isNavegadorAbierto())
        esperarQueEntreFacebook();
    }

    private static void paso5() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso5: scroleando navegador hasta encontrar boton compartir");
        if(isNavegadorAbierto())
        scrolearNavegadorHastaEncontrarBotonCompartir();
    }

    private static void paso6() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso6: bucle boton compartir hasta publicar");
        if(isNavegadorAbierto())
        bucleDesdeApretarCompartirHastaPublicar(grupoActual,cantidadActual);
    }

    private static void paso7() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso7: click boton compartir");
        if(isNavegadorAbierto())
        clickBtnCompartir();
    }

 
    private static void paso8() throws FindFailed, InterruptedException, IOException {
        System.out.println("paso8: click boton compartir...");
        if(isNavegadorAbierto())
     clickCompartir();  
    }

    private static void paso9() throws InterruptedException, FindFailed, IOException {
        System.out.println("paso9: click publicar biografia o pagina");
        if(isNavegadorAbierto())
         clickPublicarEnBiografiaOPagina();
        
    }
    
        private static void paso10() throws InterruptedException, FindFailed, IOException {
                System.out.println("paso10: click compartir en un grupo");
                if(isNavegadorAbierto())
        clickCompartirEnUnGrupo();
     
    }
        
            private static void paso11() throws InterruptedException, FindFailed {
                System.out.println("paso11: nombre grupo, elegirlo y publicar");
                if(isNavegadorAbierto())
   escribirNombreDeGrupoSeleccionarloComentarYPublicar();
    }
            
private static void paso12() throws FindFailed, InterruptedException {
    System.out.println("Recorriendo los grupos");
      recorrerLosGrupos();
    }

    private static void recorrerLosGrupos() throws FindFailed, FindFailed, InterruptedException {
        
        int c=1;
       
        while(c<=posicionDelGrupo){
            s.type(Key.DOWN);
            c++;
        }
        s.type(Key.ENTER);
        Thread.sleep(1000);
        if(s.exists(new Pattern(i.comentario).similar(0.8f))!=null){
            paso13(); 
        } else{
            //error de grupo cantidad en el txt, hay menos grupos
            s.type(Key.ESC);
            s.type(Key.ESC);
            posicionDelGrupo=c;
            
            errores+="Asegurate que hayas puesto la cantidad correcta al grupo "+ grupoActual + " \n";
            
        }
       

    }
    
        private static void paso13() throws FindFailed, InterruptedException {
        escribirComentario();  }

    private static void escribirComentario() throws FindFailed, InterruptedException {
        if(s.exists(new Pattern(i.comentario).similar(0.8f),15)!=null){
            s.click(new Pattern(i.comentario).similar(0.8f));
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
        Thread.sleep(1000);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date hoy = new Date();
Date fin = sdf.parse("2018-04-30");
//si hoy es 
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
        System.out.println("Navegador: chequeando si está abierto...");
//JOptionPane.showMessageDialog(null,i.newTab);
        if(s.exists(new Pattern(i.newTab).similar(0.8f),timeout)!=null){
            return true;
        }else{
        JOptionPane.showMessageDialog(null,"El navegador no se abrió correctamente, reinicia el programa");
        System.exit(0);
        return false;  
        }   
    }

    private static void totalizar() {
               cantTotalPublicados++;
        System.out.println("Se publicó "+ cantTotalPublicados + " veces");}

    private static void checkControlDeSeguridad() {

    }
    public static void errorHandler(String message) throws InterruptedException{
        int popup = JOptionPane.showConfirmDialog(null, message+ "\n Click SI para reanudar el programa. \n NO para finalizar programa", "Error al encontrar componente", JOptionPane.YES_NO_OPTION);
        if (popup == JOptionPane.NO_OPTION) {
            finalizarPrograma();
        }
    };

}

