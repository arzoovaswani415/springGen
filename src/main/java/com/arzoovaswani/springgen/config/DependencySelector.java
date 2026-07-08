package com.arzoovaswani.springgen.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DependencySelector {

    private final Scanner scanner;

    public DependencySelector(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> selectDependencies() {

        List<String> selected = new ArrayList<>();

        System.out.println("""
                
Select Spring Dependencies

Type dependency name.

Examples

web
data-jpa
mysql
redis

Press ENTER without typing anything to finish.

""");

        while (true) {

            System.out.print("Dependency : ");

            String input = scanner.nextLine()
                    .trim()
                    .toLowerCase();

            if (input.isBlank())
                break;

            List<String> matches = search(input);

            if (matches.isEmpty()) {

                System.out.println("No matching dependency found.");

                continue;

            }

            if (matches.size() == 1) {

                String dependency = matches.getFirst();

                if (!selected.contains(dependency)) {

                    selected.add(dependency);

                    System.out.println("✓ Added " + dependency);

                }

                else {

                    System.out.println("Already selected.");

                }

                continue;

            }

            System.out.println();

            System.out.println("Found:");

            for (int i = 0; i < matches.size(); i++) {

                System.out.printf(
                        "%d. %s%n",
                        i + 1,
                        matches.get(i)
                );

            }

            int choice;

            while (true) {

                System.out.print("Select : ");

                try {

                    choice = Integer.parseInt(scanner.nextLine());

                    if (choice >= 1 && choice <= matches.size())
                        break;

                } catch (Exception ignored) {
                }

                System.out.println("Invalid choice.");

            }

            String dependency = matches.get(choice - 1);

            if (!selected.contains(dependency)) {

                selected.add(dependency);

                System.out.println("✓ Added " + dependency);

            }

        }

        return selected;

    }

    private List<String> search(String keyword) {

        List<String> result = new ArrayList<>();

        keyword = keyword.toLowerCase();

        for (String dependency : DependencyCatalog.DEPENDENCIES) {

            if (dependency.contains(keyword)) {

                result.add(dependency);

            }

        }

        return result;

    }

}