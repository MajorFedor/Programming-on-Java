package map.UserRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Predicate;

import map.Users.User;
import map.UserIdentifire.UserIdentifire;

public class UserRegistry {
    HashMap<UserIdentifire,User> userList = new HashMap<>();
    private String userListPath = null;

    String userFileName= "userList.txt";
    File userListFile = new File(userListPath, userFileName);

    public String getUserListPath(){
        return userListPath;
    }

    public void setUserListPath(String path){
        userListPath = path;
    }

    public void registerUser(String name, String password){
        for( var user : userList.values()){
            if(user.getName().equals(name)){
                System.out.println("User already exits");
                return;
            }
        }
        
        User newUser = new User(name, password);
        userList.put(newUser.getUserIdentifire(), newUser);
        System.out.println("User registered" + name);
    }

    public User loginUser(String name, String password){
        for(var user : userList.values()){
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                user.setLoggedIn(true);
                user.setLastLoginDate(LocalDateTime.now());
                return user;
            }
        }
        return null;
    }

    public void logoutUser(Integer userId){
        for(var user : userList.values()){
            if(user.getId().equals(userId)){
                user.setLoggedIn(false);
                System.out.println("Logout from accoubnt.");
                return;
            }
        }
    }

    public boolean isUserRegistered(String login){
        for(var user : userList.values()){
            if(user.getName().equals(login)){
                System.out.println("User registered.");
                return true;
            }
        }
        
        return false;
    }

    public void removeUser(Integer id){
        UserIdentifire ui = null;
        for(var user : userList.values()){
            if(user.getId().equals(id)){
                ui = user.getUserIdentifire();
                break;
            }
        }
        if(ui !=null){
            userList.remove(ui);
            System.out.println("User removed!");
        }
    }

    public void printTotalUniqueUser(){
        for(var user : userList.values()){
            System.out.println("All uniques users" + user);
        }
    }

    public void displayAllUsers(){
        for(var user : userList.values()){
            System.out.println("Student{" + "name='" + user.getName() + "id='" + user.getName()  + "password='" + user.getPassword() +  "lastLoginDate='" + user.getLastLoginDate() + "isLoggedIn='" + user.isLoggedIn() + 
                '}');
        }
    }

    public LinkedList<User> getUserList(){
        LinkedList<User> ul = new LinkedList<>();
        for(var user : userList.values()){
            ul.add(user);
        }

        return ul;
    }
    
    public LinkedList<User> getInOrder(Comparator<User> comparator){
        LinkedList<User> ul = getUserList();
        ul.sort(comparator);
        return ul;
    }
    
    public LinkedList<User> getFiltered(Predicate<User> predicate){
        LinkedList<User> result = new LinkedList<>();
        for(var user: userList.values()){
            if(predicate.test(user)){
                result.add(user);
            }
        }
        return result;
    }

    public void saveUsers(){
        try {
            if(!userListFile.exists()){
                userListFile.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userListFile))) {
                oos.writeObject(userList);
                System.out.println("User list are serialization.");
            } catch (IOException e) {
                System.out.println("Eror: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Eror: " + e.getMessage());
        }
    }

    public HashMap<UserIdentifire, User> loadUsers(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userListFile))){
            userList = (HashMap<UserIdentifire, User>) ois.readObject();
            return userList;
        } catch (Exception e) {
            System.out.println("Eror: " + e.getMessage());
        }
        return null;
    }
}
