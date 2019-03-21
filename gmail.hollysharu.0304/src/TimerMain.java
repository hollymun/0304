import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

//TimerTask 클래스를 생성 
class TimerEx extends TimerTask{
	//타이머로 수행할 내용 
	public void run() {
		System.out.printf("히히");
	}
}


public class TimerMain {

	public static void main(String[] args) {

		TimerEx ex = new TimerEx();
		Timer timer = new Timer();
		//10초 후 수행 - 3초마다 계속 수행 
		//timer.schedule(ex, 10000, 3000);
		
											//년, 월(-1달), 일, 시, 분, 초 
		Calendar cal = new GregorianCalendar(2019, 2, 4, 17, 33, 00);
		Date d = new Date(cal.getTimeInMillis());
		timer.schedule(ex, d);
		
		
	}

}
