## Algorithm: BadCharacterHeuristic(P)

**Input:**  
String `P` (_pattern_) with `m` characters  

**Output:**  
Bad character table `badChar` that maps each character to its last occurrence in `P`

---

1. `i ← 0`  
2. **for** each character in ASCII **do**:  
   - `badCharTable[c] ← -1`  
3. **for** `i < m` **do**:  
   - `badCharTable[P[i]] ← i`  
4. **return** `badCharTable`
