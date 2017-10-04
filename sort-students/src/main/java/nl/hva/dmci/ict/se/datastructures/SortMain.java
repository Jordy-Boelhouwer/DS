/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.ArrayList;
import java.util.List;

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
        
        sortAscending(studenten);
        
        for(Student stu : studenten){
            System.out.println("Studentnummer: " + stu.getStudentNummer() + " Cijfer: " + stu.getCijfer());
        }
        
        System.out.println(isStijgend(studenten, studenten.size() - 1));
    }
    
    public static void sortAscending(ArrayList<Student> studenten) {
        
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
    
    public static <T extends Comparable<T>> boolean isStijgend(List<T> rijtje, int n) {
        if(n == 0){
            return true;
        }
        if(rijtje.get(n).compareTo(rijtje.get(n-1)) == 1){
            if(isStijgend(rijtje, n-1) == true){
                return true;
            }
        }
        return false;
    }
}
