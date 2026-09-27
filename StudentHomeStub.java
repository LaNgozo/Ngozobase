/**
 * Stub class for StudentHome
 * This prevents compilation errors when LoginForm references it
 * Replace with actual implementation
 * 
 * @author Mukelwe
 */
public class StudentHome extends javax.swing.JFrame {
    
    public StudentHome() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Home");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        javax.swing.JLabel label = new javax.swing.JLabel("Student Home - Coming Soon");
        label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        add(label);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new StudentHome().setVisible(true));
    }
}
