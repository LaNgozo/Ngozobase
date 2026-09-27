/**
 * Stub class for ParentConsole
 * This prevents compilation errors when LoginForm references it
 * Replace with actual implementation
 * 
 * @author Mukelwe
 */
public class ParentConsole extends javax.swing.JFrame {
    
    public ParentConsole() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parent Console");
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        javax.swing.JLabel label = new javax.swing.JLabel("Parent Console - Coming Soon");
        label.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        add(label);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new ParentConsole().setVisible(true));
    }
}
