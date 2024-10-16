def hanoi(n, s, d, a):
    if n == 1:
        print(s + " -> " + d)
    else:
        hanoi(n-1, s, a, d)
        print(s + " -> " + d)
        hanoi(n-1, a, d, s)


if __name__ == '__main__':

    hanoi(4, "s", "d", "a")