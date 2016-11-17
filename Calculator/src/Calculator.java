/**
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * @author Anh Nguyen T150510
 */
public class Calculator extends JFrame {
	JTextField txtIn = new JTextField(), txtIn00 = new JTextField();
	JPanel panbox = new JPanel(), panPr00 = new JPanel(),
			panPr01 = new JPanel();
	JRadioButton deg = new JRadioButton("Degrees"), rad = new JRadioButton(
			"Radians"), gra = new JRadioButton("Grads"),
			hex = new JRadioButton("Hex"), dec = new JRadioButton("Dec"),
			oct = new JRadioButton("Oct"), bin = new JRadioButton("Bin"),
			qword = new JRadioButton("Qword"),
			dword = new JRadioButton("Dword"), word = new JRadioButton("Word"),
			bit = new JRadioButton("Byte");
	JMenuBar bar = new JMenuBar();
	JMenu view = new JMenu("View"), edit = new JMenu("Edit"), help = new JMenu(
			"Help");
	JMenuItem stand = new JMenuItem("Standard", KeyEvent.VK_D),
			scien = new JMenuItem("Scientific", KeyEvent.VK_S),
			programmer = new JMenuItem("Programmer", KeyEvent.VK_P),
			exit = new JMenuItem("Exit", KeyEvent.VK_X), copy = new JMenuItem(
					"Copy", KeyEvent.VK_C), paste = new JMenuItem("Paste",
					KeyEvent.VK_V), about = new JMenuItem("About",
					KeyEvent.VK_A);
	ImageIcon imgcop = new ImageIcon("Icon/Copy.gif"), imgpas = new ImageIcon(
			"Icon/Paste.gif"), imgabout = new ImageIcon("Icon/about.gif");
	ButtonGroup btnGr = new ButtonGroup(), btnSel = new ButtonGroup(),
			btnSe = new ButtonGroup();;
	double dMemo = 0, dCalcu = 0;
	boolean fAppend = false;
	String sCal = "";
	Insets isMargin = new Insets(1, 1, 1, 1);

	public Calculator() {
		stand.setMnemonic(KeyEvent.VK_D);
		scien.setMnemonic(KeyEvent.VK_S);
		programmer.setMnemonic(KeyEvent.VK_P);
		exit.setMnemonic(KeyEvent.VK_X);
		copy.setMnemonic(KeyEvent.VK_C);
		paste.setMnemonic(KeyEvent.VK_V);
		about.setMnemonic(KeyEvent.VK_A);
		stand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.ALT_MASK));
		scien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		programmer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.ALT_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		copy.setIcon(imgcop);
		paste.setIcon(imgpas);
		about.setIcon(imgabout);
		inittalizeMenu();
		initScien();
		initStand();
		initProg();
		initEvent();
		displayMode(1);
		this.setTitle("cHW01_Calculator_T150510");
		// this.setPreferredSize(new Dimension(420, 450));
		// pack();
		this.setSize(270, 460);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.add(panbox);
		this.add(panPr00);
		this.add(panPr01);
		panPr00.setLayout(new FlowLayout(FlowLayout.LEFT));
		panPr01.setLayout(new FlowLayout(FlowLayout.LEFT));
		panbox.setLayout(new BorderLayout(14, 10));
		btnGr.add(deg);
		btnGr.add(rad);
		btnGr.add(gra);
		deg.setSelected(true);
		this.add(txtIn);
		txtIn.setEditable(false);
		txtIn.setText("0");
		panbox.add(deg, BorderLayout.WEST);
		panbox.add(rad, BorderLayout.CENTER);
		panbox.add(gra, BorderLayout.EAST);
		panbox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		btnSel.add(hex);
		btnSel.add(dec);
		btnSel.add(oct);
		btnSel.add(bin);
		btnSe.add(qword);
		btnSe.add(dword);
		btnSe.add(word);
		btnSe.add(bit);
		panPr00.add(hex);
		panPr00.add(oct);
		panPr00.add(dec);
		panPr00.add(bin);
		oct.setSelected(true);
		panPr00.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panPr01.add(qword);
		panPr01.add(dword);
		panPr01.add(word);
		panPr01.add(bit);
		qword.setSelected(true);
		panPr01.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		Font f = txtIn.getFont();
		txtIn.setFont(new Font(f.getName(), Font.BOLD, f.getSize() + 14));
		txtIn.setHorizontalAlignment(txtIn.RIGHT);

		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == stand) {
					displayMode(1);
				} else if (e.getSource() == scien) {
					displayMode(2);
				} else if (e.getSource() == programmer) {
					displayMode(3);
				} else if (e.getSource() == exit) {
					close();
				}
			}
		};
		stand.addActionListener(act);
		scien.addActionListener(act);
		programmer.addActionListener(act);
		exit.addActionListener(act);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				close();
			}
		});
	}

	public void initEvent() {
		ActionListener actNumber = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton btnNumber = (JButton) arg0.getSource();
				String sNumber = btnNumber.getText();
				String sCurrent = txtIn.getText();
				if (fAppend == true) {
					if (sCurrent.equals(0)) {
						txtIn.setText(sNumber);
					} else {
						txtIn.setText(sCurrent + sNumber);
					}
				} else {
					txtIn.setText(sNumber);
					fAppend = true;
				}
			}
		};
		btnStand[2][0].addActionListener(actNumber);
		btnStand[2][1].addActionListener(actNumber);
		btnStand[2][2].addActionListener(actNumber);
		btnStand[3][0].addActionListener(actNumber);
		btnStand[3][1].addActionListener(actNumber);
		btnStand[3][2].addActionListener(actNumber);
		btnStand[4][0].addActionListener(actNumber);
		btnStand[4][1].addActionListener(actNumber);
		btnStand[4][2].addActionListener(actNumber);
		btnStand[5][0].addActionListener(actNumber);
		btnStand[5][1].addActionListener(actNumber);

		ActionListener actMemo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent g) {
				// TODO Auto-generated method stub
				JButton btnMemo = (JButton) g.getSource();
				String sFunction = btnMemo.getText();

				if (sFunction.equals("MS")) {
					dMemo = Double.parseDouble(txtIn.getText());
				}
				if (sFunction.equals("MR")) {
					txtIn.setText("" + dMemo);
				}
				if (sFunction.equals("MC")) {
					dMemo = 0;
				}
				if (sFunction.equals("M+")) {
					dMemo = dMemo + Double.parseDouble(txtIn.getText());
				}
				if (sFunction.equals("M-")) {
					dMemo = dMemo - Double.parseDouble(txtIn.getText());
				}
				fAppend = false;
			}
		};
		btnStand[0][0].addActionListener(actMemo);
		btnStand[0][1].addActionListener(actMemo);
		btnStand[0][2].addActionListener(actMemo);
		btnStand[0][3].addActionListener(actMemo);
		btnStand[0][4].addActionListener(actMemo);

		ActionListener actCal = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnCal = (JButton) e.getSource();
				dCalcu = Double.parseDouble(txtIn.getText());
				sCal = btnCal.getText();
				fAppend = false;
			}
		};
		btnStand[5][2].addActionListener(actCal);
		btnStand[4][3].addActionListener(actCal);
		btnStand[3][3].addActionListener(actCal);
		btnStand[2][3].addActionListener(actCal);
		btnStand[2][4].addActionListener(actCal);
		btnStand[3][4].addActionListener(actCal);
		btnStand[1][4].addActionListener(actCal);
		btnStand[1][3].addActionListener(actCal);
		btnScien[1][2].addActionListener(actCal);
		btnScien[2][2].addActionListener(actCal);
		btnScien[3][2].addActionListener(actCal);
		btnScien[4][3].addActionListener(actCal);
		btnScien[0][2].addActionListener(actCal);
		btnScien[3][0].addActionListener(actCal);
		btnScien[1][3].addActionListener(actCal);
		btnScien[3][3].addActionListener(actCal);
		btnScien[2][3].addActionListener(actCal);
		btnScien[1][4].addActionListener(actCal);
		btnScien[3][4].addActionListener(actCal);
		btnScien[2][4].addActionListener(actCal);
		btnScien[4][4].addActionListener(actCal);
		btnScien[4][2].addActionListener(actCal);

		ActionListener acEqual = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sCal.equals("+")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu + dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("-")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu - dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("*")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu * dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("/")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu / dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("1/x")) {
					double result = 1 / dCalcu;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("sqrt")) {
					double result = Math.sqrt(dCalcu);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("+/-")) {
					txtIn.setText("" + (-dCalcu));
					fAppend = false;
				}
				if (rad.isSelected()) {
					if (sCal.equals("sin")) {
						double result = Math.sin(dCalcu);
						txtIn.setText("" + result);
						fAppend = false;
					}
					if (sCal.equals("cos")) {
						double result = Math.cos(dCalcu);
						txtIn.setText("" + result);
						fAppend = false;
					}
					if (sCal.equals("tan")) {
						double result = Math.tan(dCalcu);
						txtIn.setText("" + result);
						fAppend = false;
					}
				}
				if (deg.isSelected()) {
					if (sCal.equals("sin")) {
						double dTra = (dCalcu * Math.PI) / 180;
						double result = Math.sin(dTra);
						txtIn.setText("" + result);
						fAppend = false;
					}
					if (sCal.equals("cos")) {
						double dTra = (dCalcu * Math.PI) / 180;
						double result = Math.cos(dTra);
						txtIn.setText("" + result);
						fAppend = false;
					}
					if (sCal.equals("tan")) {
						double dTra = (dCalcu * Math.PI) / 180;
						double result = Math.tan(dTra);
						txtIn.setText("" + result);
						fAppend = false;
					}
				}
				if (sCal.equals("log")) {
					double result = Math.log10(dCalcu);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("ln")) {
					double result = Math.log(dCalcu);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("PI")) {
					double result = Math.PI;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("x^2")) {
					double result = dCalcu * dCalcu;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("x^3")) {
					double result = dCalcu * dCalcu * dCalcu;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("x^y")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = Math.pow(dCalcu, dCal);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("x^1/y")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = Math.pow(dCalcu, 1 / dCal);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("x^1/3")) {
					double result = Math.cbrt(dCalcu);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("10^x")) {
					double result = Math.pow(10, dCalcu);
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("n!")) {
					double result = 1;
					for (int i = 1; i <= dCalcu; i++) {
						result = result * i;
					}
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("Mod")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu % dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
			}
		};
		btnStand[5][4].addActionListener(acEqual);

		ActionListener actCl = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtIn.setText("0");
				fAppend = false;
			}
		};
		btnStand[1][1].addActionListener(actCl);

		ActionListener actClear = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtIn.setText("0");
				dCalcu = 0;
				fAppend = false;
			}
		};
		btnStand[1][2].addActionListener(actClear);

		ActionListener actSub = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s1 = txtIn.getText();
				int iSub = s1.length();
				if (iSub > 1) {
					String s2 = s1.substring(0, iSub - 1);
					txtIn.setText(s2);
				}
				if (iSub == 1) {
					txtIn.setText("0");
					fAppend = false;
				}
			}
		};
		btnStand[1][0].addActionListener(actSub);

	}

	String[][] sStard = { { "MC", "MR", "MS", "M+", "M-" },
			{ "<-", "CE", "C", "+/-", "sqrt" }, { "7", "8", "9", "/", "%" },
			{ "4", "5", "6", "*", "1/x" }, { "1", "2", "3", "-", "" },
			{ "0", ".", "+", "", "=" }, };
	String[][] sScientific = { { "", "Inv", "ln", "(", ")", },
			{ "Int", "sinh", "sin", "x^2", "n!" },
			{ "dms", "cosh", "cos", "x^y", "x^1/y" },
			{ "PI", "tanh", "tan", "x^3", "x^1/3" },
			{ "F-E", "Exp", "Mod", "log", "10^x" } };
	String[][] sProgram = { { "", "Mod", "A", }, { "(", ")", "B" },
			{ "RoL", "RoR", "C" }, { "Or", "Xor", "D" }, { "Lsh", "Rsh", "E" },
			{ "Not", "And", "F" } };
	JButton[][] btnProg = new JButton[6][3];
	JButton[][] btnScien = new JButton[5][5];
	JButton[][] btnStand = new JButton[6][5];
	JPanel panStand = new JPanel();
	JPanel panScien = new JPanel();
	JPanel panProg = new JPanel();
	int w = 40, h = 40, d = 10;
	int x = 0, y = 0;

	public void initStand() {
		panStand.setLayout(null);
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnStand[i][j] = new JButton(sStard[i][j]);
				panStand.add(btnStand[i][j]);
				btnStand[i][j].setBounds(x, y, w, h);
				btnStand[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		btnStand[5][4].setSize(w, h + d + h);
		btnStand[5][0].setSize(w + d + w, h);
		btnStand[5][1].setLocation(w + d + w + d, y - h - d);
		btnStand[5][2].setLocation(w + d + w + d + w + d, y - h - d);
		btnStand[5][4].setLocation(x - 50, y - 100);
		btnStand[4][4].setVisible(false);
		btnStand[5][4].setVisible(true);
		this.add(panStand);
		displayMode(1);
	}

	public void initScien() {
		panScien.setLayout(null);
		y = 0;
		for (int i = 0; i < 5; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnScien[i][j] = new JButton(sScientific[i][j]);
				panScien.add(btnScien[i][j]);
				btnScien[i][j].setBounds(x, y, w, h);
				btnScien[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panScien);
		displayMode(2);
	}

	public void initProg() {
		panProg.setLayout(null);
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 3; j++) {
				btnProg[i][j] = new JButton(sProgram[i][j]);
				panProg.add(btnProg[i][j]);
				btnProg[i][j].setBounds(x, y, w, h);
				btnProg[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panProg);
		displayMode(3);
	}

	public void displayMode(int mode) {
		if (mode == 2) {
			panProg.setVisible(false);
			panScien.setVisible(true);
			panStand.setVisible(true);
			panScien.setBounds(10, 150, x + 100, y);
			panStand.setBounds(270, 100, x + 100, y);
			txtIn.setBounds(10, 10, w + 460, h + 40);
			panbox.setSize(240, 40);
			panbox.setLocation(10, 100);
			panbox.setVisible(true);
			panPr00.setVisible(false);
			panPr01.setVisible(false);
			this.setSize(530, 460);
		} else if (mode == 1) {
			panScien.setVisible(false);
			panProg.setVisible(false);
			panStand.setVisible(true);
			panStand.setBounds(10, 110, x + 100, y);
			txtIn.setBounds(10, 10, w + 200, h + 50);
			panbox.setVisible(false);
			panPr00.setVisible(false);
			panPr01.setVisible(false);
			this.setSize(270, 460);
		} else if (mode == 3) {
			panPr00.setSize(80, 140);
			panPr00.setLocation(10, 100);
			panPr00.setVisible(true);
			panPr01.setSize(80, 140);
			panPr01.setLocation(10, 250);
			panPr01.setVisible(true);
			panScien.setVisible(false);
			panStand.setVisible(true);
			panbox.setVisible(false);
			txtIn.setBounds(10, 10, w + 450, h + 40);
			panProg.setVisible(true);
			panProg.setBounds(100, 100, x + 100, y);
			panStand.setBounds(260, 100, x + 100, y);
			this.setSize(520, 460);
		}
	}

	public void inittalizeMenu() {
		bar.add(view);
		bar.add(edit);
		bar.add(help);

		view.add(stand);
		view.addSeparator();
		view.add(scien);
		view.add(programmer);
		view.add(exit);

		edit.add(copy);
		edit.add(paste);

		help.add(about);

		setJMenuBar(bar);
	}

	public void close() {
		int res = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Yes",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (res == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator mainWindow = new Calculator();
		mainWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		mainWindow.setVisible(true);
	}

}
