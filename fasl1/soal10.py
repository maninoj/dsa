def factorial(n):
    if n == 0:
        return 1
    if n == 1:
        return 1

    return n * factorial(n-1)


def reverse_sum_fact(n):
    if n == 0:
        return 0

    else:
        return 1/factorial(n) + reverse_sum_fact(n-1)


if __name__ == '__main__':
    print((reverse_sum_fact(3)))
