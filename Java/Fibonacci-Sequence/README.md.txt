# Fibonacci Sequence Algorithm

## Author
Connor McGoey

## Note
This algorithm for producing the Fibonacci sequence containing n numbers is non-optimal. The more optimal algorithm would make use of dynamic programming methods.

## Introduction

This repository contains a Java implementation of the Fibonacci sequence algorithm. The Fibonacci sequence is a series of numbers where each number is the sum of the two preceding ones, starting from 0 and 1. The algorithm calculates the Fibonacci value for a given input 'n'.

## Algorithm Overview

The Fibonacci sequence algorithm is defined recursively as follows:

- The base cases are when 'n' is 0 or 1. In these cases, the Fibonacci value is directly returned as 0 and 1, respectively.
- For any other value of 'n', the Fibonacci value is calculated by summing the results of two recursive calls:
  1. `fib(n - 1)`: The Fibonacci value of 'n - 1'.
  2. `fib(n - 2)`: The Fibonacci value of 'n - 2'.

This recursive approach represents the natural definition of the Fibonacci sequence.

## Class Structure

### `fibonacci`

This class contains the implementation of the Fibonacci sequence algorithm. It includes:

- `fib(n)`: The main function that calculates the Fibonacci value for a given input 'n' using recursion and the base cases.

### `main`

The `main` method demonstrates the usage of the `fibonacci` class. It includes test cases to calculate Fibonacci values for different values of 'n'.

## Usage

The provided `main` method demonstrates how to use the Fibonacci sequence algorithm to calculate the Fibonacci value for a given input 'n'. The algorithm is useful for generating the Fibonacci sequence and understanding its recursive nature.

## Example Output

The output of the provided `main` method shows the Fibonacci values for different test cases of 'n'. Each result demonstrates the Fibonacci value for a specific input 'n'.

## Conclusion

This Java implementation provides a demonstration of the Fibonacci sequence algorithm, which is a classic example of a recursive algorithm. The algorithm's implementation can be useful for generating Fibonacci values and understanding recursive definitions in programming.
