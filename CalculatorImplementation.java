import java.rmi.RemoteException;
import java.util.Stack;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//implementation for Calculator's abstract method
public class CalculatorImplementation implements Calculator
{
	//stack access for the clients or create new stack for new clients that done have one
	public Stack<Integer> ManageStacks(int ID)
	{
		if(CalculatorServer.clientStacks.containsKey(ID))
		{
			return CalculatorServer.clientStacks.get(ID);
		}
		else
		{
			Stack<Integer> stack = new Stack<Integer>();
			CalculatorServer.clientStacks.put(ID, stack);
			return stack;
		}
	}

	//pushValue takes val and pushes it on to the top of the stack
	public void pushValue(int val, int clientID)
	{
		Stack<Integer> tmp = ManageStacks(clientID);
		tmp.push(val);
		CalculatorServer.clientStacks.put(clientID,tmp);
	}

	//void push operation pushes different values onto the stack depending on operator
	//min - pushes min val of popped val, max - pushes max val of popped val
	//lcm - pushes least common multiple of popped val, gcd - pushes greatest common divisor of popped val
	public void pushOperation(String operator, int clientID)
	{
		//implemenation for pushOperation(min)
		//pops value until stack is empty and stores lowest value to be pushed back in later
		if(operator.equals("min"))
		{
			Stack<Integer> tmpStack = ManageStacks(clientID);
			int ValToBePushed = 0;

			while(!tmpStack.isEmpty())
			{
				int tmpPoppedVal = tmpStack.pop();

				if(ValToBePushed == 0 || ValToBePushed > tmpPoppedVal)
				{
					ValToBePushed = tmpPoppedVal;
				}
			}

			tmpStack.push(ValToBePushed);
			CalculatorServer.clientStacks.put(clientID, tmpStack);
		}

		//implemenation for pushOperation(max)
		//pops value until stack is empty and stores largest value to be pushed back in later
		if(operator.equals("max"))
		{
			Stack<Integer> tmpStack = ManageStacks(clientID);
			int ValToBePushed = 0;

			while(!tmpStack.isEmpty())
			{
				int tmpPoppedVal = tmpStack.pop();

				if(ValToBePushed == 0 || ValToBePushed < tmpPoppedVal)
				{
					ValToBePushed = tmpPoppedVal;
				}
			}

			tmpStack.push(ValToBePushed);
			CalculatorServer.clientStacks.put(clientID, tmpStack);
		}

		//implementation for lcm on stack - recursively calls lcm method for answer against next value in stack
		if(operator.equals("lcm"))
		{
			Stack<Integer> tmpStack = ManageStacks(clientID);
			int ValToBePushed = 0;

			while(!tmpStack.isEmpty())
			{
				if(ValToBePushed == 0)
				{
					ValToBePushed = tmpStack.pop();
				}
				else
				{
						ValToBePushed = lcm(ValToBePushed, tmpStack.pop());
				}
			}

			tmpStack.push(ValToBePushed);
			CalculatorServer.clientStacks.put(clientID, tmpStack);
		}

		//implementation for gcd on stack - recursively calls gcd method for answer against next value in stack
		if(operator.equals("gcd"))
		{
			Stack<Integer> tmpStack = ManageStacks(clientID);
			int ValToBePushed = 0;

			while(!tmpStack.isEmpty())
			{
				if(ValToBePushed == 0)
				{
					ValToBePushed = tmpStack.pop();
				}
				else
				{
					ValToBePushed = gcd(ValToBePushed, tmpStack.pop());
				}
			}

			tmpStack.push(ValToBePushed);
			CalculatorServer.clientStacks.put(clientID, tmpStack);
		}
	}

	// //implementation for lcm for 2 numbers
	public int lcm(int a, int b)
	{
		int max = (a > b) ? a : b;

		while(true)
		{
			if ((max % a == 0) && (max % b == 0))
			{
				return max;
			}
			else
			{
				++max;
			}
		}
	}

	// //implementation for gcd for 2 numbers
	public int gcd(int a, int b)
	{
		int gcd = 0;

		for(int i = 1; i <= a && i <= b; ++i)
		{
			if(a % i == 0 && b % i == 0)
			{
				gcd = i;
			}
		}

		return gcd;
	}

	// //implementation for pop()
	public int pop(int clientID)
	{
		Stack<Integer> tmpStack = ManageStacks(clientID);
		int tmpVal = tmpStack.pop();
		CalculatorServer.clientStacks.put(clientID, tmpStack);
		return tmpVal;
	}

	// //implemenation for isEmpty()
	public boolean isEmpty(int clientID)
	{
		Stack<Integer> tmpStack = ManageStacks(clientID);
		return tmpStack.isEmpty();
	}

	// //delays for millis milliseconds before popping
	public int delayPop(int millis, int clientID)
	{
		try
		{
			TimeUnit.MILLISECONDS.sleep(millis);
			Stack<Integer> tmpStack = ManageStacks(clientID);
			int tmpVal = tmpStack.pop();
			CalculatorServer.clientStacks.put(clientID, tmpStack);
			return tmpVal;
		}
		catch(InterruptedException e)
		{
			System.err.println("failed");
			return 0;
		}
	}

	// //print entire stack - FOR TESTING PURPOSES!!!
	public void printStack(int clientID)
	{
		while(!CalculatorServer.clientStacks.get(clientID).isEmpty())
		{
			System.out.println(CalculatorServer.clientStacks.get(clientID).pop() + " , This is from stack " + clientID);
		}
	}
}