public class soal5 {
    Node head;
    public soal5(){
        head = null;
    }
    public void push(int data){
        Node temp = new Node(data);
        temp.next = head;
        head = temp;
    }

    public int pop(){
        int result = head.data;
        head = head.next;
        return result;
    }

    void print(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

}
