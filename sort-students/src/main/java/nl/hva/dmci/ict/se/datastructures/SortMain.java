/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.dmci.ict.se.datastructures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import nl.hva.dmci.ict.se.datastructures.Stopwatch;

/**
 *
 * @author Yoeri
 */
public class SortMain {

    private static double tijd;
    static Stopwatch stopwatch = new Stopwatch();

    public static void main(String[] args) {
        ArrayList<Student> studenten = new ArrayList<Student>();
        for (int i = 0; i < 80000; i++) {
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
            System.out.println(cijfer + ", " + freq[i]);
            cijfer = (cijfer * 10 + 1) / 10;
        }
        
        Student[] studentenArray = new Student[studenten.size()-1];
        for (int i = 0; i < studenten.size()-1; i++) {
            studentenArray[i] = studenten.get(i);
        }
        
        double beforeInsertion = stopwatch.elapsedTime();
        insertionSortByGrade(studentenArray);
        double afterInsertion = stopwatch.elapsedTime();

        String[] klassenLijst = KlasGenerator.maakKlassen(studenten.size());

        for (int i = 0; i < studenten.size(); i++) {
            Student current = studenten.get(i);
            current.setKlas(klassenLijst[i]);
            studenten.set(i, current);
        }

        // ==== PRINT THE BUCKETLIST ====
        System.out.println(isStijgend(studenten, studenten.size() - 1));

        double beforeBucket = stopwatch.elapsedTime();

        LinkedList<LinkedList<Student>> buckets = bucketSort(studenten);
        
        double afterBucket = stopwatch.elapsedTime();

        for (LinkedList<Student> klas : buckets) {
            Iterator iterator = klas.iterator();
            while (iterator.hasNext()) {
                Student stu = (Student) iterator.next();
                System.out.println(stu.toString());
            }
        }
        
        
        System.out.println("\nBefore InsertionSort on students: " + beforeInsertion + "\nAfter InsertionSort on students: " + afterInsertion + "\nTime needed: " + (afterInsertion - beforeInsertion));
        System.out.println("\nBefore BucketSort on students: " + beforeBucket + "\nAfter BucketSort on students: " + afterBucket + "\nTime needed: " + (afterBucket - beforeBucket));

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

    public static LinkedList<LinkedList<Student>> bucketSort(ArrayList<Student> students) {
        ArrayList<String> uniqueClasses = uniqueClasses(students);
        final int n = students.size();

        if (n == 0) {
            return null;
        }

        LinkedList<LinkedList<Student>> buckets = new LinkedList<>(); //create buckets
        for (int i = 0; i < uniqueClasses.size() - 1; i++) {
            buckets.add(new LinkedList<Student>()); //fill bucketlist with buckets
        }

        for (Student stu : students) {
            String klas = stu.getKlas();
            int indexBucket = uniqueClasses.indexOf(klas);
            buckets.get(indexBucket).add(stu);
        }

        for (int i = 0; i < uniqueClasses.size() - 1; i++) {
            LinkedList<Student> studentLinkedList = buckets.get(i);
            Student[] s = studentLinkedList.toArray(new Student[studentLinkedList.size()]);
            insertionSortByGrade(s);
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
        return buckets;
    }

    public static void insertionSortByGrade(Student[] student) {
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

    public static ArrayList<String> uniqueClasses(ArrayList<Student> studenten) {
        ArrayList<String> uniqueClasses = new ArrayList<String>();
        for (Student stu : studenten) {
            String klas = stu.getKlas();
            if (uniqueClasses.isEmpty()) {
                uniqueClasses.add(klas);
            }

            int unique = 0;
            for (int i = 0; i < uniqueClasses.size(); i++) {
                if (klas == uniqueClasses.get(i)) {
                    unique++;
                }
            }
            if (unique == 0) {
                uniqueClasses.add(klas);
            }
        }
        return uniqueClasses;
    }
}
