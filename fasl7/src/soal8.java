public class soal8 {
    public soal8(TwoLinkNode head){
        TwoLinkNode tail = head;

        while(tail.next != null){
            tail = tail.next;
        }

        tail.next = head;
        head.pre = tail;
    }
}
