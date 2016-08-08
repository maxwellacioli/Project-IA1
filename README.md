# ProjectOneAI

# Gramática

# VERIFICAÇÃO DE CONSEQUÊNCIA LÓGICA

* Input: (P v H), ~H |= P 	== 	(P v  H) ^ ~H > P
* Output: true

#Precedência de operadores:
* ~ (negação)
* ^ (conjunção)
* v (disjunção)
* > (implica)
* <> (bi-implica)

# Tokens: 

* atom = [A-Z]
* conj: ‘^’
* disj: ‘v’
* impl: ‘>’
* biim: ‘<>’
* neg: ‘~’
* simb: ‘true’ | ‘false’

# Gramática

<sequencia> ::= <sequencia> | <atomo>
<atom> ::= 'A' | 'B' | 'C' | 'D' | 'E' | 'F' | 'G' | 'H' | 'I' | 'J' | 'K' | 'L' | 'M' | 'N' | 'O' | 'P' | 'Q' | 'R' | 'S' | 'T' | 'U' | 'V' | 'W' | 'X' | 'Y' | 'Z'
<operatorInferencia> ::= <conjuncao> | <disjuncao> | <implicacao> | <biImplicacao>
<conjuncao> ::= ‘^’
<disjuncao> ::= ‘v’
<implicacao> ::= ‘>’
<biImplicacao> ::= ‘<>’


<E> ::= 	<E> conj <E> | 
<E> disj <E> |
<E> impl <E> |
<E> biim <E> |
neg <E> |
( <E> ) |
[A-Z] | verdade | falso
