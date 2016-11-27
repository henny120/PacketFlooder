package PacketFlooder;

import javax.swing.JButton;

public class PF_SendThread_Singleton {

	/** 패킷 전송 스레드 객체 **/
	public Thread m_Thread = new Thread();
	
	/** singleton을 위한 객체 **/
	private static volatile PF_SendThread_Singleton m_SendThread = null;
	
	public static synchronized PF_SendThread_Singleton getInstance() {
		if (m_SendThread == null) {
			synchronized (Thread.class) {
				if (m_SendThread == null) {
					m_SendThread = new PF_SendThread_Singleton();
				}
			}
		}
		return m_SendThread;
	}
	
}
