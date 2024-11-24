package Default;

public class Profile {
	private String name;
	private String surname;
    private String eyeColor;
    private double height;
    private String gender;
    private int age;

    public Profile(String name, String surname, String eyeColor, double height, String gender, int age) {
        this.name = name;
        this.surname = surname;
    	this.eyeColor = eyeColor;
        this.height = height;
        this.gender = gender;
        this.age = age;
    }

    // Getter e Setter
    public String getName() {
    	return name;
    }
    
    public void setName(String newName) {
    	this.name = newName;
    }
    
    public String getSurname() {
    	return surname;
    }
    
    public void setSurname(String newSurname) {
    	this.name = newSurname;
    }
    
    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
