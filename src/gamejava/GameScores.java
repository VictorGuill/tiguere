package gamejava;



public class GameScores {

    

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

    private int coins, kills, difficulty, endgameHP,points;
    private String Date;
 

    //constructor
    public GameScores(int coins, int kills, int difficulty, int endgameHP) {
        this.coins =  coins;
        this.kills = kills;
        this.difficulty = difficulty;
        this.endgameHP = endgameHP;
        this.points = calculateScorePoints(coins, kills, difficulty, endgameHP);
        
    }

    public GameScores(int coins, int kills, int difficulty, int endgameHP, int points, String Date) {
        this.coins = coins;
        this.kills = kills;
        this.difficulty = difficulty;
        this.endgameHP = endgameHP;
        this.points = points;
        this.Date = Date;
    }
    
    
    
  //getters & setters
    public int getCoins() {   
        return coins;
    }

    public int getKills() {
        return kills;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getEndgameHP() {
        return endgameHP;
    }

  
    public int getPoints() {    
        return points;
    }

    public String getDate() {
        return Date;
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

 

 


}
