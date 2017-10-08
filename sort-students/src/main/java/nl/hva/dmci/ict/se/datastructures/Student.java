/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yoeri
 */
public class Student implements Comparable<Student> {

    private static int nextStudentNummer;
    private int studentNummer;
    private double cijfer;
    private String klas;
    private Student next;

    public Student(){
        if(nextStudentNummer == 0){
            initStudentNummer();
        }
        this.studentNummer = nextStudentNummer;
        nextStudentNummer++;
        Random rnd = new Random();
        int cijfer = rnd.nextInt(100)+1;
        while(cijfer < 10){
            cijfer = rnd.nextInt(100)+1;
        }
        double doubleCijfer = cijfer/10.0;
        this.cijfer = Math.round(doubleCijfer * 10d) / 10d;
    }

    private void initStudentNummer() {
        nextStudentNummer = 50080001;
    }

    @Override
    public int compareTo(Student o) {
        if(cijfer == o.cijfer){
            return studentNummer - o.studentNummer;
        } else if (cijfer < o.cijfer){
            return -1;
        } else {
            return 1;
        }
    }
    
    public double getCijfer(){
        return cijfer;
    }
    
    public int getStudentNummer(){
        return studentNummer;
    }
    
    public void setKlas(String klas){
        this.klas = klas;
    }

    public String getKlas() {
        return klas;
    }
    
    
}
