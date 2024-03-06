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
