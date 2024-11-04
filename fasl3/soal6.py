
def print_pascal_triangle(rows):
    for i in range(rows):
        print(' ' * (rows - i - 1), end='')

        number = 1
        for j in range(i + 1):
            print(number, end=' ')
            number = number * (i - j) // (j + 1)
        print()


if __name__ == "__main__":
    rows = 5
    print_pascal_triangle(rows)
    