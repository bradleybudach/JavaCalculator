# Java Calculator

GUI based calculator application written in Java.<br>
Authors: Bradley Budach, Pronob Kumar
<br><br>
## Install Instructions:
This project was written in the eclipse editor. To run the project, you must import all the files into a project folder in eclipse. 
<br>
## Usage:
When the project is run it will create a window where the input and output can occur.<br>
Press the buttons to write the expression you are trying to solve or press the corresponding keys on the keyboard.<br>
Any valid mathematical operation is accepted.<br>
Press the = button or hit the equals or enter key to submit the expression to be solved.<br>
<br>
Valid means it must meet the following criteria:
    <ul>
        <li>Expression must contain numbers or constants (e or pi). </li>
        <ul>
            <li>decimals are accepted</li>
            <li>negative numbers are accepted, use the - sign to indicate a negative number</li>
        </ul>
        <li>It cannot contain letters that are not part of a function or constant.</li>
        <li>If there is an operator in the expression, there must be a number on either side which the operation will be performed on. </li>
        <li>Accepted operators are as follows:</li>
        <ul>
            <li>+</li>
            <li>-</li>
            <li>/</li>
            <li>*</li>
            <li>%</li>
            <li>^</li>
        </ul>
        <li>Parenthesis are accepted to direct order of operations.</li>
        <li>There must be a closing parenthesis for every opening parenthesis</li>
        <li>Accepted functions are as follows:</li>
        <ul>
            <li>sin()</li>
            <li>cos()</li>
            <li>tan()</li>
            <li>asin()</li>
            <li>acos()</li>
            <li>atan()</li>
            <li>sinh()</li>
            <li>cosh()</li>
            <li>tanh()</li>
            <li>log()</li>
            <li>logn() where n is a base that is greater than 1, n can be a constant, and the expression inside is not 0</li>
            <li>ln() where the expression inside is not 0</li>
            <li>abs()</li>
            <li>sqrt()</li>
            <li>rootn() where n is a number or constant</li>
        </ul>
        <li>Operations inside functions, and functions inside functions are accepted.</li>
    </ul>
    

If an expression is not valid, an error message will be displayed in the output instead of the result.<br>
The invert button will switch some buttons to alternate functionality. <br>
Hitting the invert button again will switch them back.<br>
The C button clears the output/input.<br>
The &#9003; button deletes a character at the current caret position.<br>
The caret can be moved with the arrow keys on the keyboard.<br>
Clicking the small button at the top will place the answer from the last solved expression into the input.<br>

