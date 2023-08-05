import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient4
{
	//manually assigning each client an ID for seperate stack implementation
	public static int clientID = 4;

	public CalculatorClient4()
	{
	}

	public static void main(String[] args)
	{
		try
		{
			Registry registry = LocateRegistry.getRegistry(0);
			Calculator stub = (Calculator) registry.lookup("CalculatorServer");

			stub.pushValue(17, clientID);
			System.out.println(stub.delayPop(5000, clientID));
		}
		catch (Exception e)
		{
			System.err.println("Client Exception: " + e.toString());
			e.printStackTrace();
		}
	}
}