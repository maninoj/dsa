def duplicate(s):
    rep = ""
    temp = ""

    if len(s) == 1 or len(s) == 0:
        print(True)
        return

    for i in range(1, len(s)):
        if s[i] == s[0]:
            rep = s[0, i]
            break

    for i in range(0, len(s), len(rep)):
        temp = s[i, i+len(rep)]
        if not temp == rep:
            print(False)
            return
        elif len(temp) == 2 and temp[1] != temp[0]:
            print(False)
            return

        duplicate(temp[1:])
