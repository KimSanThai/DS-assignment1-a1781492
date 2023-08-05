import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient
{
	//manually assigning each client an ID for seperate stack implementation
	public static int clientID = 1;

	public CalculatorClient()
	{
	}

	public static void main(String[] args)
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry(0);
			Calculator stub = (Calculator) registry.lookup("CalculatorServer");

			for(int i = 0; i < 10; i++)
			{
				stub.pushValue(1, clientID);
			}

			stub.printStack(clientID);
		}
		catch (Exception e)
		{
			System.err.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
