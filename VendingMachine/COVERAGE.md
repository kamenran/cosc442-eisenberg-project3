# VendingMachine Coverage Report

## 1. Initial Coverage

| Source File | Statement/Line Coverage | Branch Coverage |
|---|---|---|
| VendingMachine.java | 100% | 100% |
| VendingMachineItem.java | 100% | 100% |

The original JUnit 5 test suite had 100% line and branch coverage for both source files.

## 2. Coverage-Guided Improvements

No additional tests were required -- the original test suite already achieved 100% line and branch coverage for both files.

## 3. Condition-Coverage Analysis

The `makePurchase()` method contains the following Boolean decisions:

```java
if ((item != null) && (this.balance >= item.getPrice()))
```

This decision consists of two conditions:

- `item != null`: True when an item exists in the selected slot and false when the slot is empty.
- `this.balance >= item.getPrice()`: True when the customer has sufficient money and false when the balance is insufficient.

The existing tests exercise successful purchases, purchases with insufficient funds, and purchases from empty slots. When the first condition is false, Java short-circuits the `&&` operator and does not evaluate the second condition.

## 4. Test-Sensitivity Experiment

I temporarily changed the purchase condition in `VendingMachine.java` from:

```java
this.balance >= item.getPrice()
```

to:

```java
this.balance > item.getPrice()
```

This  fault prevents purchases when the balance exactly equals the item's price.

The modified code was tested using JUnit. After recording the results, the fault was removed, the original condition was restored, and the tests were rerun.

The faulty version and restored version were committed separately to GitHub.

## 5. Final Coverage

| Source File | Statement/Line Coverage | Branch Coverage |
|---|---|---|
| VendingMachine.java | 100% | 100% |
| VendingMachineItem.java | 100% | 100% |

The final implementation retains 100% line and branch coverage.

Coverage evidence is saved in `testresults/final_coverage.png`.