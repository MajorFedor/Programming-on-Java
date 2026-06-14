package EBank.Persons;

import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements Serializable{
    protected String fullName;
    protected String passportNumber;
    protected long id;
    protected String mobileNumber;
    protected int age;
    protected String dateOfBirth;
    protected String residentialAddress;
    protected static long maxId = 10;

    public Person(String fullName, String passportNumber, String mobileNumber, int age, String dateOfBirth, String residentialAddress){
        this.fullName = fullName;
        this.passportNumber = passportNumber;
        this.id = ++maxId;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.residentialAddress = residentialAddress;
    }

    public Person(int id,String fullName, String passportNumber, String mobileNumber, int age, String dateOfBirth, String residentialAddress){
        if(id > maxId){
            maxId = id;
        }
        this.id=id;
        this.fullName = fullName;
        this.passportNumber = passportNumber;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.residentialAddress = residentialAddress;
    }

    public String getName(){
        return fullName;
    }

    public String getPassportNumber(){
        return passportNumber;
    }

    public long getId() {
        return this.id;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public int getAge() {
        return this.age;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getResidentialAddress() {
        return this.residentialAddress;
    }

    public static long getMaxId() {
        return maxId;
    }

    public void setName(String newName){
        this.fullName = newName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public static void setMaxId(long maxId) {
        Person.maxId = maxId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(id, person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
