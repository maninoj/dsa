def jadval_arzehs(n, s=""):
    if len(s) == n:
        print(s)
        return

    # make tree
    jadval_arzehs(n, s+"0")
    jadval_arzehs(n, s+"1")