## Algorithm: KMPMatch(T, P)

**Input:**  
- `T`: text string with `n` characters  
- `P`: pattern string with `m` characters  

**Output:**  
Starting index of the first substring of `T` matching `P`, or an indication that `P` is not a substring of `T`.

---

1. `f ← KMPFailureFunction(P)` // use the previous algorithm  
2. `i ← 0`  
3. `j ← 0`  

4. **while** `i < n` **do**  
   1. **if** `P[j] = T[i]` **then**  
      - **if** `j = m - 1` **then**  
        - return `i - m + 1` // a match  
      - `i ← i + 1`  
      - `j ← j + 1`  
   2. **else if** `j > 0` **then**  
      - `j ← f[j - 1]` // `j` indexes just after a prefix of `P` that must match  
   3. **else**  
      - `i ← i + 1`  

5. return `"There’s no substring of T matching P."`