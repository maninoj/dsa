def change_value(a, n):
    arra = []
    arrb = []
    arrc = []
    arrd = []
    arre = []
    arrf = []

    for i in range(n):
        for j in range(n):
            arra[i][j] = a[i][j] + (1 if i == j else 0)
            arrb[i][j] = a[i][j] - (1 if (i + j == n - 1) else 0)
            arrc[i][j] = a[i][j] + (2 if (i < j and i + j < n - 1) else 0)
            arrd[i][j] = a[i][j] - (2 if (i > j and i + j > n - 1) else 0)
            arre[i][j] = a[i][j] + (3 if (j < i and i + j < n - 1) else 0)
            arrf[i][j] = a[i][j] - (3 if (j > i and i + j > n - 1) else 0)
