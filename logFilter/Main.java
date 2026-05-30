package logFilter;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import logFilter.LogLevel.LogLevel;
import logFilter.ServeLogFilter.ServerLogFilter;

public class Main {
    public static void main(String[] args) {
        
        ServerLogFilter serverLogFilter = new ServerLogFilter();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter source file path.(folder)");
        //String source_file = sc.nextLine();
        String source_file = "logFilter\\Logs\\unfiltred_logs\\log1.log";

        System.out.println("Enter target file path.(folder)");
        //String target_file= sc.nextLine();
        String target_file = "F:\\workspace\\Java\\logFilter\\Logs\\filtred_logs";

        System.out.println("""
            Choose Which log level for filtration.
            1. CRITICAL, 
            2. ERROR,
            3. WARNING,
            4. INFO,
            5. DEBUG;
                """);
        int choice = sc.nextInt();
        LogLevel level = null;
        
        if(choice > 0 && choice <= LogLevel.values().length){
            level = LogLevel.values()[choice-1];
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String logFileName= "logs_" + LocalDateTime.now().format(dtf) + ".log";
        File logFile = new File(target_file, logFileName);

        try {
            logFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Something wrong with created file." + e.getMessage());
        }
        serverLogFilter.filter(source_file, logFile.getAbsolutePath(), level);
        System.out.println("Logs save to target file.");

        sc.close();
    }
}

//https://metanit.com/java/tutorial/6.11.php
