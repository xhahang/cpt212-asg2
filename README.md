<h1 align="center">🔎 String Matching Algorithms — Boyer–Moore & KMP</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange" alt="Java">
  <img src="https://img.shields.io/badge/Topic-Algorithms-blue" alt="Algorithms">
  <img src="https://img.shields.io/badge/Use-education%20only-lightgrey" alt="Education">
</p>

<p align="center">
  Java implementations and diagrams of <b>Boyer–Moore</b> and <b>Knuth–Morris–Pratt (KMP)</b> string matching algorithms,
  created for a university assignment. Includes visual heuristics and brief, step-by-step notes.
</p>

<h2>📝 Assignment Mapping</h2>
<ul>
  <li><b>Part A</b>: Concept of string matching + BM implementation with commented code.</li>
  <li><b>Part B</b>: BM variations (e.g., Horspool) — discuss key differences &amp; complexities (add to notes if needed).</li>
  <li><b>Part C</b>: KMP overview + comparison with BM (time/space, strengths/weaknesses, scenarios).</li>
</ul>

<h2>📚 Overview</h2>
<ul>
  <li><b>Boyer–Moore (BM)</b>: Skips characters using
    <i>Bad Character</i> and <i>Good Suffix</i> heuristics for practical speedups.</li>
  <li><b>KMP</b>: Uses a <i>failure function</i> (prefix function) to avoid re-checking matched text.</li>
</ul>

<h2>🗂 Repository Structure</h2>
<pre><code>algorithms/
├─ boyer-moore/
│  ├─ imgs/
│  │  ├─ badCharHeuristic.png
│  │  ├─ bmPatternMatching.png
│  │  └─ goodSuffixHeuristic.png
│  ├─ BadCharHeuristic.psu.md
│  ├─ BMPatternMatch.psu.md
│  ├─ BoyerMooreAlgo.java
│  └─ GoodSuffixHeuristic.psu.md
├─ kmp/
│  ├─ imgs/
│  │  ├─ kmpFailureFunction.png
│  │  └─ kmpPatternMatch.png
│  ├─ kmpFailureFunction.psu.md
│  └─ kmpPatternMatch.psu.md
└─ README.md
</code></pre>

<h2>🚀 Run the Code (Java)</h2>
<ol>
  <li>Install <b>JDK 8+</b>.</li>
  <li>Open a terminal in the algorithm folder (e.g., <code>algorithms/boyer-moore</code>).</li>
  <li>Compile:
    <pre><code>javac BoyerMooreAlgo.java</code></pre>
  </li>
  <li>Run:
    <pre><code>java BoyerMooreAlgo</code></pre>
  </li>
</ol>

<hr/>

<h2>🧩 What’s Included</h2>
<ul>
  <li><b>Boyer–Moore</b> with Bad Character &amp; Good Suffix heuristics.</li>
  <li><b>KMP</b> with Failure (LPS) table construction.</li>
  <li>Concise algorithms in <code>.psu.md</code> files.</li>
</ul>

---

<p><i>Note: This repository is for educational purposes and demonstration only.</i></p>
