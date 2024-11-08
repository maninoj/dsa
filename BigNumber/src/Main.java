public class Main {
    public static void main(String[] args) {

        BigNumber num1 = new BigNumber("12345678901234567890");
        BigNumber num2 = new BigNumber("98765432109876543210");

        // Test addition
        BigNumber sum = num1.add(num2);
        System.out.println("Sum: " + sum);

        // Test subtraction
        BigNumber difference = num2.subtract(num1);
        System.out.println("Subtract: " + difference);

        // Test multiplication by a single digit
        BigNumber product = num1.multiplyByOne(2);
        System.out.println("Product by 2: " + product);

        // Test shift left
        BigNumber shiftedLeft = num1.shiftLeft(3);
        System.out.println("Shift Left in 3 position: " + shiftedLeft);

        // Test shift right
        BigNumber shiftedRight = num2.shiftRight(5);
        System.out.println("Shift Right in 5 position: " + shiftedRight);

        // Test increment
        num1.increment();
        System.out.println("Increment: " + num1);

        // Test decrement
        num1.decrement();
        System.out.println("Decrement: " + num1);

        // Test divide
        BigNumber divided = num2.divide(num1);
        System.out.println("Divided: " + divided);

        // Test Power
        BigNumber powered = num2.power(new BigNumber("15"));
        System.out.println("Powered: " + powered);

        // Test Factorial
        BigNumber facted = (new BigNumber(5)).factorial();
        System.out.println("Facted: " + facted);

        // Test Multiply
        BigNumber mult = num2.multiply(num1);
        System.out.println("Multiply: " + mult);

        // Test karatsuba Multiply
        BigNumber multkara = num2.karatsubaMultiply(num1);
        System.out.println("Karamult: " + multkara);

        // Test with negative numbers
        BigNumber negNum1 = new BigNumber("-12345678901234567890");
        BigNumber negNum2 = new BigNumber("-98765432109876543210");

        BigNumber negSum = negNum1.add(negNum2);
        System.out.println("Negative Sum: " + negSum);

        BigNumber negDifference = negNum2.subtract(negNum1);
        System.out.println("Negative Subtract: " + negDifference);
    }
}