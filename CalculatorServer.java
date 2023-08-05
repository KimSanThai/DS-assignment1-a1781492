import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;
import java.util.HashMap;

public class CalculatorServer extends CalculatorImplementation
{
	public static HashMap<Integer, Stack<Integer>> clientStacks = new HashMap<Integer, Stack<Integer>>();

	public static void main(String[] args)
	{
		try
		{
			CalculatorServer server = new CalculatorServer();

			Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 0);

			Registry registry = LocateRegistry.getRegistry();

			registry.rebind("CalculatorServer", stub);

			System.out.println("Server online");
		}
		catch(Exception e)
		{
			System.err.print("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}