## Algorithm: GoodSuffixHeuristic(shift, bpos, P, m)

**Input:**  
- `shift[0…m+1]`: array to store shift values (initially all 0)  
- `bpos[0…m+1]`: array to store border positions  
- `P`: pattern string of length `m`  

**Output:**  
Updated `shift` and `bpos` arrays

---

1. `i ← m`  
2. `j ← m + 1`  
3. `bpos[i] ← j`  

4. **while** `i > 0` **do**  
   1. **while** `j ≤ m` **and** `P[i - 1] ≠ P[j - 1]` **do**  
      - **if** `shift[j] = 0` **then**  
        `shift[j] ← j`  
      - `j ← bpos[j]`  
   2. `i ← i - 1`  
   3. `j ← j - 1`  
   4. `bpos[i] ← j`
