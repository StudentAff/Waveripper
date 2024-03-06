public class Node {
    int coefficient;
    int exponentX;
    int exponentY;
    int exponentZ;
    Node next;

    public Node(int coefficient, int exponentX, int exponentY, int exponentZ) {
        this.coefficient = coefficient;
        this.exponentX = exponentX;
        this.exponentY = exponentY;
        this.exponentZ = exponentZ;
        this.next = null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (coefficient != 0) {
            if (builder.length() > 0 && coefficient > 0) {
                builder.append("+");
            }
            if (coefficient != 1 && coefficient != -1) {
                builder.append(coefficient);
            } else if (coefficient == -1) {
                builder.append("-");
            }

            if (exponentX > 0) {
                builder.append("x");
                if (exponentX != 1) {
                    builder.append("^").append(exponentX);
                }
            }

            if (exponentY > 0) {
                builder.append("y");
                if (exponentY != 1) {
                    builder.append("^").append(exponentY);
                }
            }

            if (exponentZ > 0) {
                builder.append("z");
                if (exponentZ != 1) {
                    builder.append("^").append(exponentZ);
                }
            }
        } else {
            builder.append("0");
        }

        return builder.toString();
    }

}

