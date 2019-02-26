import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GUIClass extends Frame implements Runnable{
	
	int x=1;
	
	public GUIClass(){
		setBackground(new Color(0,0,0));
		setSize(400,150);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				dispose(); // 화면프레임을 종료
				System.exit(0); // 대상의 프로세스를 종료
			}
		});
	}// 생성자 끝

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 화면에 글자 그리기
			repaint(); // paint() 메서드를 명시적 호출
			x +=5;
		} // while(true)
		
	}// run()

	@Override
	public void paint(Graphics g) {
		Dimension d; // 프레임 폭, 높이를 저장하는 클래스
		d = getSize();
		
		g.setColor(Color.YELLOW);
		g.drawString("Hello JAVA!!!", x, d.height/2);
		
		if(x > d.width){
			x=0;
		}
		
	}
	
	
	
	
} // 클래스

public class Test2 {

	public static void main(String[] args) {
		// GUI + 메서드
		GUIClass gui = new GUIClass();
		
		Thread t = new Thread(gui);
		
		t.start();
	}

}
