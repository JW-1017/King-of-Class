package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

import com.sun.javafx.scene.paint.GradientUtils.Point;

public class MarqeuePanel extends JPanel implements Runnable {
	private boolean IsStoped = false;
	private JTextArea ending_Comment_Textarea;
	public Thread t;
	private Font font1;

	public MarqeuePanel(Dimension d, Point p, String text) {
		super();
		ending_Comment_Textarea = new JTextArea();
		ending_Comment_Textarea.setLocation(0, this.getSize().height);

		font1 = new Font("한컴 바겐세일 B", Font.BOLD, 25);
		ending_Comment_Textarea.setFont(font1);

		ending_Comment_Textarea.setText(text);
		ending_Comment_Textarea.setForeground(Color.WHITE);

		ending_Comment_Textarea.setOpaque(false);
		ending_Comment_Textarea.setEditable(false);

		this.add(ending_Comment_Textarea);
		this.setSize(d);
		this.setOpaque(false);

		t = new Thread(this);
		t.start();
	}

	public void run() {
		ending_Comment_Textarea.setLocation(0, this.getSize().height);
		while (!IsStoped) {
			if (ending_Comment_Textarea.getLocation().y < -ending_Comment_Textarea.getSize().height) {
				ending_Comment_Textarea.setLocation(0, this.getSize().height);
			} else {
				int x = 0;
				int y = ending_Comment_Textarea.getLocation().y - 1;
				ending_Comment_Textarea.setLocation(x, y);
			}
			try {
				Thread.sleep(30);
			} catch (Exception exc) {
			}
		}
	}
}