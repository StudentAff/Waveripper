import java.util.HashMap;
import java.util.Map;

class List {
    protected Node head;
    private Node tail;

    public List() {
        head = null;
        tail = null;
    }

    public void addTerm(int coefficient, int exponentX, int exponentY, int exponentZ) {
        // Adds a new term to the list with the given coefficient and exponents
        Node newNode = new Node(coefficient, exponentX, exponentY, exponentZ);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void combineLikeTerms() {
        // Combines like terms in the list by summing coefficients with the same exponents
        Map<String, Integer> termsMap = new HashMap<>();
        Node current = head;

        while (current != null) {
            String key = current.exponentX + "_" + current.exponentY + "_" + current.exponentZ;

            if (termsMap.containsKey(key)) {
                int currentCoefficient = termsMap.get(key);
                termsMap.put(key, currentCoefficient + current.coefficient);
            } else {
                termsMap.put(key, current.coefficient);
            }

            current = current.next;
        }

        // Clears the list
        head = null;

        // Rebuilds the list with combined terms
        for (Map.Entry<String, Integer> entry : termsMap.entrySet()) {
            String[] exponents = entry.getKey().split("_");
            int coefficient = entry.getValue();
            addTerm(coefficient, Integer.parseInt(exponents[0]), Integer.parseInt(exponents[1]), Integer.parseInt(exponents[2]));
        }
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = head;
        while (current != null) {
            builder.append(current.toString());
            current = current.next;
        }
        return builder.toString();
    }

}
