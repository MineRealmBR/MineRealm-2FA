package com.minerealm.app.auth2fa.frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.minerealm.app.auth2fa.Main;
import com.minerealm.app.auth2fa.SystemClipboard;

public class F2AFrame extends JFrame {

	private static final long serialVersionUID = 8044533763690408938L;
	private JPanel contentPane;
	public JLabel textLabel;

	public F2AFrame() {
		setTitle("MineRealm 2FA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textLabel = new JLabel((Main.currentNumberString != null) ? Main.currentNumberString : "XXXXXX");
		textLabel.setForeground(Color.DARK_GRAY);
		textLabel.setFont(new Font("Arial Black", Font.PLAIN, 65));
		textLabel.setBounds(10, 11, 424, 109);
		contentPane.add(textLabel);

		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SystemClipboard.copy(textLabel.getText().replace(" ", ""));
				btnCopiar.setText("Copiado!");
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						btnCopiar.setText("Copiar");
					}
				}, 1000);
			}
		});
		btnCopiar.setToolTipText("Clique para copiar o c\u00F3digo");
		btnCopiar.setBackground(Color.LIGHT_GRAY);
		btnCopiar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		btnCopiar.setBounds(10, 113, 424, 47);
		contentPane.add(btnCopiar);
		setLocationRelativeTo(null);
	}

}
