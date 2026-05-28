package map.UserIdentifire;

import java.util.Objects;

public class UserIdentifire {
    private int id;
    private String name;

    public UserIdentifire(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        this.name = n;
    }

    @Override
    public boolean equals(Object o){
        if( o == null || getClass() != o.getClass()) return false;
        UserIdentifire user = (UserIdentifire) o;
        return Objects.equals(name, user.name) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, id);
    }
}
