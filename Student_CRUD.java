package main.webapp;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Student_CRUD {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtprice;
	private JTextField txtqty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_CRUD window = new Student_CRUD();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Student_CRUD() {
		initialize();
		Connect();
		LoadProductNo();
	
	}
	
	Connection con;
	PreparedStatement stmt = null;
	ResultSet  rs = null;
	
	JComboBox comboboxid = new JComboBox();

	public void Connect()
	{
		
		String url = "jdbc:mysql://localhost/database_1";
		String uname = "root";
		String pwrd = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Class.forname helps in loading and registering the jdbc driver although it is a deappreciated practice 
			con = DriverManager.getConnection(url,uname,pwrd); //Connection is an interface and DriverManager is the class that has already implemented it
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//function to load product number in combobox
	
	public void LoadProductNo() {
		
		
		try {
			stmt = con.prepareStatement("select id from mobile");
			rs = stmt.executeQuery();
			comboboxid.removeAllItems();
			
			
			while(rs.next())
			{
				comboboxid.addItem(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtname = new JTextField();
		txtname.setToolTipText("enter the student name");
		txtname.setBounds(64, 26, 120, 29);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setToolTipText("enter the student mobile number");
		txtprice.setColumns(10);
		txtprice.setBounds(64, 68, 120, 29);
		frame.getContentPane().add(txtprice);
		
		txtqty = new JTextField();
		txtqty.setToolTipText("enter the course");
		txtqty.setColumns(10);
		txtqty.setBounds(64, 108, 120, 29);
		frame.getContentPane().add(txtqty);
		
		JLabel lblNewLabel = new JLabel("name");
		lblNewLabel.setBounds(10, 26, 108, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMobile = new JLabel("price");
		lblMobile.setBounds(10, 68, 108, 29);
		frame.getContentPane().add(lblMobile);
		
		JLabel lblNewLabel_1_1 = new JLabel("qty");
		lblNewLabel_1_1.setBounds(10, 108, 108, 29);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton btnadd = new JButton("add");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtname.getText();
				String price = txtprice.getText();
				String qty = txtqty.getText();
				
				try {
					stmt = con.prepareStatement("insert into mobile(name,price,qty) values (?,?,?)");
				} catch (SQLException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
				
				try {
					stmt.setString(1,name);
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				try {
					stmt.setString(2,price);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					stmt.setString(3, qty);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
	
				try {
					int k = stmt.executeUpdate();
					if(k==1)
					{
						JOptionPane.showMessageDialog(new JFrame(),  "record added");
						
						txtname.setText("");
						txtprice.setText("");
						txtqty.setText("");
						txtname.requestFocus();
						LoadProductNo();
;						
					}
					else {
						JOptionPane.showMessageDialog(new JFrame(),  "record add failed");
					}
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
		
				
				
				
			}
		});
		btnadd.setToolTipText("press to add new data");
		btnadd.setBounds(10, 148, 76, 23);
		frame.getContentPane().add(btnadd);
		
		JButton btndelete = new JButton("delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = comboboxid.getSelectedItem().toString();
			
				
				try {
					stmt = con.prepareStatement("delete from mobile  where id = ?");
				} catch (SQLException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
				
				try {
					stmt.setString(1,pid);
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				
				
	
				try {
					stmt.executeUpdate();
					
						JOptionPane.showMessageDialog(new JFrame(),  "record deleted");
						
						txtname.setText("");
						txtprice.setText("");
						txtqty.setText("");
						txtname.requestFocus();
						LoadProductNo();
;						
				
					
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
		
				
				
				
			}
		});
		btndelete.setToolTipText("press to delete data");
		btndelete.setBounds(108, 148, 76, 23);
		frame.getContentPane().add(btndelete);
		
		JButton btnupdate = new JButton("update");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = comboboxid.getSelectedItem().toString();
				String name = txtname.getText();
				String price = txtprice.getText();
				String qty = txtqty.getText();
				
				try {
					stmt = con.prepareStatement("update mobile set name = ?, price = ?, qty = ? where id = ?");
				} catch (SQLException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}
				
				try {
					stmt.setString(1,name);
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				try {
					stmt.setString(2,price);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					stmt.setString(3, qty);
					stmt.setString(4,pid);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
	
				try {
					stmt.executeUpdate();
					
						JOptionPane.showMessageDialog(new JFrame(),  "record updated");
						
						txtname.setText("");
						txtprice.setText("");
						txtqty.setText("");
						txtname.requestFocus();
						LoadProductNo();
;						
				
					
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
		
				
				
				
			}
		});

	
		btnupdate.setToolTipText("press to edit data");
		btnupdate.setBounds(55, 181, 76, 23);
		frame.getContentPane().add(btnupdate);
		
		
		comboboxid.setEditable(false);
		comboboxid.setBounds(230, 53, 133, 22);
		frame.getContentPane().add(comboboxid);
		
		JLabel lblNewLabel_1 = new JLabel("product ID");
		lblNewLabel_1.setBounds(263, 26, 140, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnsearch = new JButton("search");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pid = comboboxid.getSelectedItem().toString();
				
				try {
					stmt = con.prepareStatement("select * from mobile where id = ?");
					stmt.setString(1, pid);
					rs = stmt.executeQuery();
					
					
					if(rs.next()==true)
					{
						txtname.setText(rs.getString(2));
						txtprice.setText(rs.getString(3));
						txtqty.setText(rs.getString(4));
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnsearch.setToolTipText("press to add new data");
		btnsearch.setBounds(194, 164, 89, 23);
		frame.getContentPane().add(btnsearch);
		
		JButton btnnew = new JButton("new");
		btnnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtname.setText("");
				txtprice.setText("");
				txtqty.setText("");
				
				comboboxid.setSelectedIndex(1);
			}
		});
		btnnew.setToolTipText("press to add new data");
		btnnew.setBounds(293, 164, 89, 23);
		frame.getContentPane().add(btnnew);
		
		JLabel lblNewLabel_2 = new JLabel("created by vishal mishra");
		lblNewLabel_2.setBounds(146, 223, 199, 27);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
