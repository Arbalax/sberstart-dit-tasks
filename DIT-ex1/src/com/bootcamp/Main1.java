package com.bootcamp;

import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {

        System.out.println("Please enter first name and last name:");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.next();
        String lastName = scanner.next();

        Person person = new Person(firstName, lastName);

        System.out.println(person);
    }
}
