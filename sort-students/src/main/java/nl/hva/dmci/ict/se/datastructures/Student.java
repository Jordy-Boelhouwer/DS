/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.Random;

/**
 *
 * @author Yoeri
 */
public class Student {
    private static int nextStudentNummer;
    private int studentNummer;
    private double cijfer;
    
    public Student(){
        if(nextStudentNummer == 0){
            initStudentNummer();
        }
        this.studentNummer = nextStudentNummer;
        nextStudentNummer++;
        Random rnd = new Random();
        double cijfer = rnd.nextDouble()*9+1;
        this.cijfer = Math.round(cijfer * 10d) / 10d;
    }
    
    private void initStudentNummer(){
        nextStudentNummer = 50080001;
    }
    
    public double getCijfer(){
        return cijfer;
    }
    
    public int getStudentNummer(){
        return studentNummer;
    }
    
}
