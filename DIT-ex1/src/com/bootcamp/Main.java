package com.bootcamp;

public class Main {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("usage: com.bootcamp.Main first_name last_name");
            System.exit(1);
        }

        String firstName = args[0];
        String lastName = args[1];

        Person person = new Person(firstName, lastName);

        System.out.println(person);

    }
}
