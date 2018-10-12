package com.cying.webtoolkit.study.csdn;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculate implements ActionListener {
	String s = "";
	String s1 = "";
	String s2 = "";
	JTextField jt = new JTextField(20);
	String z = "+,-,*,/";
	String z1 = "0,1,2,3,4,5,6,7,8,9,.";
	double d;

	public void actionPerformed(ActionEvent e) {
		String s3 = e.getActionCommand();
		if (s3.contains(z1)) {
			s = s + s3;
			s2 = s;
			jt.setText(s2);
		}
		if (s3.contains(z)) {
			d = Double.parseDouble(s2);
			s = "";// ����
			s1 = s3;// ���������
		}
		if (s3.equals("=")) {
			if (s2.equals("+")) {
				s2 = (d + Double.parseDouble(s)) + "";// ʵ�ּӷ�
			}
			if (s2.equals("-")) {
				s2 = (d + Double.parseDouble(s)) + "";// ʵ�ּӷ�
			}
			if (s2.equals("*")) {
				s2 = (d + Double.parseDouble(s)) + "";// ʵ�ּӷ�
			}
			if (s2.equals("/")) {
				s2 = (d + Double.parseDouble(s)) + "";// ʵ�ּӷ�
			}
			jt.setText(s2);
			s = "";// �����������
		}
	}

	public Calculate() {
		JFrame jf = new JFrame("Calculate");
		JPanel p = new JPanel();// ���
		// JTextField f=new JTextField();
		JButton[] b = new JButton[16];
		// JButton add,sub,mul,div,equ,dot;
		// Calculate c=new Calculate();
		GridLayout grid = new GridLayout(4, 4);
		String[] lab = { "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "/", "=" };
		for (int i = 0; i < b.length; i++) {
			b[i] = new JButton(lab[i]);
			p.add(b[i]);
			b[i].addActionListener(this);
		}
		p.setLayout(grid);
		/*
		 * f.setBounds(35,15,200,25); p.setBounds(35,50,200,120);
		 * f.setHorizontalAlignment(JTextField.LEFT);
		 * p.add(b[7]);p.add(b[8]);p.add(b[9]);p.add(div);
		 * p.add(b[4]);p.add(b[5]);p.add(b[6]);p.add(mul);
		 * p.add(b[1]);p.add(b[2]);p.add(b[3]);p.add(sub);
		 * p.add(b[0]);p.add(dot);p.add(equ);p.add(add);
		 */

		jf.add(jt, BorderLayout.NORTH);
		jf.add(p);
		jf.setResizable(false);
		jf.setLocation(300, 200);
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Calculate();
	}

}
