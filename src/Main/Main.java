package Main;

import java.io.File;

import javax.swing.UnsupportedLookAndFeelException;

import PF_GUI.PF_GUI;


/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 *
 */

public class Main {

	/**
	 * Native Library Load (jpcap.dll)
	 */
	static {
		try {
			System.load(new File("jpcap.dll").getAbsolutePath());
			System.out.println(new File("jpcap.dll").getAbsolutePath());
		} catch (UnsatisfiedLinkError e) {
			System.out.println("Native code library failed to load.\n" + e);
			System.exit(1);
		}
	}// static end

	/**
	 * 프로그램의 진입 메소드
	 * 룩앤필을 사용
	 * UI Thread가 죽을 수도 있음으로 Thread를 사용해서 GUI호출
	 * 
	 * @param args 매개변수
	 * @throws ClassNotFoundException 예외처리
	 * @throws InstantiationException 예외처리
	 * @throws IllegalAccessException 예외처리
	 * @throws UnsupportedLookAndFeelException 예외처리
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		// 룩앤필 적용 1 (JDK에 내장된 Nimbus)
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			} // if end
		} // for end
		
		// 룩앤필 적용 2 (Quaqua.jar 맥 스타일)
		// javax.swing.UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		// javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);

		
		// Thread를 사용하여서 GUI를 호출
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new PF_GUI().setVisible(true);
			} // run end
		}); // runnable end

	}

}
