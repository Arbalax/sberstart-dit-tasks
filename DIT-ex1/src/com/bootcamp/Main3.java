package com.bootcamp;

import java.util.*;
import java.util.stream.Collectors;

public class Main3 {

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
        List<Person> sortedPersonList = personList.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .collect(Collectors.toList());
        for (Person person : sortedPersonList)
            System.out.println(person);
    }
}
