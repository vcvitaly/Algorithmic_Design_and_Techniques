package io.github.vcvitaly.algo.design._03_greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Change {
    static int minNumOfCoins(int amount) {
        int coinCount = 0;
        while (amount > 0) {
            amount -= biggestCoin(amount).value;
            coinCount++;
        }
        return coinCount;
    }

    private static Coin biggestCoin(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    String.format("%d should be one of: %s", amount, Arrays.toString(Coin.values()))
            );
        }
        if (amount >= 10) {
            return Coin.TEN;
        } else if (amount >= 5) {
            return Coin.FIVE;
        } else {
            return Coin.ONE;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(minNumOfCoins(m));

    }

    enum Coin {
        ONE(1),
        FIVE(5),
        TEN(10);

        int value;

        Coin(int value) {
            this.value = value;
        }
    }
}
