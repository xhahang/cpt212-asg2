## Algorithm: BoyerMooreSearch(T, P)

**Input:**  
- `T`: text string with `n` characters  
- `P`: pattern string with `m` characters  

**Output:**  
Starting index of the first substring of `T` matching `P`, or an indication that `P` is not a substring of `T`.

---

1. `m ← length(P)`  
2. `n ← length(T)`  
3. `badChar ← BadCharacterHeuristic(P)`  
4. Initialize `shift[0..m]` to `0`  
5. Initialize `bpos[0..m]` to `0`  
6. `foundIndex ← empty list`  
7. `GoodSuffixHeuristic(shift, bpos, P, m)`  
8. `s ← 0` // shift of the pattern with respect to text  
9. `found ← false`  

10. **while** `s ≤ n - m` **do**  
    1. `j ← m - 1`  
    2. **while** `j ≥ 0` **and** `P[j] = T[s + j]` **do**  
       - `j ← j - 1`  
    3. **if** `j < 0` **then**  
       - print `"Pattern occurs at position"`, `s`  
       - `s ← s + shift[0]`  
       - `found ← true`  
    4. **else**  
       - `badCharShift ← j - badChar[T[s + j]]`  
       - `goodSuffixShift ← shift[j + 1]`  
       - `s ← s + max(goodSuffixShift, badCharShift)`  

11. **if** `not found` **then**  
    - print `"Pattern not found"`  

12. **if** `foundIndex` is not empty **then**  
    - print `"Pattern found at index: "`, `foundIndex`
