"""
public BigNumber factorial() {
        BigNumber result = new BigNumber("1");
        BigNumber current = new BigNumber(this.digits, this.isNegative);

        while (!current.isZero()) {
            result = result.multiply(current);
            current = current.subtract(new BigNumber("1"));
        }

        return result;
    }
"""

# code baray java bod 