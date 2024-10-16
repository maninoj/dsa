def reverse_array(array):
    if len(array) == 0:
        return array
    if len(array) == 1:
        return array[0]

    return array[-1] + reverse_array(array[:-1])
