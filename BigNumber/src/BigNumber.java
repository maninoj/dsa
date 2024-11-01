import java.util.Arrays;

public class BigNumber {
    private int[] digits;
    private boolean negative;

    public BigNumber(){
        this.digits = new int[]{0};
        this.negative = false;
    }

    public BigNumber(String bn){
        if (bn.startsWith("-")) {
            this.negative = true;
            bn = bn.substring(1);
        } else {
            this.negative = false;
        }

        int[] digitArray = new int[bn.length()];
        for (int i = 0; i < bn.length(); i++) {
            digitArray[i] = Character.getNumericValue(bn.charAt(i));
        }

        this.digits = digitArray;
    }

    public BigNumber(int a){
        if (a < 0) {
            this.negative = true;
            a = -a;
        } else {
            this.negative = false;
        }

        String bn = String.valueOf(a);
        int[] digitArray = new int[bn.length()];
        for (int i = 0; i < bn.length(); i++) {
            digitArray[i] = Character.getNumericValue(bn.charAt(i));
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

    public void increment(){
        BigNumber one = new BigNumber("1");
        BigNumber result = this.add(one);
        this.digits = result.digits;
        this.negative = result.negative;

    }

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


}
