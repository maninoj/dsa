import java.util.Arrays;

public class BigNumber {
    private int[] digits;  //make an array for storage number
    private boolean negative;  //bool for negative numbers

    //constructor with no given args to initialize an empty array
    public BigNumber(){
        this.digits = new int[]{0};
        this.negative = false;
    }

    //constructor with string args that write the String in array
    public BigNumber(String s){
        //find the negativeness of number
        if (s.startsWith("-")) {
            this.negative = true;
            s = s.substring(1);
        } else {
            this.negative = false;
        }

        //write every element of string in array
        int[] digitArray = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digitArray[i] = Character.getNumericValue(s.charAt(i));
        }

        this.digits = digitArray;
    }

    public BigNumber(int a){
        //find the negativeness of number
        if (a < 0) {
            this.negative = true;
            a = -a;
        } else {
            this.negative = false;
        }

        //make the int to string and write it into an array
        String s = String.valueOf(a);
        int[] digitArray = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            digitArray[i] = Character.getNumericValue(s.charAt(i));
        }

        this.digits = digitArray;
    }

    public BigNumber(int[] a, boolean neg){
        this.digits = trimLeadingZeros(a);
        this.negative = neg;
    }

    public BigNumber(byte[] a, boolean neg){
        this.digits = new int[a.length];
        for (int i = 0; i < digits.length; i++) {
            this.digits[i] = a[i];
        }

        this.digits = trimLeadingZeros(this.digits);
        this.negative = neg;
    }

    public BigNumber add(BigNumber other){
        int maxSize = Math.max(this.digits.length, other.digits.length) + 1;
        int[] a = extendDigits(this.digits, maxSize);
        int[] b = extendDigits(other.digits, maxSize);
        int[] resultDigits = new int[maxSize];
        int carry = 0;

        for (int i = maxSize - 1; i >= 0; i--) {
            int sum = a[i] + b[i] + carry;
            resultDigits[i] = sum % 10;
            carry = sum / 10;
        }

        return new BigNumber(trimLeadingZeros(resultDigits), this.negative);
    }

    public BigNumber subtract(BigNumber other){
        if (this.negative != other.negative) {
            BigNumber negatedOther = new BigNumber(other.digits, !other.negative);
            return this.add(negatedOther);
        }

        int[] a = extendDigits(this.digits, Math.max(this.digits.length, other.digits.length));
        int[] b = extendDigits(other.digits, a.length);
        int[] resultDigits = new int[a.length];
        boolean resultNegative = false;

        int borrow = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            int diff = a[i] - b[i] - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            resultDigits[i] = diff;
        }

        return new BigNumber(trimLeadingZeros(resultDigits), resultNegative);
    }

    public BigNumber multiplyByOne(int n){
        int[] resultDigits = new int[this.digits.length + 1];
        int carry = 0;

        for (int i = this.digits.length - 1; i >= 0; i--) {
            int product = this.digits[i] * n + carry;
            resultDigits[i + 1] = product % 10;
            carry = product / 10;
        }
        resultDigits[0] = carry;

        return new BigNumber(trimLeadingZeros(resultDigits), this.negative);
    }

    public BigNumber shiftRight(int positions) {
        if (positions >= this.digits.length) {
            return new BigNumber();
        }
        int[] newDigits = Arrays.copyOfRange(this.digits, 0, this.digits.length - positions);
        return new BigNumber(trimLeadingZeros(newDigits), this.negative);
    }

    public BigNumber shiftLeft(int positions) {
        int[] newDigits = new int[this.digits.length + positions];
        System.arraycopy(this.digits, 0, newDigits, positions, this.digits.length);

        for (int k=1; k<=positions; k++) {
            for (int i = 1; i < newDigits.length; i++) {
                newDigits[i - 1] = newDigits[i];
            }
        }
        return new BigNumber(trimLeadingZeros(newDigits), this.negative);
    }

    public BigNumber multiply(BigNumber other) {
        int[] result = new int[this.digits.length + other.digits.length];

        for (int i = this.digits.length - 1; i >= 0; i--) {
            int carry = 0;
            for (int j = other.digits.length - 1; j >= 0; j--) {
                int product = this.digits[i] * other.digits[j] + result[i + j + 1] + carry;
                result[i + j + 1] = product % 10;
                carry = product / 10;
            }
            result[i] += carry;
        }

        boolean isResultNegative = this.negative != other.negative;
        return new BigNumber(trimLeadingZeros(result), isResultNegative);
    }

    public BigNumber factorial() {
        BigNumber result = new BigNumber("1");
        BigNumber current = new BigNumber(this.digits, this.negative);

        while (!current.isZero()) {
            result = result.multiply(current);
            current = current.subtract(new BigNumber("1"));
        }

        return result;
    }

    //increase by one
    public void increment(){
        BigNumber one = new BigNumber("1");
        BigNumber result = this.add(one);
        this.digits = result.digits;
        this.negative = result.negative;

    }

    //decrease by one
    public void decrement(){
        BigNumber one = new BigNumber("1");
        BigNumber result = this.subtract(one);
        this.digits = result.digits;
        this.negative = result.negative;
    }

    private int[] trimLeadingZeros(int[] digits) {
        int start = 0;
        while (start < digits.length - 1 && digits[start] == 0) {
            start++;
        }

        return Arrays.copyOfRange(digits, start, digits.length);
    }

    private int[] extendDigits(int[] digits, int newSize) {
        int[] extended = new int[newSize];
        System.arraycopy(digits, 0, extended, newSize - digits.length, digits.length);
        return extended;
    }

    private boolean isZero() {
        for (int digit : digits) {
            if (digit != 0) return false;
        }
        return true;
    }

    //overriding toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(negative ? "-" : "");
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }


}
