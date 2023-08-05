//Kim San Thai - a1781492



Design Brief:

Develop and operate a Java RMI system that can implement 5 methods. The system will contain a stack that the methods can act upon. These methods include:
-void pushValue(int val) - pushes "Val" onto the top of the stack
-void pushOperation(String operator) - does 1 of 4 things depending on the operator assigned
-int pop() - returns a value from the top of the stack
-boolean isEmpty() - returns a boolean depending on if the stack is empty or not (true - empty) and (false - has stuff in it)
-int delayPop(int millis) - waits "millis" milliseconds before popping the stack



Implementation:

To ensure that each client has their own stack, each client has been manually assigned an ID that is unique to them. This ID is added as parameter for every method in order to check whether a new stack needs to be created or an existing stack can be used. This is done through the Stack<Integer> ManageStacks(clientID) method which uses HashMap to link the unique ID to seperate stacks. For each method that is required to be implemented, a temporary stack that is returned from the ManageStacks method is used so that operations can be conducted locally before putting it back on the server's HashMap.



Usage and testing:

The operations are done through client code after the the stub establishes connection with the server using the registry.lookup method. As shown in the CalculatorClient files the methods implemented can be called upon from the stub class with the same parameters as in the design brief but with the added addition of the clientID as an extra parameter.

Automated testing was done using the makefile that compiles the java files. There also exists the bash script "script.sh" which autograder cant run due to priviledges issues. This scripts runs rmiregistry first then the server followed by all 4 clients. As the method is simple, testing can be done for additional clients simply by adding an extra lines to the script and making another client java file.

An addition printStack(clientID) method is also implemented that pops and prints all of the values stored in the assigned stack and shows which ID stack it came from for testing purposes.

Currently, the CalculatorClient just pushes 1 to its respective stacks 10 times and then the printStack method is called (expected output is 50 lines of code saying 1 , This is from stack 1). CalculatorClient2 pushes 1 to 49 to its stack then its pushOperation(lcm) is called, the resulting value is then popped and printed out (expected output is 2520). CalculatorClient3 will check if its empty then push in 5 if its empty and pop and print out that 5 (expected output is 5 - using if statement so the 5 will not be returned if isEmpty method does not work). CalculatorClient4 will just push the number 17 and use delayPop by 5000 milliseconds (5 seconds) and output that result (expected output is 17 after 5 seconds).



Potential issues:

LCM and GCD function does not like 0 as the computer does not like dividing by 0. The makefile and the script does not take into account closing the server and rmiregistry due to ease of testing for individual clients. These may need to be closed manually after testing is completed. There are no commands that can be inputted to call the methods manually, the methods have to be invoked through the client java file beforehand.
