/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Helper;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author End User
 */
public class VoiceOutput2 {
    
    private static final String VOICENAME_kevin = "kevin";

    public static void speak(String text) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME_kevin);
        voice.allocate();
        voice.speak(text);
    }

    public static void main(String[] args) {
        String text = "umar mukhtar bin hambaran, room number 3";
        VoiceOutput2.speak(text);
    }
}
