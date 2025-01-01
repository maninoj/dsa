public class soal10 {
    public static Node sortLinkedList(Node head) {
        if (head == null || head.next == null)
            return head;

        boolean swapped;
        Node dum = new Node(0);
        dum.next = head;

        do {
            swapped = false;
            Node prev = dum;
            Node current = dum.next;

            while (current != null && current.next != null) {
                if (current.data > current.next.data) {

                    Node nextNode = current.next;
                    current.next = nextNode.next;
                    nextNode.next = current;
                    prev.next = nextNode;

                    swapped = true;
                }

                prev = prev.next;
                current = prev.next;
            }
        } while (swapped);

        return dum.next;
    }
}
