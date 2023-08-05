import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient3
{
	//manually assigning each client an ID for seperate stack implementation
	public static int clientID = 3;

	public CalculatorClient3()
	{
	}

	public static void main(String[] args)
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry(0);
			Calculator stub = (Calculator) registry.lookup("CalculatorServer");

			if(stub.isEmpty(clientID))
			{
				stub.pushValue(5, clientID);
				System.out.println(stub.pop(clientID));
			}
		}
		catch (Exception e)
		{
			System.err.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}
}