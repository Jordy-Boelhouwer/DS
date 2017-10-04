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

    public Student() {
        if (studentNummer == 0) {
            initStudentNummer();
        }
        this.studentNummer = nextStudentNummer;
        nextStudentNummer++;
        Random rnd = new Random();
        cijfer = rnd.nextDouble() * 10 + 1;
    }

    private void initStudentNummer() {
        nextStudentNummer = 50080001;
    }

    public void sortAscending(ArrayList<Student> studenten) {
        
        int min;
        Student temp;
        for (int i = 0; i < studenten.size() - 1; i++) {
            // Assume first element is min
            min = i;
            for (int j = i + 1; j < studenten.size() - 1; j++) {
                if (studenten.get(min).compareTo(studenten.get(j)) > 0){
                    min = j;
                }
            }
            temp = studenten.get(i);
            studenten.set(i, studenten.get(min));
            studenten.set(min, temp);
        }
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
}
