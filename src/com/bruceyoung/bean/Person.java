package com.bruceyoung.bean;

import java.util.List;
import java.util.Random;

import com.bruceyoung.constant.Constants;
import com.bruceyoung.util.MyPanel;
import com.bruceyoung.util.PersonPool;

public class Person {
    private City city;
    private int x;
    private int y;
    private MoveTarget moveTarget;
    private boolean wearMask = false;
    int sig=1;


    double targetXU;
    double targetYU;
    double targetSig=50;


    public interface State{
        int SUSCEPTIBLE = 0;//�׸���
        int SUSCEPTIBLE_ISOLATE = 1;//ѡ��ӼҸ�����׸���
        int EXPOSED = 2;//Ǳ���ߣ����д�Ⱦ�ԣ���Ⱦ�ʵ��ڻ�����
        int INFECTED = 3;//�����ߣ����д�Ⱦ��
        int EXPOSED_ISOLATE = 4;//Ǳ���ڸ����ߣ��޷���Ⱦ���ˣ��п��ܳ�Ϊ������
        int INFECTED_ISOLATE = 5;//�����ڸ����ߣ��޷���Ⱦ����
        int INFECTED_HOSPITAL = 6;//��Ժ�����ߣ��޷���Ⱦ����
        int CURED = 7;//�����ߣ�������߿��壬���ᱻ�ٴθ�Ⱦ
        int DEATH = 8;//������
    }

    public Person(City city, int x, int y) {
        this.city = city;
        this.x = x;
        this.y = y;
        targetXU = 100*new Random().nextGaussian()+x;
        targetYU = 100*new Random().nextGaussian()+y;

    }
    public boolean wantMove(){
        double value = sig*new Random().nextGaussian()+Constants.u;
        return value>0;
    }

    private int state=State.SUSCEPTIBLE;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getWearMask() {return wearMask; }

    public void setWearMask(boolean wearMask) {this.wearMask = wearMask; }
    int exposedTime=0;
    int infectedTime=0;
    public boolean isExposed(){
        return state>=State.EXPOSED;
    }
    public void beExposed(){
        state = State.EXPOSED;
        exposedTime=MyPanel.worldTime;
    }

    public double distance(Person person){
        return Math.sqrt(Math.pow(x-person.getX(),2)+Math.pow(y-person.getY(),2));
    }

    private void moveTo(int x,int y){
        this.x+=x;
        this.y+=y;
    }
    private void action(){
        if(state==State.INFECTED_HOSPITAL || state == State.DEATH || state == State.INFECTED_ISOLATE || state == State.EXPOSED_ISOLATE || state == State.SUSCEPTIBLE_ISOLATE){
            return;
        }
        if(!wantMove()){
            return;
        }
        if(moveTarget==null||moveTarget.isArrived()){

            double targetX = targetSig*new Random().nextGaussian()+targetXU;
            double targetY = targetSig*new Random().nextGaussian()+targetYU;
            moveTarget = new MoveTarget((int)targetX,(int)targetY);

        }


        int dX = moveTarget.getX()-x;
        int dY = moveTarget.getY()-y;
        double length=Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2));

        if(length<1){
            moveTarget.setArrived(true);
            return;
        }
        int udX = (int) (dX/length);
        if(udX==0&&dX!=0){
            if(dX>0){
                udX=1;
            }else{
                udX=-1;
            }
        }
        int udY = (int) (dY/length);
        if(udY==0&&udY!=0){
            if(dY>0){
                udY=1;
            }else{
                udY=-1;
            }
        }

        if(x>700){
            moveTarget=null;
            if(udX>0){
                udX=-udX;
            }
        }
        moveTo(udX,udY);

//        if(wantMove()){
//        }


    }
//�趨��ȫ����
    private float SAFE_DIST = Constants.SAFE_DIST;

    public void update(){
        //@TODO��ʱ���Ϊ״̬��

        //�׸���״̬ת������
        if(state == State.SUSCEPTIBLE) {
            if(new Random().nextFloat() < Constants.SUSCEPTIBLE_ISOLATE_RATE && MyPanel.worldTime > 30) {
                state = State.SUSCEPTIBLE_ISOLATE;
            }else{
                List<Person> people = PersonPool.getInstance().getPersonList();
                for(Person person:people) {
                    if(person.getState() == State.SUSCEPTIBLE) {
                        continue;
                    }
                    //��Ǳ���߽Ӵ��м��ʱ���Ⱦ
                    if(person.getState() == State.EXPOSED  && distance(person) < SAFE_DIST) {
                        float broadRate = Constants.EXPOSED_BROAD_RATE;
                        if(wearMask && person.getWearMask()) {
                            broadRate = 0;
                        }else if(wearMask || person.getWearMask()) {
                            broadRate = broadRate * 0.1f;
                        }
                        float random = new Random().nextFloat();
                        if(random < broadRate){
                            this.beExposed();
                        }
                    }
                    //�ͻ����߽Ӵ��м��ʱ���Ⱦ
                    if(person.getState() == State.INFECTED  && distance(person) < SAFE_DIST) {
                        float broadRate = Constants.INFECTED_BROAD_RATE;
                        if(wearMask && person.getWearMask()) {
                            broadRate = 0;
                        }else if(wearMask || person.getWearMask()) {
                            broadRate = broadRate * 0.1f;
                        }
                        float random = new Random().nextFloat();
                        if(random < broadRate) {
                            this.beExposed();
                        }
                    }
                }
            }
        }
        //Ǳ����״̬ת������
        if(state == State.EXPOSED) {
            if(MyPanel.worldTime - exposedTime < Constants.EXPOSED_TIME) {
                float random1 = new Random().nextFloat();
                if (random1 < Constants.EXPOSED_ISOLATE_RATE && MyPanel.worldTime > 30) {
                    state = State.EXPOSED_ISOLATE;
                }
                float random2 = new Random().nextFloat();
                if (random2 < Constants.EXPOSED_TO_INFECTED_RATE) {
                    state = State.INFECTED;
                    infectedTime = MyPanel.worldTime;
                }
            }else {
                state = State.INFECTED;
                infectedTime = MyPanel.worldTime;
            }
        }
        //������״̬ת������
        if(state == State.INFECTED) {
            Bed bed = null;
            //���鱩��һ��ʱ���ҽԺ��ʼӦ������
            if(MyPanel.worldTime > Constants.HOSPITAL_RECEIVE_TIME) {
                bed = Hospital.getInstance().pickBed();
            }
            if(bed == null){
                if(Constants.ISOLATION && MyPanel.worldTime > 30) {
                    //�в��ֻ�����ѡ�����
                    float random = new Random().nextFloat();
                    if(random < Constants.ISOLATE_RATE) {
                        state = State.INFECTED_ISOLATE;
                    }
                }
                //�м�������
                float random = new Random().nextFloat();
                if(random < Constants.DEATH_RATE) {
                    state = State.DEATH;
                }
            }else{
                state=State.INFECTED_HOSPITAL;
                x=bed.getX();
                y=bed.getY();
                bed.setEmpty(false);
            }
        }
        //Ǳ���ڸ�����״̬ת������
        if(state == State.EXPOSED_ISOLATE) {
            //�м��ʳ�Ϊ������
            float random = new Random().nextFloat();
            if(random < Constants.EXPOSED_TO_INFECTED_RATE) {
                state = State.INFECTED_ISOLATE;
                infectedTime = MyPanel.worldTime;
            }
        }
        //����������״̬ת������
        if(state == State.INFECTED_ISOLATE) {
            Bed bed = null;
            if(MyPanel.worldTime > Constants.HOSPITAL_RECEIVE_TIME) {
                bed = Hospital.getInstance().pickBed();
            }
            if(bed == null){
                float random = new Random().nextFloat();
                if(random < Constants.DEATH_RATE) {
                    state = State.DEATH;
                }
            }else{
                state=State.INFECTED_HOSPITAL;
                x=bed.getX();
                y=bed.getY();
                bed.setEmpty(false);
            }
        }
        //������Ժ��״̬ת������
        if(state == State.INFECTED_HOSPITAL) {
            float random = new Random().nextFloat();
            if(random < Constants.CURED_HOSPITAL_RATE) {
                state = State.CURED;
                //�ճ�ҽԺ��λ
                Bed bed = Hospital.getInstance().getBed(x,y);
                if(bed != null) {
                    bed.setEmpty(true);
                    Random random1 = new Random();
                    x = (int) (100 * random1.nextGaussian() + city.getCenterX());
                    y = (int) (100 * random1.nextGaussian() + city.getCenterY());
                    if(x>700){
                        x=700;
                    }
                }
            }
        }
        action();
    }
}