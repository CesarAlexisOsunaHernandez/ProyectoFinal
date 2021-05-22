import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Audio {
	static Clip audio;
	String url= "MUSICA/";

	public void Musica(String name){
		try {
			audio =AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(getClass().getResource(url+name+".wav")));
			audio.start();
			audio.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e){
			
		}
	}
	
	public void Sonido(String name){
		try {
			audio =AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(getClass().getResource(url+name+".wav")));
			audio.start();
		}catch(Exception e){
			
		}
	}

	public static void detener() {
		audio.stop();
	}
	
	public float getVolume() {
	    FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
}
