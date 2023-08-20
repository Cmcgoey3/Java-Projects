# Minimum Number of Coins Algorithm

## Author
Connor McGoey

## Introduction

This repository contains a Java implementation of an algorithm to find the minimum number of coins needed to make change for a given amount. The algorithm uses a dynamic programming approach to solve the problem efficiently.

## Algorithm Overview

The algorithm aims to find the minimum number of coins required to make change for a specific amount 'V' using a given set of coin denominations. It follows these steps:

1. Create an array `pathLengths` of length 'V + 1' to store the minimum number of coins required for each amount from 0 to 'V'. Initialize all elements of this array with a value greater than 'V' to mark them as unreachable.

2. Set `pathLengths[0]` to 0, as no coins are needed to make change for an amount of 0.

3. For each amount 'i' from 1 to 'V', iterate through each coin denomination and calculate the minimum number of coins needed to make change for the amount 'i'. Update `pathLengths[i]` with this minimum value.

4. Finally, if the value at `pathLengths[V]` is still 'V + 1', it means that it's not possible to make exact change for the amount 'V' using the given coin denominations. In this case, return -1. Otherwise, return the value at `pathLengths[V]`, which represents the minimum number of coins required.

## Class Structure

### `min_coins`

This class contains the implementation of the algorithm to find the minimum number of coins needed to make change. It includes:

- `minimumCoins(coins, V)`: The main function that takes an array of coin denominations and an amount 'V' as input. It calculates and returns the minimum number of coins required to make change for 'V'.

### `main`

The `main` method demonstrates the usage of the `min_coins` class by providing a test case. It calculates the minimum number of coins needed to make change for a specific amount 'V' using a given set of coin denominations.

## Usage

The provided `main` method demonstrates how to use the algorithm to find the minimum number of coins needed to make change for a given amount 'V' using a set of coin denominations. This algorithm can be useful for solving problems related to currency exchange and optimization of coin usage.

## Example Output

The output of the provided `main` method shows the minimum number of coins required to make change for a specific test case amount 'V' using a set of coin denominations.

## Conclusion

This Java implementation showcases an efficient dynamic programming algorithm to find the minimum number of coins needed to make change for a given amount using a set of coin denominations. The algorithm's implementation can be helpful for solving real-world problems involving currency exchange and minimizing coin usage.
