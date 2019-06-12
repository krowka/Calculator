# Calculator
Simple android application to evaluate diffrent kind of math expression.

Application uses [Shunting-yard algorithm](https://en.wikipedia.org/wiki/Shunting-yard_algorithm "Shunting-yard algorithm at wikipedia") 
to parse mathematical expressions specified in 
infix notation to [Reverse Polish Notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation "RPN at wikipedia") 
(also knows as postfix notation)

## Example of supported expressions
```
1+2-3*4/5

1+2-3*(4/5)

-(1+2-3*4)

(-(1/2)*0.5-10)/2
```
