def find_max(arr, n):
    if n == 0:
        return arr
    if n == 1:
        return arr[0]

    return max(arr[n-1], find_max(arr, n-1))


if __name__ == '__main__':
    print(find_max([1, 3, 2], 3))
