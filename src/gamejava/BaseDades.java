/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejava;

import static gamejava.GameJava.scores;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author molin
 */
public class BaseDades {
    private  Connection conn;
    
    public BaseDades() throws SQLException{
        String url = "jdbc:mysql://localhost/scores";
        String usuari = "root";
        String contrasenya = "mysql";
        conn = DriverManager.getConnection(url,usuari,contrasenya);
    }
    
    public void close() throws SQLException{
        conn.close();
    }
    
    public void insertar() throws SQLException {
        
        int size = scores.size();
        GameScores score = scores.get(size-1);
        String query = "insert into SCORES values(?, ?, ?, ?, NOW(),?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setInt(1, score.getCoins());
        st.setInt(2, score.getKills());
        st.setInt(3, score.getDifficulty());
        st.setInt(4, score.getEndgameHP());
        st.setInt(5, score.getPoints());
        st.execute();
    }
    
    public void writeArray() throws SQLException{
        String query="SELECT*FROM Scores ORDER BY DATE_PLAY,POINTS desc;";
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            int coins = rs.getInt(1);
            int kills = rs.getInt(2);
            int dificulty = rs.getInt(3);
            int EndgameHP = rs.getInt(4);
            String date = rs.getString(5);
            int points = rs.getInt(6);
            
            GameJava.scores.add(new GameScores(coins,kills,dificulty,EndgameHP,points,date));
        }
    }
}
