def generate_sub(arr, index=0, curr_sub=[]):
    print(curr_sub)

    for i in range(index, len(arr)):
        curr_sub.append(arr[i])
        generate_sub(arr, index+1, curr_sub)

        # pop to bring back
        curr_sub.pop()


if __name__ == '__main__':
    generate_sub([1, 2, 3])