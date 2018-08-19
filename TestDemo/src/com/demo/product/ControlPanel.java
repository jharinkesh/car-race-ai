package com.demo.product;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

class ControlPanel extends JPanel implements KeyListener{
	
	Main main;
	
	ControlPanel(Main main){
		this.main = main;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(main.soundTrack.stopped)
				main.soundTrack.resume();
			
			main.runTrack.stop = false;
			if(main.runTrack.delay>0)
				main.runTrack.delay -= 1;
			break;
		case KeyEvent.VK_DOWN:
			main.runTrack.delay += 1;
			break;
		case KeyEvent.VK_LEFT:
			if(!main.isLeft ){
				main.carMain.setBounds(680, 550, 60, 120);
				main.isLeft = true;
				System.out.println(main.randomCar2.getY()+","+main.randomCar3.getY()+",0");
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(main.isLeft ){
				main.carMain.setBounds(780, 550, 60, 120);
				main.isLeft = false;
				System.out.println(main.randomCar2.getY()+","+main.randomCar3.getY()+",1");
			}
			break;
		case KeyEvent.VK_SPACE:
			main.runTrack.stop = true;
			main.soundTrack.stop();
			break;
		case KeyEvent.VK_CONTROL:
			main.soundTrack.horn();
			break;
		}
		main.speedLabelValue.setText((100 - (main.runTrack.delay * 10)) + " Kmph");
	}		
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
