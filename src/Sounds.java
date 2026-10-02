import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;
public class Sounds implements Runnable{
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    public boolean stopPlaying = false;
    public boolean pausePlaying = false;
//    private static Sounds falcon=null;
//    private static Sounds[][] soundEffects = new Sounds[9][3];
    private static String[][] sounds = new String[9][3];
    private static String falcon = null;
    Sounds(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
    }
    public void run()
    {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            //    System.out.println("Format: " + format);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
            source.open(format);
            source.start();
            int read = 0;
            byte[] audioData = new byte[16384];
            while (!stopPlaying && read > -1){
                if (pausePlaying)
                    read = 0;
                else
                    read = ais.read(audioData,0,audioData.length);

                if (read >= 0) {
                    source.write(audioData,0,read);
                }
            }
            donePlaying = true;

            source.drain();
            source.close();
        }
        catch (Exception exc) {
            System.out.println("error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }
    public static void InitSoundEffects(){
        String[] classNames = {"artificer","barbarian","bard","cleric","druid","monk","ranger","rogue","wizard"};
        String[] action = {"attack","ability","super"};
        for (int i = 0; i<9;i++) {
            for (int j = 0; j<3; j++) {
                sounds[i][j]="src/attackAbilitySuper/"+classNames[i]+"/"+action[j]+".wav";
            }
        }
        falcon="src/attackAbilitySuper/druid/falcon.wav";
    }
    public static void playCurrentSound(CharacterClass ptr, int type) {
        if (DominateOrDecease.mute) return;
        if (type==-1) {
            new Sounds(falcon);
            return;
        }
        for (int i = 0; i<9;i++) {
            if (ptr.getName().equals(CharacterClass.getClassNames().get(i)))
                new Sounds(sounds[i][type]);
        }
    }

}





