import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient2
{
	//manually assigning each client an ID for seperate stack implementation
	public static int clientID = 2;

	public CalculatorClient2()
	{
	}

	public static void main(String[] args)
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry(0);
			Calculator stub = (Calculator) registry.lookup("CalculatorServer");

			for(int i = 1; i < 10; i++)
			{
				stub.pushValue(i, clientID);
			}

			stub.pushOperation("lcm",clientID);
			System.out.println(stub.pop(clientID));
		}
		catch (Exception e)
		{
			System.err.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}
}