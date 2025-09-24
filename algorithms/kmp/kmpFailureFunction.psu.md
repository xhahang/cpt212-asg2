## Algorithm: KMPFailureFunction(P)

**Input:**  
- `P`: pattern string with `m` characters  

**Output:**  
The failure function `f` for `P`, which maps `j` to the length of the longest prefix of `P` that is also a suffix of `P[1…j]`

---

1. `i ← 1`  
2. `j ← 0` // length of previous longest prefix suffix  
3. `f[0] ← 0`  

4. **while** `i < m` **do**  
   1. **if** `P[i] = P[j]` **then**  
      - `j ← j + 1` // we have matched `j + 1` characters  
      - `f[i] ← j`  
      - `i ← i + 1`  
   2. **else if** `j ≠ 0` **then**  
      - `j ← f[j - 1]` // `j` indexes just after a prefix of `P` that must match  
   3. **else**  
      - `f[i] ← 0` // we have no match here  
      - `i ← i + 1`