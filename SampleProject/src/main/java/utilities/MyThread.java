package utilities;

public class MyThread extends Thread{
	
	public MyThread (String s) {
		super (s);
	} 
	
	public void run() {
		System.out.println("Run: "+getId());
	}

}
