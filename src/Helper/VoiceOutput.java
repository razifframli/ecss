/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Helper;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author End User
 */
public class VoiceOutput {
    public static void main(String[] args) {
        VoiceOutput.getSound1("Hai, saya umaq!");
    }
    public static void getSound1(String str) {
        InputStream sound = null;
        try {
            Audio audio = Audio.getInstance();
            String lang = Language.ENGLISH;
//            if (QueueCall.cmbLanguage.getSelectedIndex() == 1) {
//                lang = Language.INDONESIAN;
//            }
            sound = audio.getAudio(str, lang);
            audio.play(sound);
        } catch (JavaLayerException ex) {
            Logger.getLogger(VoiceOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VoiceOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sound.close();
            } catch (IOException ex) {
                System.out.println("Error in VoiceOutput: "+ex.getMessage());
            }
        }
    }
}
