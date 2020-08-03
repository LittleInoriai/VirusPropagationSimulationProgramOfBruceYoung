package com.bruceyoung.test;

import javax.swing.*;

import com.bruceyoung.bean.Person;
import com.bruceyoung.constant.Constants;
import com.bruceyoung.util.MyPanel;
import com.bruceyoung.util.PersonPool;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;
/**
 * 绘制图形
 *  运行之前首先调整Constants的常量值
 *   运行之前首先调整Constants的常量值
 *    运行之前首先调整Constants的常量值
 *     运行之前首先调整Constants的常量值
 *      运行之前首先调整Constants的常量值
 *       运行之前首先调整Constants的常量值
 */
public class Main {
 public static void main(String[] args) {
        MyPanel p = new MyPanel();
        Thread panelThread = new Thread(p);
        JFrame frame = new JFrame();
        frame.add(p);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelThread.start();

        List<Person> people = PersonPool.getInstance().getPersonList();
        for(int i=0;i<Constants.ORIGINAL_COUNT;i++){
            int index = new Random().nextInt(people.size()-1);
            Person person = people.get(index);

            while (person.isExposed()){
                index = new Random().nextInt(people.size()-1);
                person = people.get(index);
            }
            person.beExposed();

        }


    }
}
