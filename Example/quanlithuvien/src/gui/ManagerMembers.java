package gui;

import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class ManagerMembers extends JPanel {
	public ManagerMembers() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("");

		JPanel wrapPanel1 = new JPanel();
		wrapPanel1.setLayout(new BoxLayout(wrapPanel1, BoxLayout.Y_AXIS));
		JPanel addMembers = new AddMembers();
		wrapPanel1.add(Box.createVerticalGlue());
		wrapPanel1.add(addMembers);
		wrapPanel1.add(Box.createVerticalGlue());
		JComponent panel1 = makeTextPanel("Panel #1", wrapPanel1);
		tabbedPane.addTab("Thêm thành viên", icon, panel1, "Add Members");

		JPanel wrapPanel2 = new JPanel();
		wrapPanel2.setLayout(new BoxLayout(wrapPanel2, BoxLayout.Y_AXIS));
		JPanel editMembers = new EditMembers();
		wrapPanel2.add(Box.createVerticalGlue());
		wrapPanel2.add(editMembers);
		wrapPanel2.add(Box.createVerticalGlue());
		JComponent panel2 = makeTextPanel("Panel #2", wrapPanel2);
		tabbedPane.addTab("Cập nhật thành viên", icon, panel2, "Edit Members");

		add(tabbedPane);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		int location = JTabbedPane.LEFT; // or TOP, BOTTOM, RIGHT
		// pane = new JTabbedPane(location);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
	}

	protected JComponent makeTextPanel(String text, JPanel panel) {
		// JPanel panel = new JPanel(false);
		// JLabel filler = new JLabel(text);
		// filler.setHorizontalAlignment(JLabel.CENTER);
		// panel.setLayout(new GridLayout(1, 1));
		// panel.add(filler);
		return panel;
	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ManagerMembers.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			//System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("ManagerMembers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new ManagerMembers(), BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}