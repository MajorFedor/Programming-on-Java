import Settings.*;
import Game.*;

public class Main {
    public static void main(String[] args){
        LoadSettings.checkExistingSettings();
        Menu.showMenu();
    }
}
