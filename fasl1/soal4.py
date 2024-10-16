def binary(n):
    if n == 0:
        return ""
    else:
        return binary(n // 2) + str(n % 2)
    