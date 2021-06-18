package com.bootcamp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main6 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Person> personList = new ArrayList<>();
        while (true) {
            System.out.println("Menu:");
            System.out.println("1.Add");
            System.out.println("2.Show");
            System.out.println("3.Exit");
            System.out.println("4.Show sorted unique");
            System.out.println("5.Save to file");
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
                    System.out.println("----------");
                    for (Person person : personList)
                        System.out.println(person);
                    System.out.println("----------");
                    break;
                case "3":
                    System.exit(0);
                    break;
                case "4":
                    Map<String, Person> uniquePersonMap = new HashMap<>();
                    for (Person person : personList)
                        uniquePersonMap.put(person.getLastName(), person);
                    List<Person> uniquePersonList = new ArrayList<>(uniquePersonMap.values());
                    List<Person> sortedUniquePersonList = uniquePersonList.stream()
                            .sorted(Comparator.comparing(Person::getLastName))
                            .collect(Collectors.toList());
                    System.out.println("----------");
                    for (Person person : sortedUniquePersonList)
                        System.out.println(person);
                    System.out.println("----------");
                    break;
                case "5":
                    try (FileWriter fileWriter = new FileWriter("persons.txt")) {
                        fileWriter.write(String.valueOf(personList));
                        fileWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
}
