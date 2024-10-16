def coin_combination(n, coins, current_comb=[]):
    if n == 0:
        print(current_comb)
        return
    if n < 0:
        return

    for coin in coins:
        coin_combination(n-coin, coins, current_comb+[coin])


if __name__ == '__main__':
    coins = [2, 5, 10]
    n = 17

    coin_combination(n, coins)

