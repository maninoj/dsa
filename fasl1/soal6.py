def zarb(a, b):
    if b == 0 or a == 0:
        return 0
    if b < 0:
        return -1

    else:
        return a + zarb(a, b-1)
