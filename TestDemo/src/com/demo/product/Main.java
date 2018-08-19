package com.demo.product;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	BgPanel left1 = new BgPanel("left.jpg"),middle1 = new BgPanel("middle.jpg");
	BgPanel right1 = new BgPanel("right.jpg");

	BgPanel left2 = new BgPanel("left.jpg"),middle2 = new BgPanel("middle.jpg");
	BgPanel right2 = new BgPanel("right.jpg");

	BgPanel carMain = new BgPanel("car-main.jpg");
	JButton b1 = new JButton("Start");	
	JButton ai = new JButton("AI MODE");
	ControlPanel p;
	
	Label speedLabel;
	Label speedLabelValue;
	SoundTrack soundTrack = new SoundTrack();
	
	Main() throws InterruptedException {
		setVisible(true);
		this.getContentPane().setBackground(new Color(199, 199, 199));
		setExtendedState(MAXIMIZED_BOTH);
		URL iconurl = getClass().getResource("icon.png");
		setIconImage(Toolkit.getDefaultToolkit().getImage(iconurl));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		int height = 5000;
		//b1.setBounds(50, 100, 100, 50);
		//left.setBounds(50, 200, 100, 50);
		//right.setBounds(50, 300, 100, 50);

		left2.setBounds(300, -5000, 300, height);
		middle2.setBounds(750, -5000, 15, height);
		right2.setBounds(900, -5000, 250, height);

		left1.setBounds(300, 0, 300, height);
		middle1.setBounds(750, 0, 15, height);
		right1.setBounds(900, 0, 250, height);

		carMain.setBounds(680, 550, 60, 120);
		
		speedLabel = new Label("Speed:- ");
		speedLabel.setFont(new Font("Arial",Font.BOLD,18));
		speedLabelValue = new Label("0 Kmph");
		speedLabelValue.setFont(new Font("Arial",Font.BOLD,30));
		speedLabelValue.setForeground(Color.RED);
		speedLabel.setBounds(30, 10, 100, 30);
		speedLabelValue.setBounds(130, 10, 150, 30);
		
		add(speedLabel);
		add(speedLabelValue);
		
		add(carMain);

		add(left1);
		add(middle1);
		add(right1);

		add(left2);
		add(middle2);
		add(right2);
		
		p = new ControlPanel(this);
		p.setBounds(10, 80, 200, 300); p.setBackground(Color.GRAY);
		p.add(b1);
		p.add(ai);
		ai.setBackground(Color.BLACK);
		ai.setForeground(Color.WHITE);

		add(p);
		
		b1.addActionListener(this);
		ai.addActionListener(this);
		p.addKeyListener(p);
		
		runTrack = new RunTrack(this);
		p.requestFocusInWindow();
		this.repaint();
	}
	
	BgPanel randomCar1 = new BgPanel("car-friend.jpg");
	BgPanel randomCar2 = new BgPanel("car-down1.jpg");
	BgPanel randomCar3 = new BgPanel("car-down2.png");
	BgPanel randomCar4 = new BgPanel("truck-up.jpg");
	
	boolean start = true, isLeft = true, aiMode= false;
	RunTrack runTrack;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			startStop();
		}else if(e.getSource() == ai){
			if(aiMode){
				aiMode = false;
				ai.setBackground(Color.RED);
				ai.setForeground(Color.WHITE);
			}else{
				aiMode = true;
				ai.setBackground(Color.BLACK);
				ai.setForeground(Color.WHITE);	
			}
		}
	}

	public void startStop() {
		if (start) {
			runTrack.start();
			start = false;
			soundTrack.carstart();
			soundTrack.carRun();			
		} else {
			runTrack.stop = !runTrack.stop;
			if (runTrack.stop)
				b1.setLabel("Stopped!!, Start");
			else
				b1.setLabel("Started!!, Stop");
		}

		//System.out.println("STOP VALUE: " + runTrack.stop);
	}


	public static void main(String[] args) throws InterruptedException {
		new Main();
	}	

}

