def factorial(n):
    if n == 0:
        return 1
    if n == 1:
        return 1

    return n * factorial(n-1)


def sum_factorial(n):
    if n == 0:
        return 0

    return factorial(n) + sum_factorial(n-1)