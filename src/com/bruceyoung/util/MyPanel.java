package com.bruceyoung.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import com.bruceyoung.bean.Hospital;
import com.bruceyoung.bean.Person;
import com.bruceyoung.constant.Constants;
import com.bruceyoung.util.ExportExcel;

public class MyPanel extends JPanel implements Runnable {


	   private int pIndex=0;

	   ExportExcel exportExcel = new ExportExcel();

	    public MyPanel() {
	        this.setBackground(new Color(0x444444));
	    }

	    @Override
	    public void paint(Graphics arg0) {
	        super.paint(arg0);
	        //draw border
	        arg0.setColor(new Color(0x00ff00));
	        arg0.drawRect(Hospital.getInstance().getX(),Hospital.getInstance().getY(),
	                Hospital.getInstance().getWidth(),Hospital.getInstance().getHeight());



	        List<Person> people = PersonPool.getInstance().getPersonList();

	        int S = 0, E = 0, I = 0, R = 0, D = 0;
	        if(people==null){
	            return;
	        }
	        people.get(pIndex).update();
	        for(Person person:people){

	            switch (person.getState()){
	            	case Person.State.SUSCEPTIBLE_ISOLATE:
	                case Person.State.SUSCEPTIBLE:{
	                    arg0.setColor(new Color(0xdddddd));
	                    S++;
	                }break;
					case Person.State.EXPOSED_ISOLATE:
	                case Person.State.EXPOSED: {
	                    arg0.setColor(new Color(0xffee00));
	                    E++;
	                }break;
	            	case Person.State.INFECTED_ISOLATE:
	                case Person.State.INFECTED:
	                case Person.State.INFECTED_HOSPITAL:{
	                    arg0.setColor(new Color(0xff0000));
	                    I++;
	                }break;
					case Person.State.CURED: {
						arg0.setColor(new Color(0x00ff00));
						R++;
					}break;
					case Person.State.DEATH: {
						arg0.setColor(new Color(0x444444));
						D++;
					}
	            }
	            person.update();
	            arg0.fillOval(person.getX(), person.getY(), 3, 3);

	        }
			System.out.println(worldTime + ": S:" + S + ", E: " + E +", I: " + I + ", R: " + R + ", D: " + D);
	        int[] rowData = new int[5];
	        rowData[0] = S; rowData[1] = E; rowData[2] = I; rowData[3] = R; rowData[4] = D;
			try {
				exportExcel.WriteExcel("D://susceptibleIsolate.xlsx", rowData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			worldTime++;
			if(worldTime == 30) {
				if(Constants.WEAR_MASK) {
					for(Person person:people) {
						float random = new Random().nextFloat();
						if(random < Constants.WEAR_MASK_RATE) {
							person.setWearMask(true);
						}
					}
				}
			}
			if(worldTime == 600){
				System.exit(0);
			}
	        pIndex++;
	        if(pIndex>=people.size()){
	            pIndex=0;
	        }
	    }

	    public static int worldTime=0;
	    @Override
	    public void run() {
	        while (true) {

	            this.repaint();

	            try {
	                Thread.sleep(100);

	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	    }


	}
