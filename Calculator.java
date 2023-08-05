import java.rmi.Remote;
import java.rmi.RemoteException;

//Calculator implementation
public interface Calculator extends Remote
{
	void pushValue(int val, int clientID) throws RemoteException;

	void pushOperation(String operator, int clientID) throws RemoteException;

	int lcm(int a, int b) throws RemoteException;

	int gcd(int a, int b) throws RemoteException;

	int pop(int clientID) throws RemoteException;

	boolean isEmpty(int clientID) throws RemoteException;

	int delayPop(int millis, int clientID) throws RemoteException;

	void printStack(int clientID) throws RemoteException;
}