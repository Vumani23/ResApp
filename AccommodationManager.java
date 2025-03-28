class Student {
    private String studentID;
    private String password;
    
    public Student(String studentID, String password) {
        this.studentID = studentID;
        this.password = password;
    }
    
    public String getStudentID() {
        return studentID;
    }
    
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

class Accommodation {
    private String name;
    private String type;
    private int totalSpaces;
    private int availableSpaces;

    public Accommodation(String name, String type, int totalSpaces, int availableSpaces) {
        this.name = name;
        this.type = type;
        this.totalSpaces = totalSpaces;
        this.availableSpaces = availableSpaces;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public int getAvailableSpaces() {
        return availableSpaces;
    }

    @Override
    public String toString() {
        return name + " (" + type + ") - Available: " + availableSpaces;
    }
}
