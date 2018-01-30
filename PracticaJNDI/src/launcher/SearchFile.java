package launcher;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class SearchFile extends JFrame {

	private JPanel contentPane;
	private JTextField txtFile1;
	private JTextField txtFile2;
	private JTextField txtFile3;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFile frame = new SearchFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "file:///Users/ramsesdiezgalvan/Desktop/pruebaBusqueda" }));

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					searchFile();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		txtFile1 = new JTextField();
		txtFile1.setColumns(10);

		txtFile2 = new JTextField();
		txtFile2.setColumns(10);

		txtFile3 = new JTextField();
		txtFile3.setColumns(10);

		JLabel lblNewLabel = new JLabel("Directorio");

		JLabel lblFile = new JLabel("File 1");

		JLabel lblFile_1 = new JLabel("File 2");

		JLabel lblFile_2 = new JLabel("File 3");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(91)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
						.addComponent(lblFile).addComponent(lblFile_1).addComponent(lblFile_2))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtFile2, Alignment.LEADING).addComponent(txtFile3, Alignment.LEADING)
								.addComponent(txtFile1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 173,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton))
						.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(106)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(25)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel).addComponent(
						comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFile1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFile))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFile2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFile_1))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFile3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFile_2))
				.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE).addComponent(btnNewButton)
				.addGap(17)));
		contentPane.setLayout(gl_contentPane);
	}

	@SuppressWarnings("null")
	protected void searchFile() throws NamingException {

		String[] array = new String[4];

		array[0] = comboBox.getSelectedItem().toString();
		// creamos el initial context
		array[1] = txtFile1.getText().toString();
		array[2] = txtFile2.getText().toString();
		array[3] = txtFile3.getText().toString();

		Properties p = new Properties();
		// definimos la clase del driver

		p.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");

		p.put(Context.PROVIDER_URL, array[0]);
		Context ctx = new InitialContext(p);

		// busca los ficheros dentro del contexto
		for (int i = 1; i < array.length; i++) {
			try {

				ctx.lookup(array[i]);
				if (!array[i].equals("")) {
					JOptionPane.showMessageDialog(null, "Archivo: " + array[i] + " ENCONTRADO!!");

					System.out.println(array[i] + "  ENCONTRADO!!");
				}
			} catch (NamingException ex) {
				JOptionPane.showMessageDialog(null, "Archivo: " + array[i] + " NO EXISTE!!");

				System.out.println(array[i] + "  NO EXISTE");
			}
		}

	}// fin main

}
