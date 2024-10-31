public class BigNumber {
    private byte[] digits;
    private boolean negative;

    public BigNumber(){
        this.digits = new byte[]{0};
        this.negative = false;
    }

    public BigNumber(String bn){
        if (bn.startsWith("-")) {
            this.negative = true;
            bn = bn.substring(1);
        } else {
            this.negative = false;
        }
        this.digits = toDigitArray(number);
    }

    public BigNumber(int[] a, boolean neg){
        this.digits = trimLeadingZeros(digits);
        this.negative = neg;
    }

    public BigNumber(byte[] a, boolean neg){
        this.digits = new byte[a.length];
        for (int i = 0; i < digits.length; i++) {
            this.digits[i] = a[i];
        }

        this.digits = trimLeadingZeros(this.digits);
        this.negative = neg;
    }

    public BigNumber(int a){
        if (a < 0) {
            this.negative = true;
            a = -a;
        } else {
            this.negative = false;
        }
        this.digits = toDigitArray(String.valueOf(a));
    }

    public BigNumber add(BigNumber bn){}

    public BigNumber subtract(BigNumber bn){}

    public BigNumber multiplyByOne(int n){}

    public BigNumber increment(){}

    public BigNumber decrement(){}


}
