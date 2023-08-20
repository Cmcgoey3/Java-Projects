# Coin Change Problem Solver

## Author
Connor McGoey

## Introduction

This repository contains a Java implementation of an algorithm to solve the Coin Change Problem. The algorithm aims to find the number of different combinations of coins that can add up to a given target value 'n' using a given set of coin denominations.

## Problem Description

Given a set of coin denominations 'S', the number of coins 'm', and a target value 'n', the problem is to find the total number of ways to make change for 'n' using the coins in the set 'S'. Each coin in the set 'S' can be used an unlimited number of times.

## Algorithm Overview

The algorithm uses a recursive approach to solve the Coin Change Problem. The main idea is to consider two possible paths for each coin: one where the coin is included in the combination and another where it is not included. By summing up the results of these two paths, the algorithm calculates the total number of combinations for the target value 'n'.

## Class Structure

### `coin_change`

This class contains the implementation of the algorithm to solve the Coin Change Problem. It includes:

- `count(S, m, n)`: The main function that takes an array of coin denominations 'S', the number of coins 'm', and the target value 'n' as input. It calculates and returns the total number of combinations to make change for 'n' using coins from the set 'S'.

### `main`

The `main` method demonstrates the usage of the `coin_change` class by providing two test cases. It calculates the total number of combinations needed to make change for specific target values 'n' using given sets of coin denominations 'S'.

## Usage

The provided `main` method showcases how to use the algorithm to find the total number of combinations required to make change for specific target values 'n' using different sets of coin denominations 'S'. This algorithm is useful for solving problems related to optimizing coin usage and calculating the number of ways to make change.

## Example Output

The output of the provided `main` method shows the total number of combinations needed to make change for specific target values 'n' using different sets of coin denominations 'S'.

## Conclusion

This Java implementation demonstrates an algorithm to solve the Coin Change Problem using a recursive approach. The algorithm efficiently calculates the total number of combinations needed to make change for a given target value 'n' using a set of coin denominations 'S'. This algorithm can be applied to various scenarios, such as currency exchange and optimization problems involving coin usage.
