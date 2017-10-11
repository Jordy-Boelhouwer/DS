/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

        for (Student stu : studenten) {
            System.out.println("Studentnummer: " + stu.getStudentNummer() + " Cijfer: " + stu.getCijfer());
        }

        int[] freq = new int[101];
        for (Student stu : studenten) {
            double position = stu.getCijfer() * 10;
            int intPos = (int) position;
            int occurrence = freq[intPos];
            occurrence++;
            freq[intPos] = occurrence;
        }

        double cijfer = 1.0;
        for (int i = 10; i < 101; i++) {
            //System.out.printf("%.1f, %f", cijfer, freq[i]);
            System.out.println(cijfer + ", " + freq[i]);
            cijfer = (cijfer * 10 + 1) / 10;
        }

        String[] klassenLijst = KlasGenerator.maakKlassen(10000);

        for (int i = 0; i < studenten.size() - 1; i++) {
            Student current = studenten.get(i);
            current.setKlas(klassenLijst[i]);
            studenten.set(i, current);
        }
        
        bucketSort(studenten);
        
        for(int i = 0; i < studenten.size() - 1; i++){
            System.out.println("Studentnummer: " + studenten.get(i).getStudentNummer()
                    + " Cijfer: " + studenten.get(i).getCijfer()
                    + " Klas: " + studenten.get(i).getKlas());
        }

        System.out.println(isStijgend(studenten, studenten.size() - 1));
    }

    public static void sortAscending(ArrayList<Student> studenten) {

        int min;
        Student temp;
        for (int i = 0; i < studenten.size() - 1; i++) {
            // Assume first element is min
            min = i;
            for (int j = i + 1; j < studenten.size(); j++) {
                if (studenten.get(min).compareTo(studenten.get(j)) > 0) {
                    min = j;
                }
            }
            temp = studenten.get(i);
            studenten.set(i, studenten.get(min));
            studenten.set(min, temp);
        }
    }

    public static <T extends Comparable<T>> boolean isStijgend(List<T> rijtje, int n) {
        if (n == 0) {
            return true;
        }
        if (rijtje.get(n).compareTo(rijtje.get(n - 1)) == 1 || rijtje.get(n).compareTo(rijtje.get(n - 1)) == 0) {
            if (isStijgend(rijtje, n - 1) == true) {
                return true;
            }
        }
        return false;
    }

    public static void bucketSort(ArrayList<Student> students) {
        final String[] courses = {"IB", "IG", "IN", "IS", "IT"};
        final int amountOfBuckets = courses.length;
        final int n = students.size();

        if (n == 0) {
            return;
        }

        LinkedList<LinkedList<Student>> buckets = new LinkedList<>(); //create buckets
        for (int i = 0; i < amountOfBuckets; i++) {
            buckets.add(new LinkedList<>()); //fill bucketlist with buckets
        }

        for (int i = 0; i < courses.length; i++) {
            for (Student student : students) {
                if (student.getKlas().startsWith(courses[i])) {
                    buckets.get(i).add(student);
                }
            }
        }

        for (int i = 0; i < amountOfBuckets; i++) {
            LinkedList<Student> studentLinkedList = buckets.get(i);
            Student[] s = studentLinkedList.toArray(new Student[studentLinkedList.size()]);
            insertionSortByClass(s);
            studentLinkedList = new LinkedList<>(Arrays.asList(s));
            buckets.set(i, studentLinkedList);
        }

        LinkedList<Student> result = new LinkedList<>();
        for (LinkedList<Student> bucket : buckets) {
            result.addAll(bucket);
        }

        for (int i = 0; i < result.size(); i++) {
            students.set(i, result.get(i));
        }
    }

    public static void insertionSortByClass(Student[] student) {
        Student temp;
        for (int i = 1; i < student.length; i++) {
            for (int j = i; j > 0; j--) {
                if (student[j].compareTo(student[j - 1]) < 0) {
                    temp = student[j];
                    student[j] = student[j - 1];
                    student[j - 1] = temp;
                }
            }
        }
    }
}
