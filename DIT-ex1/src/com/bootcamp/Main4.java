package com.bootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Person> personList = new ArrayList<>();
        while (true) {
            System.out.println("Menu:");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Exit");
            String decision = scanner.next();
            scanner.skip(".*");
            switch (decision) {
                case "1":
                    System.out.println("Please enter first name and last name to add to the list:");
                    String firstName = scanner.next();
                    String lastName = scanner.next();
                    scanner.skip(".*");
                    Person newPerson = new Person(firstName, lastName);
                    personList.add(newPerson);
                    break;
                case "2":
                    for (Person person : personList)
                        System.out.println(person);
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
}
