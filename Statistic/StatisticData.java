package Statistic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Settings.SettingsData;
import Game.Game;

public class StatisticData {

    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final LocalDateTime time = LocalDateTime.now();

    public static String winner = Game.winner();
    public static String date = time.format(format);
    public static int size = SettingsData.boardSize;
    public static String firstPlayer = SettingsData.firstPlayer;
    public static String secondPlayer = SettingsData.secondPlayer;
}
