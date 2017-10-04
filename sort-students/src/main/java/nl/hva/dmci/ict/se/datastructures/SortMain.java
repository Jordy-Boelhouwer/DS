/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.ArrayList;

/**
 *
 * @author Yoeri
 */
public class SortMain {

    public static void main(String[] args) {
        ArrayList<Student> studenten = new ArrayList<Student>();
        for (int i = 0; i < 10000; i++) {
            studenten.add(new Student());
        }
        
        for(Student stu : studenten){
            System.out.println("Studentnummer: " + stu.getStudentNummer() + " Cijfer: " + stu.getCijfer());
        }
    }
}
