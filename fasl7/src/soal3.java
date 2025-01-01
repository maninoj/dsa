import java.util.Scanner;

public class soal3 {
    public TwoLinkNode head;

    public soal3(){
        head = null;
    }

    void enqueue(int data){
        TwoLinkNode temp = new TwoLinkNode(data);
        if(head == null){
            head = temp;
            return;
        }

        TwoLinkNode temp2 = head;
        while(temp2.next != null){
            temp2 = temp2.next;
        }

        temp2.next = temp;
        temp.prev = temp2;

    }
    int dequeue(){
        if(head == null){
            return 0;
        }

        TwoLinkNode min = head;
        TwoLinkNode temp = head.next;

        while(temp != null){
            if(min.data > temp.data){
                min = temp;
            }

            temp = temp.next;
        }

        if(min == head && min.next == null){
            head = null;
        }
        else if (min == head){
            head = head.next;
            head.prev = null;
        }
        else if(min.next == null){
            min.prev.next = null;
        }
        else{
            min.next.prev = min.prev;
            min.prev.next = min.next;
        }

        return min.data;
    }

    void print(){
        TwoLinkNode temp = head;

        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        soal3 a = new soal3();
        //bakhsh b
        System.out.println("enter n and then enter numbers one by one");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int i = 0; i < n; i++){
            a.enqueue(input.nextInt());
        }
        System.out.println("unorderd numbers: ");
        a.print();

        System.out.println("numbers in order: ");
        for(int i =0; i < n; i++){
            System.out.print(a.dequeue() + " ");
        }

    }
}



class TwoLinkNode{
    public int data;
    public TwoLinkNode next;
    public TwoLinkNode prev;

    public TwoLinkNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
