package com.demo.product;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundTrack {
	
	Clip clip;
	boolean stopped = true;
	
	public void horn(){
		try {
			AudioInputStream  audioInputStream = 
	                AudioSystem.getAudioInputStream(getClass().getResource("horn1.wav"));
	        // create clip reference
	        Clip clip = AudioSystem.getClip();
	        // open audioInputStream to the clip
	        clip.open(audioInputStream);
	        clip.start();
	        //clip.loop(Clip.LOOP_CONTINUOUSLY);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void carstart(){
		try {
			AudioInputStream  audioInputStream = 
	                AudioSystem.getAudioInputStream(getClass().getResource("carstart1.wav"));
	        // create clip reference
	        Clip clip = AudioSystem.getClip();
	        // open audioInputStream to the clip
	        clip.open(audioInputStream);
	        clip.start();
	        //clip.loop(Clip.LOOP_CONTINUOUSLY);
	        Thread.sleep(3000);
	        
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void carRun(){
		try {
			AudioInputStream  audioInputStream = 
	                AudioSystem.getAudioInputStream(getClass().getResource("car-idle-sound.wav"));
	        // create clip reference
	        clip = AudioSystem.getClip();
	        // open audioInputStream to the clip
	        clip.open(audioInputStream);
	        clip.start();
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        stopped = false;
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void stop(){
		try {
			clip.stop();
			stopped = true;
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void resume(){
		try {
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			stopped = false;
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	

}
