import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            FileWriter writer = new FileWriter("output.txt");

            int testCases = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < testCases; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                char operator = parts[0].charAt(0);
                String polynomial1 = parts[1];
                String polynomial2 = parts[2];

                String result = processOperation(operator, polynomial1, polynomial2);

                writer.write(result + "\n");
            }

            scanner.close();
            writer.close(); // Close the FileWriter

            System.out.println("Output file 'output.txt' created successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error writing to output.txt file.");
            e.printStackTrace();
        }
    }


    public String processOperation(char operator, String polynomial1, String polynomial2) {
        List list1 = parsePolynomial(polynomial1);
        List list2 = parsePolynomial(polynomial2);

        List result = null;

        if (operator == '+') {
            result = add(list1, list2);
        } else if (operator == '-') {
            result = subtract(list1, list2);
        } else if (operator == '*') {
            // Implement multiplication
        } else {
            return "Invalid operator";
        }

        if (result != null) {
            String resultString = result.toString();
            System.out.println(resultString);
            return resultString;
        } else {
            return "Invalid operation";
        }
    }


    private List parsePolynomial(String polynomial) {
        List list = new List();
        String[] terms = polynomial.split("\\+");
        for (String term : terms) {
            String[] parts = term.split("(?=\\b[-+*/]\\b)");
            int coefficient = Integer.parseInt(parts[0].replaceAll("[^0-9-]", ""));
            int exponentX = 0, exponentY = 0, exponentZ = 0;
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].contains("x")) {
                    exponentX = Integer.parseInt(parts[i].replaceAll("\\D", ""));
                } else if (parts[i].contains("y")) {
                    exponentY = Integer.parseInt(parts[i].replaceAll("\\D", ""));
                } else if (parts[i].contains("z")) {
                    exponentZ = Integer.parseInt(parts[i].replaceAll("\\D", ""));
                }
            }
            list.addTerm(coefficient, exponentX, exponentY, exponentZ);
        }
        return list;
    }


    private List add(List list1, List list2) {
        List result = new List();
        Node current1 = list1.head;
        Node current2 = list2.head;

        while (current1 != null || current2 != null) {
            if (current1 == null) {
                result.addTerm(current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ);
                current2 = current2.next;
            } else if (current2 == null) {
                result.addTerm(current1.coefficient, current1.exponentX, current1.exponentY, current1.exponentZ);
                current1 = current1.next;
            } else {
                int comparison = compareTerms(current1, current2);
                if (comparison < 0) {
                    result.addTerm(current1.coefficient, current1.exponentX, current1.exponentY, current1.exponentZ);
                    current1 = current1.next;
                } else if (comparison > 0) {
                    result.addTerm(current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ);
                    current2 = current2.next;
                } else {
                    int newCoefficient = current1.coefficient + current2.coefficient;
                    if (newCoefficient != 0) {
                        result.addTerm(newCoefficient, current1.exponentX, current1.exponentY, current1.exponentZ);
                    }
                    current1 = current1.next;
                    current2 = current2.next;
                }
            }
        }

        return result;
    }

    private List subtract(List list1, List list2) {
        List result = new List();
        Node current1 = list1.head;
        Node current2 = list2.head;

        while (current1 != null || current2 != null) {
            if (current1 == null) {
                result.addTerm(-current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ);
                current2 = current2.next;
            } else if (current2 == null) {
                result.addTerm(current1.coefficient, current1.exponentX, current1.exponentY, current1.exponentZ);
                current1 = current1.next;
            } else {
                int comparison = compareTerms(current1, current2);
                if (comparison < 0) {
                    result.addTerm(current1.coefficient, current1.exponentX, current1.exponentY, current1.exponentZ);
                    current1 = current1.next;
                } else if (comparison > 0) {
                    result.addTerm(-current2.coefficient, current2.exponentX, current2.exponentY, current2.exponentZ);
                    current2 = current2.next;
                } else {
                    int newCoefficient = current1.coefficient - current2.coefficient;
                    if (newCoefficient != 0) {
                        result.addTerm(newCoefficient, current1.exponentX, current1.exponentY, current1.exponentZ);
                    }
                    current1 = current1.next;
                    current2 = current2.next;
                }
            }
        }

        return result;
    }

    private int compareTerms(Node term1, Node term2) {
        if (term1.exponentX != term2.exponentX) {
            return Integer.compare(term1.exponentX, term2.exponentX);
        } else if (term1.exponentY != term2.exponentY) {
            return Integer.compare(term1.exponentY, term2.exponentY);
        } else {
            return Integer.compare(term1.exponentZ, term2.exponentZ);
        }
    }

}


