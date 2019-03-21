import java.util.concurrent.locks.ReentrantLock;

//스레드 클래스 생성
class RunEx implements Runnable{

	private int idx;
	private int tot;
	
	//상호배제를 위한 lock 인스턴스 생성
	private ReentrantLock lock = new ReentrantLock();
	
	//스레드가 호출할 메소드 
	private void sum() {
		try {
			//idx를 천 번 증가해서 tot에 저장 
			for(int i=0; i<=1000; i=i+1) { 
				lock.lock();
				//synchronized(this){
				idx = idx + 1; 
				Thread.sleep(1);
				tot = tot + idx;
				//}
				lock.unlock();
				
			}
		}
		catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.getMessage();
		}
	}
	
	//tot값 넘겨주는 메소드
	public int getTot() {
		return tot;
	}
	
	@Override
	public void run() {
		try {
			sum();
		}
		catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.getMessage();
			}
			
	}
	
}

public class LockMain {

	public static void main(String[] args) {
		//RunEx의 인스턴스 생성
		RunEx ex = new RunEx();
		
		//동일한 객체를 사용하는 2개의 스레드가 동시에 작업을 수행하면 
		//동시 수정 문제가 나타날 수 있음 
		Thread th1 = new Thread(ex);
		th1.start();
		Thread th2 = new Thread(ex);
		th2.start();
		
		try {
			Thread.sleep(10000);
			System.out.printf("%d\n", ex.getTot());
		}
		catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.getMessage();
		}

	}

}
