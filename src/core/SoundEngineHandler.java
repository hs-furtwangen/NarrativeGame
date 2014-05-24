package core;

public class SoundEngineHandler {
	
	private static SoundEngine soundEngine;
	
	public static SoundEngine getSoundEngine(){
		if(soundEngine==null)
			soundEngine = new SoundEngine();
		return soundEngine;
	}

}
