def is_transparent(arra, arrb, n):
    for i in range(n):
        for j in range(n):
            if arra[i][j] != arrb[j][i]:
                return False

    return True

