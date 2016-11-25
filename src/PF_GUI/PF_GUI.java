package PF_GUI;

import javax.swing.JFrame;

import EventListener.PF_ActionListener;
import EventListener.PF_ChangeListener;
import EventListener.PF_KeyListener;


/**
 * 
 * @author Myeong-Un Ryu
 * @version 1.0.0
 * @since 16.11.16
 * 
 * PF_GUI_RESOURCE�� ����Ǿ ������ JFrame�� ����� Ŭ����
 * RESOURCE�� Ŭ������ ��� PF_GUI_RESOURCE���� SINGLETON���� ������ ������ ��ϰ� ȭ���� �����Ѵ�.
 *
 */

public class PF_GUI extends JFrame
{
	/** PF_GUI_ResourceŬ������ �̱��� ��ü **/
	private static final PF_GUI_Resource m_RESOURCE;

	
	/** static ���� ȣ�� **/
	static
	{
		m_RESOURCE = PF_GUI_Resource.get_Instance();
	}
	
	
	public PF_GUI()
	{
		// Jframe ������, OSȮ�� 
		init();
		
		// Listener ��� 
		add_listener();
		
		
	} // ������ end
	
	
	/**
	 * GUI�� Resource�� ȣ�� �Ͽ� �����ϴ� Method
	 */
	private void init()
	{
		// Ÿ��Ʋ ����
		String os = System.getProperty("os.name");
		if (os.indexOf("Win") != -1) 
		{ // �������� ���
			this.setTitle("Packet Flooder. Window ver @myeonguni.com");
		} 
		else if (os.indexOf("Lin") != -1) 
		{ // �������� ���
			this.setTitle("Packet Flooder. Linux ver @myeonguni.com");
		}
		
		
		// Jframe ���� 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 425, 400);
		this.setContentPane(m_RESOURCE.m_contentPane);
		
		
		// Protocol Type label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_protocolType);
		
		// TCP Protocol Radio btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_tcp);
		
		// UDP Protocol Radio btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_udp);
		
		// null1 Protocol Radio btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_null1);
		
		// null2 Protocol Radio btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_null2);
		
		// null3 Protocol Radio btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_rdbtn_null3);
		
		
		// NIC list label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_NIC);

		// NIC list ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_NIC_scroll);
		
		
		// Destination label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_destination);
		
		// IP Address label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_ip);
		
		// IP Address Text Field ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_tf_ip);
		
		// Port Number label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_port);

		// Port Number Text Field ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_tf_port);
		

		// Transmission control label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_transmission);

		// speed label ���		
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_speed);

		// speed Text Field ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_tf_speed);

		// speed slider ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_slider_speed);

		
		// Status label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_status);
		
		// Packets send label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_sendPacketsLbl);
		
		// Packets send value label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_sendPackets);
		
		// Seconds elapsed label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_elapsedSecondsLbl);

		// Seconds elapsed value label ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_lbl_elapsedSeconds);

		
		// Start btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_btn_flooder_Start);
		
		// Stop btn ���
		m_RESOURCE.m_contentPane.add(m_RESOURCE.m_btn_flooder_Stop);
		
	}
	
	/**
	 * Event Listener�� �߰� ��Ű�� �޼ҵ�
	 * EventListener ��Ű���� Listener �������� ���� �Ѵ�.
	 */
	private void add_listener()
	{
		// Start ��ư�� �̺�Ʈ ��� 
		m_RESOURCE.m_btn_flooder_Start.addActionListener(new PF_ActionListener());
		
		// Stop ��ư�� �̺�Ʈ ��� 
		m_RESOURCE.m_btn_flooder_Stop.addActionListener(new PF_ActionListener());
		
		// Speed �ؽ�Ʈ�ʵ忡 �Է�(����) ��ȿ�� üũ �̺�Ʈ ���
		m_RESOURCE.m_tf_speed.addKeyListener(new PF_KeyListener());
		
		// Speed �����̴��� �̺�Ʈ ���
		m_RESOURCE.m_slider_speed.addChangeListener(new PF_ChangeListener());
		
		
		/*
		.addChangeListener(new SliderListener());
		...
		class SliderListener implements ChangeListener {
		    public void stateChanged(ChangeEvent e) {
		        JSlider source = (JSlider)e.getSource();
		        if (!source.getValueIsAdjusting()) {
		            int fps = (int)source.getValue();
		            ...
		        }    
		    }
		}*/
	}

}
