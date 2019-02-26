class ATM{
	private int money;
	
	public ATM(int m){
		money = m;
	}
	
	// 입금
	// deposit -> 입금자, 금액
	// ㅇㅇㅇ가 0000원을 입금! 메세지 출력
	
	synchronized void deposit(String name, int amount){
		money += amount;
		System.out.println(name+"님이 "+amount+"원을 입금 하셨습니다.");
	}
	// 출금
	// withdraw -> 출금자, 금액
	// ㅇㅇㅇ 가 0000원을 출금! 메세지 출력
	// 잔액이 있을경우에만 출금, 잔액 부족 메시지 출력
	synchronized void withdraw(String name, int amount){
		if((money-amount) >= 0){
			// 출금 가능하면
			money -= amount;
			System.out.println(name+"님이 "+amount+"원을 출금 하셨습니다.");
		}else{ // 잔액 부족
			System.out.println(name+"님이 출금 실패 하셨습니다.(잔액부족)");
		}
	}
	
	// 잔액조회
	// 계좌 잔액은 : 00000원 입니다. 메세지 출력
	public void getMoney(){
		System.out.println("계좌 잔액은 : "+money+"원 입니다.");
	}
}

// 사용자
class AtmUser extends Thread{
	
	boolean flag = false; // 입금(true), 출금(false)
	ATM atm;
	
	// 사용자의 이름생성, 계좌벙보를 저장
	public AtmUser(String name, ATM atm){
		super(name);
		this.atm = atm;
	}
	
	@Override
	public void run() {
		for(int i=1;i<5;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag){
				atm.deposit(getName(), ((int)(Math.random()*10+2)*100));
				atm.getMoney();
			}else{
				atm.withdraw(getName(), ((int)(Math.random()*10+2)*100));
				atm.getMoney();
			}
			
			flag = !flag;
			
		}
	}
	
}


public class Test3 {

	public static void main(String[] args) {
		
		// 멀티쓰레드 프로그램(대부분의 프로그램) 하나의 자원을(프로그램을)
		// 여러개의 쓰레드가 동시에 접근하여 사용가능(자원을 공유)
		// => 동시에 같은 자원에 접근할수도있다.
		
		// 동기화 -> 임계영역(critical section)을 생성하여 하나의 쓰레드만
		// 작업을 처리할 수 있도록 만들어주는 공간(영역)
		// => 하나의 쓰레드가 작동하는 동안에 다른 쓰레들은 락으로 상태 변경하여
		// 다음 동작을 수행하기 전까지 대기
		
		// - 동기화 블럭 (주로 메서드 안에서 만들어짐)
		// public void Method(){
		/* 	synchronized (동기화 객체, 클래스명){
				임계영역에서의 처리구문
			}
		   }*/
		
		// - 메서드를 통한 동기화
		/* public synchronized void Method(){
		  // 임계영역에서의 처리구문
		 }*/
		
		ATM KB = new ATM(1000);
		AtmUser user1 = new AtmUser("홍길동",KB);
		AtmUser user2 = new AtmUser("갑돌이",KB);
		AtmUser user3 = new AtmUser("갑순이",KB);
		
		user1.start();
		user2.start();
		user3.start();
		
		
		
	}

}
