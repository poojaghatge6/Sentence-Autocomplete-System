# Sentence-Autocomplete-System

Implemented beam search to find all probable complete sentences given a prefix of a sentence. Modeled the data i.e large corpus of sentences in a weighted directed graph to optimize the storage.

Technology Stack: Java, Eclipse.

Output:

```
The probability of "Water" appearing after "<s>" is 4.0E-4
The probability of "<s>" appearing after "Water" is 0.0
The probability of "economy" appearing after "planned" is 0.046511627906976744
The probability of "</s>" appearing after "." is 1.0
0.0044965367999641095	<s> He said . </s>
3.7626732189364806E-14	<s> Israel and Jordan signed the peace process . </s>
2.2198405834260207E-5	<s> It is expected . </s>
```
