package gamejava;

import static gamejava.GameJava.scores;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameScores {

    public static final String FILE_PATH = "scores.bin";

    final int EASY_COIN_POINTS = 100;
    final int EASY_KILL_POINTS = 100;
    final int EASY_COIN_BONUS = 25;
    final int EASY_ENDGAME_HP_BONUS[] = {25, 50, 100};

    final int MEDIUM_COIN_POINTS = 150;
    final int MEDIUM_KILL_POINTS = 150;
    final int MEDIUM_COIN_BONUS = 50;
    final int MEDIUM_ENDGAME_HP_BONUS[] = {50, 100, 150};

    final int HARD_COIN_POINTS = 200;
    final int HARD_KILL_POINTS = 200;
    final int HARD_COIN_BONUS = 100;
    final int HARD_ENDGAME_HP_BONUS[] = {100, 200, 300};

    byte hour, minuts, day, month,
            coins, kills, difficulty, endgameHP;
    short year, points;

    //constructor
    public GameScores(int coins, int kills, int difficulty, int endgameHP, LocalDateTime time) {
        this.coins = (byte) coins;
        this.kills = (byte) kills;
        this.difficulty = (byte) difficulty;
        this.endgameHP = (byte) endgameHP;
        this.points = calculateScorePoints(coins, kills, difficulty, (int) endgameHP);

        DateTimeFormatter hourF = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter minutsF = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter dayF = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter monthF = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter yearF = DateTimeFormatter.ofPattern("yyyy");

        this.hour = Byte.parseByte(hourF.format(time));
        this.minuts = Byte.parseByte(minutsF.format(time));
        this.day = Byte.parseByte(dayF.format(time));
        this.month = Byte.parseByte(monthF.format(time));
        this.year = Short.parseShort(yearF.format(time));
    }

    public GameScores(byte hour, byte minuts, byte day, byte month, short year, byte coins, byte kills, byte difficulty, byte endgameHP) {
        this.hour = hour;
        this.minuts = minuts;
        this.day = day;
        this.month = month;
        this.year = year;

        this.coins = coins;
        this.kills = kills;
        this.difficulty = difficulty;
        this.endgameHP = endgameHP;
        this.points = calculateScorePoints(coins, kills, difficulty, endgameHP);
    }

    //getters & setters
    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
    }

    public byte getMinuts() {
        return minuts;
    }

    public void setMinuts(byte minuts) {
        this.minuts = minuts;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    public byte getCoins() {
        return coins;
    }

    public void setCoins(byte coins) {
        this.coins = coins;
    }

    public byte getKills() {
        return kills;
    }

    public void setKills(byte kills) {
        this.kills = kills;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(byte difficulty) {
        this.difficulty = difficulty;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public byte getEndgameHP() {
        return endgameHP;
    }

    public void setEndgameHP(byte endgameHP) {
        this.endgameHP = endgameHP;
    }

    public short getPoints() {
        return points;
    }

    public void setPoints(short scorePoints) {
        this.points = scorePoints;
    }

    //metodos
    public String getFormatedDate() {
        return (hour + ":" + String.format("%02d", minuts) + " " + String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year);
    }

    public String toString() {
        return ("DateTime: " + hour + ":" + String.format("%02d", minuts) + " " + String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year) + "\nCoins: " + coins + "\nKills: " + kills + "\nDifficulty: " + difficulty + "\nEndgame HP: " + endgameHP + "\nScore: " + points;
    }

    public static int size() {
        return 10;
    }

    public short calculateScorePoints(int coins, int kills, int difficulty, int endgameHP) {
        short totalPoints = 0;
        int coinBonusTimes;

        switch (difficulty) {
            case 1: //EASY
                //sumamos los puntos de cada moneda al total
                totalPoints += coins * EASY_COIN_POINTS;

                //calculamos cuantos bonus por cada 5 monedas tenemos y lo sumamos al total
                coinBonusTimes = coins / 5;
                totalPoints += coinBonusTimes * EASY_COIN_BONUS;

                //sumamos los puntos por cada kill al total
                totalPoints += kills * EASY_KILL_POINTS;

                //sumamos el bonus dependiendo la vida restante del jugador al acabar partida
                if (endgameHP >= 75) {
                    totalPoints += EASY_ENDGAME_HP_BONUS[2];
                } else if (endgameHP >= 50) {
                    totalPoints += EASY_ENDGAME_HP_BONUS[1];
                } else if (endgameHP >= 25) {
                    totalPoints += EASY_ENDGAME_HP_BONUS[0];
                }
                break;
            case 2: //MEDIUM
                //sumamos los puntos de cada moneda al total
                totalPoints += coins * MEDIUM_COIN_POINTS;

                //calculamos cuantos bonus por cada 5 monedas tenemos y lo sumamos al total
                coinBonusTimes = coins / 5;
                totalPoints += coinBonusTimes * MEDIUM_COIN_BONUS;

                //sumamos los puntos por cada kill al total
                totalPoints += kills * MEDIUM_KILL_POINTS;

                //sumamos el bonus dependiendo la vida restante del jugador al acabar partida
                if (endgameHP >= 75) {
                    totalPoints += MEDIUM_ENDGAME_HP_BONUS[2];
                } else if (endgameHP >= 50) {
                    totalPoints += MEDIUM_ENDGAME_HP_BONUS[1];
                } else if (endgameHP >= 25) {
                    totalPoints += MEDIUM_ENDGAME_HP_BONUS[0];
                }
                break;
            case 3: //HARD
                //sumamos los puntos de cada moneda al total
                totalPoints += coins * HARD_COIN_POINTS;

                //calculamos cuantos bonus por cada 5 monedas tenemos y lo sumamos al total
                coinBonusTimes = coins / 5;
                totalPoints += coinBonusTimes * HARD_COIN_BONUS;

                //sumamos los puntos por cada kill al total
                totalPoints += kills * HARD_KILL_POINTS;

                //sumamos el bonus dependiendo la vida restante del jugador al acabar partida
                if (endgameHP >= 75) {
                    totalPoints += HARD_ENDGAME_HP_BONUS[2];
                } else if (endgameHP >= 50) {
                    totalPoints += HARD_ENDGAME_HP_BONUS[1];
                } else if (endgameHP >= 25) {
                    totalPoints += HARD_ENDGAME_HP_BONUS[0];
                }
                break;
        }
        return totalPoints;
    }

    //metodos lectura/escritura archivo binario
    public static void writeFile(ArrayList<GameScores> scores) {
        RandomAccessFile randomAccess;
        int i, pos;
        GameScores score;

        try {
            randomAccess = new RandomAccessFile(FILE_PATH, "rw");

            for (i = 0; i < scores.size(); i++) {
                score = scores.get(i);
                pos = GameScores.size() * i;
                randomAccess.seek(pos);

                randomAccess.writeByte(score.getHour());
                randomAccess.writeByte(score.getMinuts());
                randomAccess.writeByte(score.getDay());
                randomAccess.writeByte(score.getMonth());
                randomAccess.writeShort(score.getYear());
                randomAccess.writeByte(score.getCoins());
                randomAccess.writeByte(score.getKills());
                randomAccess.writeByte(score.getDifficulty());
                randomAccess.writeByte(score.getEndgameHP());
            }

            randomAccess.close();
        } catch (IOException e) {
            new Exception("No se ha podido acceder al archivo" + FILE_PATH
                    + "\n" + e.toString());
        }

    }

    public static void writeScore(GameScores score, int position) {
        RandomAccessFile randomAccess;
        int pos;

        try {
            randomAccess = new RandomAccessFile(FILE_PATH, "rw");

            pos = GameScores.size() * position;
            randomAccess.seek(pos);

            randomAccess.writeByte(score.getHour());
            randomAccess.writeByte(score.getMinuts());
            randomAccess.writeByte(score.getDay());
            randomAccess.writeByte(score.getMonth());
            randomAccess.writeShort(score.getYear());

            randomAccess.writeByte(score.getCoins());
            randomAccess.writeByte(score.getKills());
            randomAccess.writeByte(score.getDifficulty());
            randomAccess.writeByte(score.getEndgameHP());

            randomAccess.close();
        } catch (IOException e) {
            new Exception("No se ha podido acceder al archivo" + FILE_PATH
                    + "\n" + e.toString());
        }

    }

    public static ArrayList readFile() {
        ArrayList<GameScores> scores = new ArrayList<>();
        File file;
        RandomAccessFile randomAccess;
        GameScores score;
        long fileSize;
        int numScores, i, pos;

        byte hour, minuts, day, month,
                coins, kills, difficulty, endgameHP;
        short year;

        try {
            file = new File(FILE_PATH);
            if (file.exists()) {

                fileSize = file.length();
                numScores = (int) fileSize / GameScores.size();

                randomAccess = new RandomAccessFile(file, "r");
                for (i = 0; i < numScores; i++) {

                    pos = GameScores.size() * i;
                    randomAccess.seek(pos);

                    hour = randomAccess.readByte();
                    minuts = randomAccess.readByte();
                    day = randomAccess.readByte();
                    month = randomAccess.readByte();
                    year = randomAccess.readShort();

                    coins = randomAccess.readByte();
                    kills = randomAccess.readByte();
                    difficulty = randomAccess.readByte();
                    endgameHP = randomAccess.readByte();

                    score = new GameScores(hour, minuts, day, month, year, coins, kills, difficulty, endgameHP);

                    scores.add(score);
                }
            } else {
                new Exception("No se ha podido abrir al archivo");
            }
        } catch (FileNotFoundException e) {
            new Exception("No se ha podido abrir al archivo" + FILE_PATH
                    + "\n" + e.toString());
        } catch (IOException e) {
            new Exception("No se ha podido acceder al archivo" + FILE_PATH
                    + "\n" + e.toString());
        }

        return scores;
    }

    public static ArrayList readScore(int position) {
        ArrayList<GameScores> scores = new ArrayList<>();
        File file;
        RandomAccessFile randomAccess;
        GameScores score;
        int pos;

        byte hour, minuts, day, month,
                coins, kills, difficulty, endgameHP;
        short year;

        try {
            file = new File(FILE_PATH);
            if (file.exists()) {
                randomAccess = new RandomAccessFile(file, "r");

                pos = GameScores.size() * position;
                randomAccess.seek(pos);

                hour = randomAccess.readByte();
                minuts = randomAccess.readByte();
                day = randomAccess.readByte();
                month = randomAccess.readByte();
                year = randomAccess.readShort();

                coins = randomAccess.readByte();
                kills = randomAccess.readByte();
                difficulty = randomAccess.readByte();
                endgameHP = randomAccess.readByte();

                score = new GameScores(hour, minuts, day, month, year, coins, kills, difficulty, endgameHP);

                scores.add(score);
            } else {
                new Exception("No se ha podido abrir al archivo");
            }
        } catch (FileNotFoundException e) {
            new Exception("No se ha podido abrir al archivo" + FILE_PATH
                    + "\n" + e.toString());
        } catch (IOException e) {
            new Exception("No se ha podido acceder al archivo" + FILE_PATH
                    + "\n" + e.toString());
        }

        return scores;
    }

    public static void resetScores(ArrayList<GameScores> scores) {
        scores.clear();
        writeFile(scores);
    }

    public static void ordenarScores(ArrayList<GameScores> scores) {
        Collections.sort(scores, new Comparator<GameScores>() {
            @Override
            public int compare(GameScores score1, GameScores score2) {
                return Short.valueOf(score2.getPoints()).compareTo(score1.getPoints());
            }
        });
    }
;
}
