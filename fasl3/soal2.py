def factorial(num):
    result = 1
    for i in range(2, num + 1):
        result *= i
    return result

def combinations(n, r):
    if r > n:
        return 0
    if r == 0 or r == n:
        return 1

    numerator = 1
    denominator = 1

    for i in range(1, r + 1):
        numerator *= (n - i + 1)
        denominator *= i

    return numerator // denominator


if __name__ == '__main__':
    n = 5
    r = 2
    print(combinations(n, r))
