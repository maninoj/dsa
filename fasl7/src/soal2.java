public class soal2 {
    public static Node createNew(Node h1, Node h2){
        Node head;

        if(h1 == null){
            return h2;
        }

        if(h2 == null){
            return h1;
        }

        if(h1.data > h2.data){
            head = h2;
            h1 = h1.next;
        }
        else{
            head = h2;
            h2 = h2.next;
        }

        Node temp = head;

        while(h1 != null && h2 != null){
            if(h1.data > h2.data){
                temp.next = h2;
                h2 = h2.next;
            }
            else{
                temp.next = h1;
                h1 = h1.next;
            }

            temp = temp.next;
        }

        if(h1 == null){
            temp.next = h2;
        }
        else{
            temp.next = h1;
        }



        return head;

    }
}
