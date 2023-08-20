# Permutation Coefficient Algorithm

## Author
Connor McGoey

## Introduction

This repository contains a Java implementation of the Permutation Coefficient algorithm. The algorithm calculates the number of ways to choose a subset of 'k' elements from a larger set of 'n' elements while maintaining the order of elements. It is commonly used in combinatorics to calculate permutations.

## Algorithm Overview

The Permutation Coefficient algorithm calculates the number of ways to arrange 'k' elements out of 'n' elements, with consideration for the order. The algorithm is defined as follows:

- If 'k' is greater than 'n', the result is 0, as it's not possible to have a subset larger than the original set.
- If 'k' is 0, the result is 1, as there's only one way to arrange an empty subset.
- Otherwise, the result is calculated as the sum of two recursive calls:
  1. Multiplying 'k' by the permutation coefficient of 'n - 1' and 'k - 1'.
  2. Adding the permutation coefficient of 'n - 1' and 'k'.

This calculation follows the binomial coefficient formula.

## Class Structure

### `permutation_coefficient`

This class contains the implementation of the Permutation Coefficient algorithm. It includes:

- `per(n, k)`: The main function that calculates the permutation coefficient using recursive calls as described above.

### `main`

The `main` method demonstrates the usage of the `permutation_coefficient` class. It includes test cases to calculate permutation coefficients for given values of 'n' and 'k'.

## Usage

The provided `main` method demonstrates how to use the Permutation Coefficient algorithm to calculate the number of ways to arrange a subset of 'k' elements from a set of 'n' elements. The algorithm is useful in scenarios where order matters, such as arranging objects in a sequence.

## Example Output

The output of the provided `main` method shows the results of applying the Permutation Coefficient algorithm to different values of 'n' and 'k'. Each result demonstrates the number of ways to arrange a subset of 'k' elements from a set of 'n' elements.

## Conclusion

This Java implementation provides a demonstration of the Permutation Coefficient algorithm, which is essential in combinatorics and counting arrangements. The algorithm's implementation can be useful for solving problems related to permutations and ordering of elements.
