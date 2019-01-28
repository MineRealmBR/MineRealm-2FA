package com.minerealm.app.auth2fa;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.minerealm.app.auth2fa.frames.F2AFrame;

public class Main {

	public static File appFolder;
	public static File appFile;;
	public static Properties properties;
	public static String secretKey;
	public static String currentNumberString;

	public static void main(String[] args) throws Exception {
		properties = new Properties();
		appFolder = new File("MineRealm 2FA");
		if (!appFolder.exists())
			appFolder.mkdirs();
		appFile = new File(appFolder, "secretkey.properties");

		if (loadSecretKey()) {
			currentNumberString = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(secretKey);
			F2AFrame frame = new F2AFrame();
			frame.setVisible(true);
			while (true) {
				currentNumberString = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(secretKey);
				frame.textLabel.setText(currentNumberString);
				Thread.sleep(1000);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Sua SecretKey não é válida.");
		}

	}

	public static boolean loadSecretKey() {
		if (appFile.exists()) {
			try {
				properties.load(new FileReader(appFile));
				secretKey = properties.getProperty("secretkey");
			} catch (Exception exception) {
				return false;
			}
		} else {
			secretKey = JOptionPane.showInputDialog("Informe sua SecretKey");

			if (secretKey != null && !secretKey.isEmpty()) {
				try {
					currentNumberString = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(secretKey);

					properties.put("secretkey", secretKey);
					properties.store(new FileWriter(appFile), null);
				} catch (Exception exception) {
					return false;
				}
			}
		}
		return (secretKey != null && !secretKey.isEmpty());
	}

	public String getSecretKey() {
		return null;
	}

}
