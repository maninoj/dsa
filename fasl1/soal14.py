def hanoi(n, s, d, a):
    if n == 1:
        print(s + " -> " + a)
        print(a + " -> " + d)
    else:
        hanoi(n-1, s, d, a)
        print(s + " -> " + a)
        hanoi(n-1, d, s, a)
        print(a + " -> " + d)
        hanoi(n-1, s, d, a)
