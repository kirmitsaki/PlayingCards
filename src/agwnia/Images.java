/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agwnia;


import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Vrachno
 */
public class Images {
    
     
        private ArrayList<ImageIcon> images;
        
        public Images(ArrayList<Card> cards){
         
        images  = new ArrayList<ImageIcon>();
        for (int i=1;i<=52;i++){
             String dir = System.getProperty("user.dir")+"//src//card pics//"+i+".png";

                    
                    ImageIcon pic = new ImageIcon(dir);
                    cards.get(i-1).setPic(pic);
                    images.add(pic);
                    //System.out.println(dir);
            }
        
            
           
        }
       
         public ArrayList<ImageIcon> getPics(){
    
        return images;
}
 
}
