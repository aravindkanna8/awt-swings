package jsmSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.*;

public class EmployeeAdd implements WindowListener {
    JFrame f;
    EmployeeAdd() {
        f = new JFrame("Add Employee");
        f.setSize(375,700);
        f.setLayout(null);
        f.setResizable(false);
       // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title,name,fname,address,city,pin,mob,pan,pass,cpass,constraints,constraint1,constraint2,constraint3;
        JTextField nameT,fnameT,cityT,pinT,mobT,panT;
        JTextArea addressT;
        JPasswordField passT,cpassT;
        JCheckBox terms;
        JButton upload,submit;

        Font f1 = new Font("Times New Roman",Font.BOLD,20);
        Font f2 = new Font("Times New Roman",Font.PLAIN,20);

        title = new JLabel("Add Employee Details");
        title.setFont(f1);
        title.setBounds(95,43,190,22);
        f.add(title);

        name = new JLabel("Name:");
        name.setBounds(33,85,58,22);
        name.setFont(f2);
        f.add(name);

        fname = new JLabel("Father's Name:");
        fname.setBounds(33,127,121,22);
        fname.setFont(f2);
        f.add(fname);

        address= new JLabel("Address:");
        address.setBounds(33,169,76,22);
        address.setFont(f2);
        f.add(address);

        city = new JLabel("City:");
        city.setBounds(33,229,45,22);
        city.setFont(f2);
        f.add(city);

        pin = new JLabel("Pin:");
        pin.setBounds(226,229,32,22);
        pin.setFont(f2);
        f.add(pin);

        mob = new JLabel("Mobile Number:");
        mob.setBounds(33,271,139,22);
        mob.setFont(f2);
        f.add(mob);

        pan = new JLabel("PAN:");
        pan.setBounds(33,313,49,22);
        pan.setFont(f2);
        f.add(pan);

        pass = new JLabel("Enter Password:");
        pass.setBounds(33,355,131,22);
        pass.setFont(f2);
        f.add(pass);

        cpass = new JLabel("Confirm Password:");
        cpass.setBounds(33,508,155,22);
        cpass.setFont(f2);
        f.add(cpass);

        nameT = new JTextField();
        nameT.setBounds(172,85,177,22);
        nameT.setFont(f2);
        f.add(nameT);

        fnameT = new JTextField();
        fnameT.setBounds(172,127,177,22);
        fnameT.setFont(f2);
        f.add(fnameT);

        addressT = new JTextArea();
        addressT.setBounds(172,169,177,40);
        addressT.setFont(new Font("Times New Roman",Font.PLAIN,15));
        f.add(addressT);

        cityT = new JTextField();
        cityT.setBounds(82,229,140,22);
        cityT.setFont(f2);
        f.add(cityT);

        pinT = new JTextField();
        pinT.setBounds(262,229,87,22);
        pinT.setFont(f2);
        f.add(pinT);

        mobT = new JTextField();
        mobT.setBounds(172,271,177,22);
        mobT.setFont(f2);
        f.add(mobT);

        panT = new JTextField();
        panT.setBounds(172,313,177,22);
        panT.setFont(f2);
        f.add(panT);

        passT = new JPasswordField();
        passT.setBounds(172,355,177,22);
        f.add(passT);

        cpassT = new JPasswordField();
        cpassT.setBounds(188,508,161,22);
        f.add(cpassT);

        constraints = new JLabel("Note that password must contain at least");
        constraints.setFont(f2);
        constraints.setBounds(25,397,350,22);
        f.add(constraints);

        constraint1 = new JLabel("1 Uppercase");
        constraint1.setFont(f2);
        constraint1.setBounds(25,419,323,22);
        f.add(constraint1);

        constraint2 = new JLabel("1 Number");
        constraint2.setFont(f2);
        constraint2.setBounds(25,441,323,22);
        f.add(constraint2);

        constraint3 = new JLabel("1 Special Character");
        constraint3.setFont(f2);
        constraint3.setBounds(25,463,323,22);
        f.add(constraint3);

        terms = new JCheckBox("I accept the terms & conditions");
        terms.setBounds(33,573,285,22);
        terms.setFont(f2);
        f.add(terms);

     

        submit = new JButton("Submit");
        submit.setFont(f2);
        submit.setBounds(138,605,100,22);
        f.add(submit);

        f.setVisible(true);
        

        passT.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                constraint1.setForeground(Color.RED);
                constraint2.setForeground(Color.RED);
                constraint3.setForeground(Color.RED);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String regex1 = "(?=.*[A-Z]).+$";
                String regex2 = "(?=.*\\d).+$";
                String regex3 ="^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                Pattern constraint1P = Pattern.compile(regex1);
                Pattern constraint2P = Pattern.compile(regex2);
                Pattern constraint3P = Pattern.compile(regex3);

                Matcher matcher1 = constraint1P.matcher(passT.getText());
                Matcher matcher2 = constraint2P.matcher(passT.getText());
                Matcher matcher3 = constraint3P.matcher(passT.getText());

                if(matcher1.matches()) {
                    constraint1.setForeground(Color.GREEN);
                }
                if(matcher2.matches()) {
                    constraint2.setForeground(Color.GREEN);
                }
                if(matcher3.matches()) {
                    constraint3.setForeground(Color.GREEN);
                }
            }
        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern mobilePattern = Pattern.compile("(0/91)?[6-9][0-9]{9}");
                Pattern pinPattern = Pattern.compile("^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$");
                Pattern PANPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

                Matcher mobileMatch = mobilePattern.matcher(mobT.getText());
                Matcher pinMatch = pinPattern.matcher(pinT.getText());
                Matcher PANMatch = PANPattern.matcher(panT.getText().toUpperCase());

                boolean PlainText = nameT.getText().equals("") || fnameT.getText().equals("") || addressT.getText().equals("") || cityT.getText().equals("");
                if(!(mobileMatch.matches() && pinMatch.matches() && PANMatch.matches() && passT.getText().matches(cpassT.getText()) && !(PlainText) && terms.isSelected() &&
                        Pattern.matches("^[^0-9]+$",nameT.getText()) && Pattern.matches("^[^0-9]+$",fnameT.getText()) && Pattern.matches("^[^0-9]+$",cityT.getText())))
                    JOptionPane.showMessageDialog(null,"Please check the data you have entered");
                else {
                    add(nameT.getText(),fnameT.getText(),addressT.getText(),cityT.getText(),pinT.getText(),mobT.getText(),panT.getText().toUpperCase(),passT.getText());
                }
            }
        });
    }

    public static void main(String[] args) {
        new EmployeeAdd();
    }

    void add(String name1,String fname1,String add1,String city1,String pin1,String mob1,String pan1,String pass1) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "jsmSystem", "pass");
            Statement st = con.createStatement();
            String id = "EM003";
            int salary = 20000;
            String query = "insert into Employee(ID,name,fname,address,city,pin,mobile,password,pan,salary) values ('" + id + "','" + name1 + "','" + fname1 + "','" + add1 + "','" +
                    city1 + "','" + pin1 + "','" + mob1 + "','" + pass1 + "','" + pan1 + "'," + salary +")";
        
            st.executeUpdate(query);
            con.setAutoCommit(true);
            con.close();
            JOptionPane.showMessageDialog(null, "Success!");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
        }
    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}