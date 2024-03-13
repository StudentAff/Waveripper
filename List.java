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

        // Clear the list
        head = null;

        // Rebuild the list with combined terms
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
