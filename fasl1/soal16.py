def subsets(s):
    if len(s) == 0:
        return [[]]

    first = s[0]
    rest = s[1:]

    without_first = subsets(rest)

    with_first = [[first] + subset for subset in without_first]

    return without_first + with_first


if __name__ == '__main__':
    my_set = [1, 2, 3]
    result = subsets(my_set)

    for subset in result:
        print(subset)
