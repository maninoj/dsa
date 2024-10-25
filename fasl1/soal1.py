def divide(a, b):
    if b == 0:
        return
    if a < b:
        return 0
    return 1+divide(a-b, b)
