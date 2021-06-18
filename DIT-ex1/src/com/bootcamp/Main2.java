package com.bootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Person> personList = new ArrayList<>();

        while (true) {
            System.out.println("Please enter first name and last name to add to the list or '-1' to print the list:");
            String firstName = scanner.next();
            if (firstName.equals("-1"))
                break;
            String lastName = scanner.next();
            scanner.skip(".*");
            Person person = new Person(firstName, lastName);
            personList.add(person);
        }
        for (Person person : personList)
            System.out.println(person);
    }
}
