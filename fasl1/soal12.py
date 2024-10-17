def coin_combination_no_repeating(n, coins, current_comb=[], unique_comb=set()):
    if n == 0:
        # add to set for sub repeated arrays
        unique_comb.add(tuple(sorted(current_comb)))
        return

    if n < 0:
        return

    for coin in coins:
        coin_combination_no_repeating(n - coin, coins, current_comb+[coin], unique_comb)


    return unique_comb


if __name__ == '__main__':
    coins = [2, 5, 10]
    n = 17

    unique = coin_combination_no_repeating(n, coins)

    for comb in unique:
        print(comb)
