package PLL;

import javax.swing.*;

import BLL.BLLFascade;
import BLL.IRootCRUDBO;
import BLL.RootCRUDBO;
import TransferObject.RootTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RootCRUDPO extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel rootLabel,errorLabel;
    JTextField rootTextField;
    JButton createRoot, updateRoot, deleteRoot,back ;
    
    IRootCRUDBO root1;
    IRootCRUDBO iroot = new RootCRUDBO(root1);
    BLLFascade businessFascade = new BLLFascade(iroot);
    RootTO root;


    public RootCRUDPO() {
    	
    	// Setting Title, Size, & Location of Window Swing
        setTitle("Root CRUD");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initializing Labels for Input
        rootLabel = new JLabel("Enter Root: ");
        errorLabel = new JLabel(" ");
        rootLabel.setSize(700, 350);
        // Initializing TextFields for Input 
        rootTextField = new JTextField(20);
        // Initializing Buttons for CRUD Operations
        createRoot = new JButton("Create Root");
        updateRoot = new JButton("Update Root");
        deleteRoot = new JButton("Delete Root");
        back = new JButton("Back");
        
        // Changing the Colors of the Buttons
        createRoot.setBackground(Color.GREEN);
        updateRoot.setBackground(Color.YELLOW);
        deleteRoot.setBackground(Color.RED);
        createRoot.setForeground(Color.WHITE);
        deleteRoot.setForeground(Color.WHITE);
        
        // Making a Title using Embedded HTML in Swing to make line breaks
        JLabel titleLabel = new JLabel("<html><br>Root CRUD Operations<br><br></html>", SwingConstants.CENTER);
        // Making Panel for Title of Page
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        
        // Making a Panel for Input
        JPanel inputPanel = new JPanel();
        root=new RootTO();
        inputPanel.setLayout(new GridLayout(1,2));
        // Adding Labels & TextField to the InputPanel
        inputPanel.add(rootLabel);
        inputPanel.add(rootTextField);
        inputPanel.add(errorLabel);
        // Making a Panel for Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createRoot);
        buttonPanel.add(updateRoot);
        buttonPanel.add(deleteRoot);  
        buttonPanel.add(back); 
        
        // Changing the Fonts Style, & Size
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Font inputFormFont = new Font("Arial", Font.BOLD, 40);
        // Changing Fonts for Buttons
        createRoot.setFont(buttonFont);
        updateRoot.setFont(buttonFont);
        deleteRoot.setFont(buttonFont);
        back.setFont(buttonFont);
        errorLabel.setFont(inputFormFont);
        // Changing Fonts for InputForm
        rootLabel.setFont(inputFormFont);
        rootTextField.setFont(inputFormFont);
        // Changing the Fonts for TitlePanel
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        
        // Setting Layout of the Application
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        createRoot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				root.setRoot(rootTextField.getText());
				
				if( rootTextField.getText().isEmpty() ) {
						showError();
				}
				else {
					hideError();
					
					iroot.createRoot(root);
				}
			}
    	});
        
        updateRoot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if( rootTextField.getText() == "" ) {
						showError();
				}
				else {
					hideError();
				}
			}
    	});
        
        deleteRoot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( rootTextField.getText() == "" ) {
					showError();
				}
				else {
					hideError();
					iroot.deleteRoot(root);
				}
			}
    	});
    }
        
      
         
    public void showError() {
    	errorLabel.setText("Error! Something Went Wrong.");
    }
    
    public void hideError() {
    	errorLabel.setText("");
    }


public static void main(String[] args) {
	
	SwingUtilities.invokeLater(() -> {
		RootCRUDPO p = new RootCRUDPO();
		p.setVisible(true);    		
    });
   
    
}
}
