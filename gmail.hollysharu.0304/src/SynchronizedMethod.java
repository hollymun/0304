

//스레드 클래스
class SumClass implements Runnable {

	//인덱스와 합계를 저장할 변수
	private int idx;
	private int sum;

	
	//idx 값을 10000번 증가 시켜서 sum에 추가 
	//synchronized를 붙이면 이 메소드의 수행 중에는 
	//이 메소드를 다시 호출할 수 없도록 함
	//이렇게 자신이 작업하는 동안 다른 작업이 끼어들 수 없도록 하는 것은
	//Atomicity(원자성)을 보장한다고 함 
	private /*synchronized*/ void add() {
		try {
			for(int i=0; i<10000; i=i+1) {
				//한번에 수행되어야 할 최소구역 찾기
				//이 영역 내에서는 this를 사용하는 부분은 동시에 수행할 수 없도록 함 
				synchronized(this) {
					idx = idx + 1; 
					Thread.sleep(1);
					sum = sum + idx; 
				}
			}
		}catch(Exception e) { }
	}
	
	//sum의 값을 리턴하는 메소드 
	public int getSum() {
		return sum;
	}
	
	//스레드로 수행될 메소드 
	public void run() {
		add();
	}
	
}

public class SynchronizedMethod {

	public static void main(String[] args) {
		SumClass sumClass = new SumClass();
		
		//2개의 스레드가 sumclass를 공유해서 2만 번 증가 시키면서 합계를 계산 
		Thread th1 = new Thread(sumClass);
		th1.start();
		Thread th2 = new Thread(sumClass);
		th2.start();
		
		try {
			Thread.sleep(30000);
			System.out.printf("합계: %d\n", sumClass.getSum());

		} catch (Exception e) {}
	
		
	}

}
