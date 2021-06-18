package com.bootcamp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main7 {

    private interface Exec {
        void exec(List<Person> data) throws Exception;
    }

    private static class MenuItem {
        private final String name;
        private final Exec exec;

        public MenuItem(String name, Exec exec) {
            this.name = name;
            this.exec = exec;
        }

        public String getName() {
            return name;
        }

        public Exec getExec() {
            return exec;
        }
    }

    private static class Menu {
        private final List<MenuItem> items;
        private final Scanner scanner;

        public Menu(Scanner scanner) {
            this.scanner = scanner;
            items = new ArrayList<>();
            items.add(0, new MenuItem("Wrong input", new Exec() {
                @Override
                public void exec(List<Person> data) throws Exception {
                    System.out.println("----------");
                    System.out.println("Wrong input");
                    System.out.println("----------");
                }
            }));
            items.add(1, new MenuItem("Add", new Exec() {
                @Override
                public void exec(List<Person> data) throws Exception {

                    System.out.println(data.get(data.size() - 1) + " was added.");
                }
            }));
            items.add(2, new MenuItem("Show", new Exec() {
                @Override
                public void exec(List<Person> data) throws Exception {
                    System.out.println("----------");
                    for (Person person : data)
                        System.out.println(person);
                    System.out.println("----------");
                }
            }));
            items.add(3, new MenuItem("Exit", new Exec() {
                @Override
                public void exec(List<Person> data) throws Exception {
                    System.exit(0);
                }
            }));
            items.add(4, new MenuItem("Show sorted unique", new Exec() {
                @Override
                public void exec(List<Person> data) throws Exception {
                    Map<String, Person> uniquePersonMap = new HashMap<>();
                    for (Person person : data)
                        uniquePersonMap.put(person.getLastName(), person);
                    List<Person> uniquePersonList = new ArrayList<>(uniquePersonMap.values());
                    List<Person> sortedUniquePersonList = uniquePersonList.stream()
                            .sorted(Comparator.comparing(Person::getLastName))
                            .collect(Collectors.toList());
                    System.out.println("----------");
                    for (Person person : sortedUniquePersonList)
                        System.out.println(person);
                    System.out.println("----------");
                }
            }));
            items.add(5, new MenuItem("Save to file", new Exec() {
                @Override
                public void exec(List<Person> data) throws Exception {
                    try (FileWriter fileWriter = new FileWriter("persons.txt")) {
                        fileWriter.write(String.valueOf(data));
                        fileWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        public void getDecision(List<Person> personList) {
            try {
                if (!scanner.hasNextInt()) {
                    items.get(0).getExec().exec(personList);
                    scanner.skip(".*");
                    return;
                }
                int decision = scanner.nextInt();
                scanner.skip(".*");
                if (decision >= items.size()) {
                    items.get(0).getExec().exec(personList);
                    return;
                }
                if (decision == 1) {
                    System.out.println("Please enter first name and last name to add to the list:");
                    String firstName = scanner.next();
                    String lastName = scanner.next();
                    scanner.skip(".*");
                    Person newPerson = new Person(firstName, lastName);
                    personList.add(newPerson);
                }
                items.get(decision).getExec().exec(personList);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        while (true) {
            for (int j = 1; j < menu.items.size(); j++) {
                System.out.println(j + "." + menu.items.get(j).getName());
            }
            menu.getDecision(personList);
        }


    }
}

