public class soal9 {
    public static void duplicate(Node current){
        if(current == null){
            return;
        }
        Node temp = new Node(current.data);
        temp.next = current.next;
        current.next = temp;
        duplicate(temp.next);
    }
}
