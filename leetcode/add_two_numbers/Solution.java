package add_two_numbers;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);

        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode currRes = result;

        int carry = 0;

        while(curr1 != null || curr2 != null || carry != 0){
            int val1 = (curr1 != null) ? curr1.val : 0;
            int val2 = (curr2 != null) ? curr2.val : 0;

            int sum = val1 + val2 + carry;

            carry = sum / 10;
            int digit = sum % 10;

            currRes.next = new ListNode(digit);
            currRes = currRes.next;

            if (curr1 != null) {
                curr1 = curr1.next;
            }
            if (curr2 != null) {
                curr2 = curr2.next;
            }
        }

        return result.next; 
    }

    public static void main(String[] args) {
    // Skapar första listan: l1 = [9,9,9,9,9,9,9]
    ListNode l1 = new ListNode(9);
    ListNode curr1 = l1;
    for (int i = 0; i < 6; i++) {
        curr1.next = new ListNode(9);
        curr1 = curr1.next;
    }

    // Skapar andra listan: l2 = [9,9,9,9]
    ListNode l2 = new ListNode(9);
    ListNode curr2 = l2;
    for (int i = 0; i < 3; i++) {
        curr2.next = new ListNode(9);
        curr2 = curr2.next;
    }

    // Anropar lösningen (förväntat resultat: [8, 9, 9, 9, 0, 0, 0, 1])
    Solution solution = new Solution();
    ListNode result = solution.addTwoNumbers(l1, l2);

    // Skriver ut resultatet
    System.out.print("Resultat: [");
    ListNode current = result;
    while (current != null) {
        System.out.print(current.val);
        if (current.next != null) {
            System.out.print(", ");
        }
        current = current.next;
    }
    System.out.println("]");
}
}
