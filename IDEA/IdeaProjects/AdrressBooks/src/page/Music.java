package page;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;

/**
 * @ProjectName: AdrressBooks
 * @Package: page
 * @ClassName: Music
 * @Author: JN
 * @Description:
 * @Date: 2020/12/16 16:33
 * @Version: 1.0
 */
public class Music {

    public Music(){

    }
    File f;
    URI uri;
    URL url;

    public static void main(String[] args) {
        Music music = new Music();
        music.play();
    }

     void play() {

        try {
            f = new File("src/华晨宇 - 好想爱这个世界啊.wav");

            uri = f.toURI();
            url = uri.toURL();

            AudioClip aau;
            aau = Applet.newAudioClip(url);

            aau.loop();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
      void stop(){
          try {
              f = new File("src/华晨宇 - 好想爱这个世界啊.wav");

              uri = f.toURI();
              url = uri.toURL();

              AudioClip aau;
              aau = Applet.newAudioClip(url);

              aau.stop();

          } catch (Exception e){
              e.printStackTrace();
          }
      }
}


