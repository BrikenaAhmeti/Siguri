package application;
	
import javafx.application.Application;
import java.util.Map;
import java.util.TreeMap;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
   	 String [] vargu1= {"E","T","A","O","I","N","S","H","R","D","L","U","C","M","W","F","Y","G","P","B","V","K","X","J","Q","Z"}; //shkronjat te renditura sipas frekuences se perdorimit 
   	
       Label nameLbl = new Label("Write your CIPHERTEXT: ");
       TextField textFld = new TextField(); 
       
       
       Button Btn = new Button("Decryption");
       
       Label msg = new Label();                
       msg.setStyle("-fx-text-fill: black;");       //percaktimi i ngjyre se mesazhit dales (plaintext-it)
       
       Button exitBtn = new Button("Exit");
       Btn.setOnAction(e -> { 
           String text = textFld.getText();
           text=text.toUpperCase();               //kthimi ne shkronja te medha
           text=text.replaceAll(" ","");          //eliminimi i hapsirave
           text=text.replaceAll("[-+.^:,;!?_=$#@&*123456789]","");   //eliminimi i numrave dhe simboleve
           int [] vargu2=new int[text.length()];           //formimi i vargut qe ruan pozicionin fillestar te secilit karakter ku numri+65 paraqet karakterin ne kodin Ascii
           int[] vargu=new int[text.length()];                        //vargu qe do ruaj secila shkronje sa here paraqitet
           
           
           for(int i=0;i<text.length();i++) {
          	 vargu2 [i]=text.charAt(i)%65;         //ruajtja e pozicionit te shkronjave ne tekstin hyres
          	 
           }
            
           System.out.println("\n\n");
            for(int i=0;i<text.length();i++)   //numerimi i perseritjes se shkronjave ne form te numrave ne indekset perkatese 
            {
          	 char x=text.charAt(i);
          	 int in=x%65;
          	 vargu[in]++;
            }
            
           
            System.out.println("\n\n");
      
    		
    		
            int[]  output = new int[vargu.length];

    	    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

    	    for (int n = 0; n < vargu.length; n++) {            
    	    	
    	        map.put(vargu[n] * vargu.length + n, n);      //funksion i map/treemap duke perdorur qelsin dhe vleren mbushet map me te dhenat e vargut
    	    }

    	    int n = 0;

    	    for (Integer index: map.values()) {
    	    	
    	        output[n++] = index;         // pasi ne map jane vendosur te dhenat  te sortuara (nga me i vogli) por duke ruajtur indeksat e mehershem 
    	    }                               //keta indeksa ruhen ne nje varg te ri
    	    int temp;                             

    	    for (int i = 0; i < vargu.length/2; i++)
    	      {                                   //menyra klasike e reverse( pasi vargu duhet te jet nga shkronjat qe perseriten me shpesh)
    	         temp = output[i];
    	         output[i] = output[vargu.length-1 - i];
    	         output[vargu.length-1 - i] = temp;
    	      }
    	   String frekuenca= ((char)(0+65)+"-"+vargu[0]+"  ");    //ruajtja e shkronjave dhe numrit te paraqitjes se tyre ne nje string per t'i paraqitur ne dalje
          for(int j=1;j<26;j++) {                             
              frekuenca=frekuenca+((char)(j+65)+"-"+vargu[j]+"  ");  
              }
          
    	    
    	    String [] varguFinal=new String[text.length()];   //ne vargun final ruhet teksti i dekriptuar, ku secili antare eshte nje karakter 
    	    for (int i=0;i<text.length();i++) {
    	    	for(int j=0;j<text.length();j++) {         //shkronjat nga output kontrollohen nese gjenden ne vargun hyres kjo realizohet permes
    	    	if(output[i]==vargu2[j])                   //vargut qe jane ruajtur pozitat ne forme te numrave, nese ato perputhen shkronjat nderrohen 
    	    	varguFinal[j]=vargu1[i];                   //me shkronjat ne pozitat e njejta nga vargu me shkronja sipas frekuences se perdorimit
    	    	}
    	    }
       	 String emri= varguFinal[0];  
        	 for(int a=1;a<varguFinal.length;a++) {       //dhe vargu i ri ruhet ne nje string si shkak i lehtesis se paraqitjes permes javafx  
        		 emri=emri+varguFinal[a];
        	 }
           if (text.length() > 0) {
           	
               msg.setText(frekuenca+"\n"+"PLAINTEXT : "+emri);  //kthimi i rezultatit te fituar
               
           }
                   
       });
        
        exitBtn.setOnAction(e -> Platform.exit());  //percaktimi i punes qe do kyej butoni exit
                
        VBox root = new VBox();
        
        textFld.setMaxWidth(1000);
        root.setSpacing(5);
        root.getChildren().addAll(nameLbl, textFld,Btn,exitBtn,msg);  //thirrja dhe renditja e lelemnteve si butonave,tekstit etj.
        Btn.setPrefSize(90, 30);       //percaktimi i madhesis se buttonave
        exitBtn.setPrefSize(60, 30); 
        Btn.setTranslateX(450);       //percaktimi i pozites se butonave
        Btn.setTranslateY(10);
        exitBtn.setTranslateX(550);
        exitBtn.setTranslateY(-25);
        Scene scene = new Scene(root, 660, 300);
        stage.setScene(scene);
        stage.show();
    }
}