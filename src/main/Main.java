package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import java.util.Random;

import static java.lang.Math.*;

class SThread extends Thread {
	@Override
    public void run() {
		long st =  System.currentTimeMillis();
		double a = 0;
		for(int i = 1;i <= 1000*50000 ;i++) {
			a = cos(Main.r.nextDouble());
			a = sin(Main.r.nextDouble());
			a = log10(Main.r.nextDouble());
			a = tan(Main.r.nextDouble());
			a = cosh(Main.r.nextDouble());
			a = sinh(Main.r.nextDouble());
			a = tanh(Main.r.nextDouble());
			Main.s.setText("跑分中..."+i);
			Main.pb.setValue(i);
		}
		long et =  System.currentTimeMillis();
		long time = et - st;
		long ds = (long) ceil((100 - time / 1000.0)*100);
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
	public static JTabbedPane jtp = new JTabbedPane(3);
	
	public static JPanel paofen = new JPanel();
	public static JPanel setting = new JPanel();
	
	public static PopupMenu popupMenu = new PopupMenu();
	public static MenuItem open = new MenuItem("打开主程序");
	public static MenuItem close = new MenuItem("退出程序");
	
	public static JCheckBox soc = new JCheckBox("关闭时自动最小化");
	public static SystemTray st = SystemTray.getSystemTray();
	
	public static void main(String[] args) {
		mf.setResizable(false);
		if (SystemTray.isSupported()) {// 判断当前平台是否支持系统托盘
			soc.setSelected(true);
			try {
				ImageIcon img = new ImageIcon(Main.class.getResource("/main/logo.png"));
				mf.setIconImage(img.getImage());
				TrayIcon ti = new TrayIcon(img.getImage());
				ti.setImageAutoSize(true);
				ti.setImage(img.getImage());
				ti.setToolTip ("跑分");
				ti.setPopupMenu (popupMenu);
				ti.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseClicked(MouseEvent e) {
						mf.setVisible(true);
					}
				});
				st.add(ti);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
        }
		else {
			JOptionPane.showInternalMessageDialog(null, "您的电脑不支持系统托盘", "错误", JOptionPane.ERROR_MESSAGE);
			soc.setSelected(false);
			soc.setEnabled(false);
		}
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusStyle");
		}catch(Exception ignored) {
			
		}

		// TODO 自动生成的方法存根
		mf.setSize(230, 175);
		// 设置字体
		s.setFont(new java.awt.Font("黑体", Font.PLAIN, 16));
		start_btn.setFont(new java.awt.Font("黑体", Font.PLAIN, 21));
		// 按钮添加事件
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(t.isAlive()){
					Object[] options = {"确定","帮助"};
					int res = JOptionPane.showOptionDialog(null, "请等待跑分完成","错误",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
					if(res != JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, new StringBuilder().append("1.分数最大是10000，但一般情况下不可能跑到10000分。\n").append("2.没了").toString(), "帮助", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					s.setText("跑分中...");
					t.start();
				}
			}
		});
		pb.setStringPainted(true);
		paofen.add(BorderLayout.NORTH,s);
		paofen.add(BorderLayout.CENTER,start_btn);
		paofen.add(BorderLayout.SOUTH,pb);
		
		popupMenu.add(open);
		popupMenu.add(close);
		
		setting.add(soc);
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.setVisible(true);
			}
		});
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		jtp.addTab("跑分", paofen);
		jtp.addTab("设置", setting);
		jtp.setTabPlacement(JTabbedPane.LEFT);
		mf.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO 自动生成的方法存根

			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				if(soc.isSelected()) {
					mf.setVisible(false);
				}
				else {
					System.exit(0);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		mf.add(jtp);
		mf.setVisible(true);
		
	}
}
