# Java Calculator

GUI based calculator application written in Java.

<br>
Authors: Bradley Budach, Pronob Kumar
<br>
This project was written in the eclipse editor. To run the project, you must import all the files into a project folder in eclipse. 

When the project is run it will create a window where the input and output can occur.
Press the buttons to write the expression you are trying to solve or press the corresponding keys on the keyboard.
Any valid mathematical operation is accepted.
Press the = button or hit the equals or enter key to submit the expression to be solved.

Valid means it must meet the following criteria:
    - Expression must contain numbers or constants (e or pi). 
        - decimals are accepted
        - negative numbers are accepted, use the - sign to indicate a negative number
    - It cannot contain letters that are not part of a function or constant
    - If there is an operator in the expression, there must be a number on either side which the operation will be performed on. 
    - Accepted operators are as follows:
        - +
        - -
        - /
        - *
        - %
        - ^
    - Parenthesis are accepted to direct order of operations.
    - There must be a closing parenthesis for every opening parenthesis
    - Accepted functions are as follows:
        - sin()
        - cos()
        - tan()
        - asin()
        - acos()
        - atan()
        - sinh()
        - cosh()
        - tanh()
        - log()
        - logn() where n is a base that is greater than 1, n can be a constant, and the expression inside is not 0
        - ln() where the expression inside is not 0
        - abs()
        - sqrt()
        - rootn() where n is a number or constant
    - Operations inside functions, and functions inside functions are accepted

If an expression is not valid, an error message will be displayed in the output instead of the result.
(IMAGE)

The invert button will switch some buttons to alternate functionality. 
Hitting the invert button again will switch them back.
(IMAGE)

The C button clears the output/input.
(IMAGE)
The \u232B button deletes a character at the current caret position.
(IMAGE)
The caret can be moved with the arrow keys on the keyboard.
Clicking the small button at the top will place the answer from the last solved expression into the input.
(IMAGE)

