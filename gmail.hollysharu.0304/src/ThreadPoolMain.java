import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//스레드 클래스
class ThreadExt extends Thread{
	public void run() {
	
		try {
			Thread.sleep(3000);
			//스레드 이름 출력
			System.out.printf("%s\n", this.getName());
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
		}
		
	}
}

public class ThreadPoolMain {

	public static void main(String[] args) {
	/*	
		//현재 컴퓨터의 프로세서 코어 수 확인 
		System.out.printf("코어 개수: %d\n", Runtime.getRuntime().availableProcessors());
		
		//10개의 스레드를 생성해서 모두 시작 
		for(int i=0; i<10; i=i+1) {
			ThreadExt th = new ThreadExt(); 
			th.start();
		}
	*/
		
		//threadPool 만들기
		//동시에 4개까지 스레드를 허용하는 스데드풀 생성 
		ExecutorService service = Executors.newFixedThreadPool(4);
		//스레드 10개를 생성해서 스데르풀에 추가
		//동시에 4개씩 수행함 
		for (int i=0; i<10; i=i+1) {
			ThreadExt th = new ThreadExt();
			service.submit(th);
		}
		
		//service.shutdown();
		
		//7초 동안 만들어진 작업을 수행하고 종료 
		try {
			service.awaitTermination(7, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}
		
		service.shutdownNow();
		
		//스레드풀의 중지
		//shutdown(): 현재 스레드풀에 있는 모든 작업을 수행하고 중지
		//awaitTermination(시간): 시간 동안 작업을 처리하고 중지 
		//shutdownNow(): 바로 중지 
		
		
	}
}
