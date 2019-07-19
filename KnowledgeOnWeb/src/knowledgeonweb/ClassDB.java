/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledgeonweb;

/**
 *
 * @author Daniel
 */
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class ClassDB {

    static String[] programs = {"Aeronautics-Astronautics", "Earth Sciences", "Applied Physics", "Biochemistry"
        + "", "Education", "Biosciences", "Humanities and Sciences", "Engineering and Applied Sciences"
        + "", "Architecture", "Law", "Nursing", "Journalism"};
    static Random random = new Random();

    private static int getRndNumber() {
        int randomNumber = 0;
        boolean loop = true;
        while (loop) {
            randomNumber = random.nextInt();
            if (Integer.toString(randomNumber).length() == 5 && !Integer.toString(randomNumber).startsWith("KoW")) {
                loop = false;
            }
        }
        return randomNumber;
    }
    static String code = "alms-" + getRndNumber();

    public static void main(String[] args) {
        
        System.out.println(code);
//        int length = programs.length;
//        int rand = (int) (Math.random() * length);
//
//        try {
//            Class.forName("org.sqlite.JDBC");
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:KnowledgeOnWebDB.db");
//            Statement stat = conn.createStatement();
//            stat.executeUpdate("DROP TABLE IF EXISTS StudentsData;");
//            stat.executeUpdate("CREATE TABLE StudentsData (schoolapplied, programappplied, studentname, city, state, zipcode,"
//                    + "country, rescc, resac, resphone, offcc, offac, offphone, email, gender, dob, citizenstatus, grade, university,"
//                    + "greScores, testdate, scorepercentage, toeflScores, honorsAwards);");
//            PreparedStatement prep = conn.prepareStatement("INSERT INTO StudentsData VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,"
//                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
//            prep.setString(1, "Central Michigan University");
//            prep.setString(2, "Applied Physics");
//            prep.setString(3, "Ilondu Daniel Mmachukwu");
//            prep.setString(4, "Ojo");
//            prep.setString(5, "Lagos");
//            prep.setString(6, "717212");
//            prep.setString(7, "Nigeria");
//            prep.setString(8, "673");
//            prep.setString(9, "3425");
//            prep.setString(10, "8546789057990");
//            prep.setString(11, "676");
//            prep.setString(12, "3455");
//            prep.setString(13, "85465343990");
//            prep.setString(14, "dan@gd.com");
//            prep.setString(15, "male");
//            prep.setString(16, "May-02-1997");
//            prep.setString(17, "Resident");
//            prep.setString(18, "89");
//            prep.setString(19, "UNILAG");
//            prep.setString(20, "30,20,10,20,20");
//            prep.setString(21, "March-02-1997");
//            prep.setString(22, "90%");
//            prep.setString(23, null);
//            prep.setString(24, null);
//            prep.addBatch();
//            conn.setAutoCommit(false);
//            prep.executeBatch();
//            conn.setAutoCommit(true);
//
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
