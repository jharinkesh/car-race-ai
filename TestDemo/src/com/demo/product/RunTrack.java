package com.demo.product;

import java.io.FileNotFoundException;

class RunTrack extends Thread {
	public volatile int delay = 7;
	public boolean stop = false;
	int width = 1000, height = 5000;
	Main m;
	TrainData t;
	
	RunTrack(Main m) {
		this.m = m;
		try {
			t = new TrainData().initialize().optimize();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	boolean attached = false;

	void startRandon(int i) {
		
		if (!attached) {
			m.add(m.randomCar1);
			m.add(m.randomCar2);
			m.add(m.randomCar3);
			m.add(m.randomCar4);
			attached = true;
		}
		m.randomCar1.setBounds(615,(int)(550 - i*0.3), 60, 120);
		m.randomCar2.setBounds(680,(int)(-250 + i*1.15), 60, 120);
		m.randomCar3.setBounds(780,(int)(150 + i*1.25), 60, 120);
		m.randomCar4.setBounds(835,(int)(550 - i*0.2), 60, 120);
	}

	public void run() {

		while (true) {
			m.speedLabelValue.setText((100 - (m.runTrack.delay * 10)) + " Kmph");
			//System.out.println("CHECKING STOP: " + (stop));
			if (!stop) {
				for (int i = 0; i < 4900; i++) {
					try {
						Thread.sleep(delay);
					} catch (Exception e) {
					}
					if (stop)
						break;
					
					startRandon(i);

					m.left2.setBounds(300, -5000 + i, 300, height);
					m.middle2.setBounds(750, -5000 + i, 15, height);
					m.right2.setBounds(900, -5000 + i, 250, height);

					m.left1.setBounds(300, i, 300, height);
					m.middle1.setBounds(750, i, 15, height);
					m.right1.setBounds(900, i, 250, height);
					m.p.requestFocusInWindow();
					
					if(m.aiMode && i%3 == 0){
						int p = t.predict(m.randomCar2.getY(), m.randomCar3.getY());
						if(p == 0){
							if(!m.isLeft ){
								m.carMain.setBounds(680, 550, 60, 120);
								m.isLeft = true;
								System.out.println(m.randomCar2.getY()+","+m.randomCar3.getY()+",0");
							}
						}else{
							if(m.isLeft ){
								m.carMain.setBounds(780, 550, 60, 120);
								m.isLeft = false;
								System.out.println(m.randomCar2.getY()+","+m.randomCar3.getY()+",1");
							}
						}
					}
				}
			}
		}
	}
}