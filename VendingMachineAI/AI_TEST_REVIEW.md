
# AI-Generated Test Suite Review

The initial AI JUnit had eight tests for `VendingMachine.java` and `VendingMachineItem.java`. All passed, and VS Code reported 100% statement/line coverage and 100% branch coverage for both source files. The tests did the implementation with relatively few test cases but the coverage alone did not show whether every important boundary or wrong implementation would get detected. I reviewed the generated tests for meaningful assertions, bad inputs, and some boundary behavior rather than treating the coverage percentage as total proof of test quality.

My manual JUnit from Task 1 did 39 test cases and also achieved 100% statement/line and branch coverage for both source files. Compared with the AI one, the manual one exercised more individual scenarios, including validation and exception behavior. The two got to the same coverage percentages despite differing in many ways regarding test count and depth. This showed me that a smaller JUnit can execute every line and branch without necessarily checking as many behaviors.

Because the initial AI suite already reached 100% coverage, my follow-up AI prompt asked for stronger assertions and boundary tests rather than tests for uncovered lines. I added a ninth test for purchasing a free item with a zero balance. It verifies that the purchase succeeds, the item is removed, and the balance stays at zero. The first suggested version used slot code `"A1"`, which caused `VendingMachineException` because that code is invalid in this implementation. I corrected the test to use the valid slot code `"C"` and reran the suite. All nine  passed, with 100% statement/line and branch coverage still reported for both source files. This was a concrete example of why AI-generated test code must be seriously reviewed against the actual implementation.

Overall, AI was useful for producing runnable tests quickly and for suggesting a focused boundary check after coverage was already complete. Its limitations did include making an unhelpful assumption about what slot code is valid and offering a test that did not raise the percentage. I learned that coverage measures which code executes, not necessarily how effectively the assertions detect faults. Test quality apparently depends on correct expected results, boundary values, validation and exception checks, and whether a test would fail if the program has defects.

## AI Interaction Record

Conversation transcript or share link: (https://chatgpt.com/share/6ad1494e-2ca4-83e9-a9a3-49e98971a7b8)

The interaction included an initial request to generate independent JUnit 5 tests using only the production source files, and a follow up prompt reporting 100% line and branch coverage and requesting stronger boundary/fault-detection tests, and importantly a correction of the invalid `"A1"` slot code.

## Coverage Evidence

Final VS Code coverage screenshot: `testresults/final_ai_coverage.png`

| Test suite | Executed tests | Statement/line coverage | Branch coverage |
|---|---:|---:|---:|
| Manual suite (Task 1) | 39 | 100% | 100% |
| Initial AI-generated suite | 8 | 100% | 100% |
| AI suite after follow-up | 9 | 100% | 100% |

*Coverage percentages refer to `VendingMachine.java` and `VendingMachineItem.java`, not the overall project including test files.*
