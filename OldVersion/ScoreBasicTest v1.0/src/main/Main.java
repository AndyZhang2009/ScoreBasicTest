package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;

class SThread extends Thread {
	@Override
    public void run() {
		long st =  System.currentTimeMillis();
		for(int i = 1;i <= 1000*50000 ;i++) {
			Math.cos(Main.r.nextDouble());
			Math.sin(Main.r.nextDouble());
			Math.log10(Main.r.nextDouble());
			Math.tan(Main.r.nextDouble());
			Math.cosh(Main.r.nextDouble());
			Math.sinh(Main.r.nextDouble());
			Math.tanh(Main.r.nextDouble());
			Main.s.setText("�ܷ���..."+i);
			Main.pb.setValue(i);
		}
		long et =  System.currentTimeMillis();
		long time = et - st;
		long ds = (long) Math.ceil((100 - time / 1000.0)*100);
		Main.s.setText("����:"+ds);
    }
}

public class Main {
	// �����������
	public static JFrame mf = new JFrame("CPU����");
	public static JLabel s = new JLabel("�ȴ���ʼ...",JLabel.CENTER);
	public static JButton start_btn = new JButton("��ʼ�ܷ�");
	public static Random r = new Random();
	public static JProgressBar pb = new JProgressBar(0, 50000000);
	public static SThread t = new SThread();
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setSize(200, 175);
		// ��������
		s.setFont(new java.awt.Font("����", 1, 16));
		start_btn.setFont(new java.awt.Font("����", 1, 21));
		// ��ť����¼�
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t.isAlive()){
					Object[] options = {"ȷ��","����"};
					int res = JOptionPane.showOptionDialog(null, "��ȴ��ܷ����","����",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					if(res != JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "1.���������10000����һ������²������ܵ�10000�֡�\n"+
																						   "2.û��", "����", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					s.setText("�ܷ���...");
					t.start();
				}
			}
		});
		pb.setStringPainted(true);
		mf.add(BorderLayout.NORTH,s);
		mf.add(BorderLayout.CENTER,start_btn);
		mf.add(BorderLayout.SOUTH,pb);
		mf.setVisible(true);
	}
}
