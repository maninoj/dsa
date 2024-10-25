def average(arr, n):
    if n == 0:
        return arr
    if n == 1:
        return arr[0]

    sum = arr[n-1] + ((n-1)*average(arr, n-1))
    
    return sum / n


if __name__ == '__main__':
    print(average([2,5,9,6], 4))
