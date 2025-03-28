import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class StudentAccommodationGUI {
    private static Map<String, Student> students = new HashMap<>();
    private static List<String> courses = Arrays.asList("Engineering", "Business", "Science", "Education");
    private static List<Accommodation> accommodations = new ArrayList<>();
    
    public static void main(String[] args) {
        students.put("12345", new Student("12345", "password123")); // Example student
        accommodations.add(new Accommodation("Bellville Campus Dorms", "School-owned", 100, 50));
        accommodations.add(new Accommodation("My Domain Observatory", "Private", 50, 25));
        accommodations.add(new Accommodation("Tygervalley Residence", "CPUT-accredited", 80, 40));

        showLoginWindow();
    }
    
    private static void showLoginWindow() {
        JFrame loginFrame = new JFrame("Student Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(null);
        
        JLabel userLabel = new JLabel("Student ID:");
        userLabel.setBounds(10, 30, 100, 25);
        loginFrame.add(userLabel);
        
        JTextField userField = new JTextField();
        userField.setBounds(120, 30, 150, 25);
        loginFrame.add(userField);
        
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 70, 100, 25);
        loginFrame.add(passLabel);
        
        JPasswordField passField = new JPasswordField();
        passField.setBounds(120, 70, 150, 25);
        loginFrame.add(passField);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = userField.getText().trim();
                String password = new String(passField.getPassword()).trim();
                
                if (students.containsKey(studentID) && students.get(studentID).validatePassword(password)) {
                    loginFrame.dispose();
                    showCourseSelection(studentID);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        loginFrame.add(loginButton);
        loginFrame.setVisible(true);
    }
    
    private static void showCourseSelection(String studentID) {
        JFrame courseFrame = new JFrame("Select Course");
        courseFrame.setSize(300, 200);
        courseFrame.setLayout(null);
        
        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setBounds(10, 30, 100, 25);
        courseFrame.add(courseLabel);
        
        JComboBox<String> courseDropdown = new JComboBox<>(courses.toArray(new String[0]));
        courseDropdown.setBounds(120, 30, 150, 25);
        courseFrame.add(courseDropdown);
        
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(100, 80, 80, 25);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) courseDropdown.getSelectedItem();
                courseFrame.dispose();
                showMarksInput(selectedCourse);
            }
        });
        
        courseFrame.add(nextButton);
        courseFrame.setVisible(true);
    }

    private static void showMarksInput(String selectedCourse) {
        JFrame marksFrame = new JFrame("Enter Marks for " + selectedCourse);
        marksFrame.setSize(350, 250);
        marksFrame.setLayout(null);
        
        JLabel marksLabel = new JLabel("Enter your marks (comma-separated):");
        marksLabel.setBounds(10, 30, 250, 25);
        marksFrame.add(marksLabel);

        JTextField marksField = new JTextField();
        marksField.setBounds(10, 60, 250, 25);
        marksFrame.add(marksField);
        
        JButton checkButton = new JButton("Check Eligibility");
        checkButton.setBounds(10, 100, 150, 25);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String marksInput = marksField.getText().trim();
                if (isEligible(marksInput)) {
                    marksFrame.dispose();
                    showAccommodationSelection();
                } else {
                    JOptionPane.showMessageDialog(marksFrame, "You do not qualify for accommodation!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        marksFrame.add(checkButton);
        marksFrame.setVisible(true);
    }

    private static boolean isEligible(String marksInput) {
        try {
            String[] marksArray = marksInput.split(",");
            int totalMarks = 0;
            for (String mark : marksArray) {
                totalMarks += Integer.parseInt(mark.trim());
            }
            double average = (double) totalMarks / marksArray.length;
            return average >= 50; // Minimum 50% required to be eligible
        } catch (Exception e) {
            return false;
        }
    }

    private static void showAccommodationSelection() {
        JFrame accommodationFrame = new JFrame("Select Accommodation");
        accommodationFrame.setSize(400, 300);
        accommodationFrame.setLayout(null);
        
        JLabel accLabel = new JLabel("Available CPUT-owned/accredited options:");
        accLabel.setBounds(10, 30, 300, 25);
        accommodationFrame.add(accLabel);
        
        JComboBox<String> accommodationDropdown = new JComboBox<>();
        for (Accommodation acc : accommodations) {
            if (acc.getType().equalsIgnoreCase("School-owned") || acc.getType().equalsIgnoreCase("CPUT-accredited")) {
                accommodationDropdown.addItem(acc.getName());
            }
        }
        accommodationDropdown.setBounds(10, 60, 300, 25);
        accommodationFrame.add(accommodationDropdown);
        
        JButton selectButton = new JButton("Apply");
        selectButton.setBounds(10, 100, 80, 25);
        selectButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(accommodationFrame, "You have applied for: " + accommodationDropdown.getSelectedItem());
            accommodationFrame.dispose();
        });

        accommodationFrame.add(selectButton);
        accommodationFrame.setVisible(true);
    }
}
