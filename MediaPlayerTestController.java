/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author SNEHA
 */
public class MediaPlayerTestController implements Initializable {
    
   @FXML
   private FontAwesomeIconView play;
   @FXML
   private FontAwesomeIconView pause;
   @FXML
   private Slider volumeSlider;
   @FXML
   private File fi;
   @FXML
   private MediaView media;
   @FXML
   private MediaPlayer mp;
   @FXML
   private Media m;
   
   
   @FXML
   public void playVideo(MouseEvent e){
       play.setVisible(false);
       pause.setVisible(true);
       mp.play();
   }
   @FXML
   public void pauseVideo(MouseEvent e){
       play.setVisible(true);
       pause.setVisible(false);
       mp.pause();
   }
   
   public void lastVideo(MouseEvent e){
       mp.seek(mp.getTotalDuration());
       mp.stop();
   }
   
   public void fastVideo(MouseEvent e){
       mp.setRate(1.2);
   }
      public void slowVideo(MouseEvent e){
       mp.setRate(0.9);
   }
   
         public void reloadVideo(MouseEvent e){
       mp.seek(mp.getStartTime());
       mp.play();
   }
   public void openVideo(ActionEvent e){
       FileChooser filechooser = new FileChooser();
       filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Videos File","*.mp4","*.mkv"));
       fi = filechooser.showOpenDialog(null);
       String path = fi.getAbsolutePath();
       path = path.replace("\\", "/");
       
       if(fi!=null){
           m = new Media(new File(path).toURI().toString());
           mp = new MediaPlayer(m);
           media.setMediaPlayer(mp);
           mp.play();
           play.setVisible(false);
           pause.setVisible(true);
           
           volumeSlider.setValue(mp.getVolume()*100);
           volumeSlider.valueProperty().addListener(new InvalidationListener() {
               @Override
               public void invalidated(Observable observable) {
                   mp.setVolume(volumeSlider.getValue()/100);
               }
           });
           
           
           DoubleProperty width = media.fitWidthProperty();
           DoubleProperty height = media.fitHeightProperty();
           width.bind(Bindings.selectDouble(media.sceneProperty(),"width"));
           height.bind(Bindings.selectDouble(media.sceneProperty(),"height"));
           
           
           
           
           
           
           
       }
   }
   
   
   
   
   
   
   
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pause.setVisible(false);
    }    
    
}
