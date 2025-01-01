public class soal1 {
    public static void deleteMaximum(Node head){
        if(head.next == null){
            head = null;
            return;
        }

        Node preMax = head;
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
            if(preMax.next.data < temp.next.data)
                preMax = temp;
        }

        if(head.data > preMax.next.data){
            head = head.next;
            return;
        }

        preMax.next = preMax.next.next;
    }

}
