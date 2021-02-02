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
			Main.s.setText("跑分中..."+i);
			Main.pb.setValue(i);
		}
		long et =  System.currentTimeMillis();
		long time = et - st;
		long ds = (long) Math.ceil((100 - time / 1000.0)*100);
		Main.s.setText("分数:"+ds);
    }
}

public class Main {
	// 定义所需变量
	public static JFrame mf = new JFrame("CPU测试");
	public static JLabel s = new JLabel("等待开始...",JLabel.CENTER);
	public static JButton start_btn = new JButton("开始跑分");
	public static Random r = new Random();
	public static JProgressBar pb = new JProgressBar(0, 50000000);
	public static SThread t = new SThread();
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setSize(200, 175);
		// 设置字体
		s.setFont(new java.awt.Font("黑体", 1, 16));
		start_btn.setFont(new java.awt.Font("黑体", 1, 21));
		// 按钮添加事件
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t.isAlive()){
					Object[] options = {"确定","帮助"};
					int res = JOptionPane.showOptionDialog(null, "请等待跑分完成","错误",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					if(res != JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "1.分数最大是10000，但一般情况下不可能跑到10000分。\n"+
																						   "2.没了", "帮助", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					s.setText("跑分中...");
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
