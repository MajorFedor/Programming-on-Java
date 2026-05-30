package logFilter.ServeLogFilter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import logFilter.LogFilter.logFilter;
import logFilter.LogLevel.LogLevel;

public class ServerLogFilter implements logFilter{
    public void filter(String source_file, String target_file, LogLevel level){
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(source_file)));
            PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(target_file)))) {
            String str;
            while(sc.hasNextLine()){
                str = sc.nextLine();
                if(str.contains(level.name())){
                    wr.println(str);
                }

            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        } 
    }
}