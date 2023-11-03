 package DAL;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PoemFileDAL {
    public void submitToDatabase(DefaultTableModel tableModel) {
        int poem_id_count = 0;
        int verse_id_count = 0;
        int misra_id_count = 0;

        String DB_URL = "jdbc:mysql://localhost:3306/poemdb";
        String DB_USER = "root";
        String DB_PASSWORD = "";

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String verse = tableModel.getValueAt(row, 0).toString();
            String title = tableModel.getValueAt(row, 1).toString();
            String misra1 = tableModel.getValueAt(row, 2).toString();
            String misra2 = tableModel.getValueAt(row, 3).toString();

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                if (connection != null) {
                    System.out.println("Connected to Poem Database");
                    try (Statement statement = connection.createStatement()) {
                        poem_id_count += 1;

                        if (verse.contains("1")) {
                        	String insertQuery_poem = "INSERT INTO poem (Poem_ID, Book_ID, Poem_Title) VALUES (" + poem_id_count + ", 1, '" + title + "')";
                            statement.executeUpdate(insertQuery_poem);
                            System.out.println("Inserted into Poem Database");
                        }

                        verse_id_count = verse_id_count + 1;

                        String insertQuery_verse = "INSERT INTO verse (Verse_ID, No_of_Verses, Poem_ID) VALUES (" + verse_id_count + ", '" + verse + "', " + poem_id_count + ")";
                        statement.executeUpdate(insertQuery_verse);
                        System.out.println("Inserted into Verse Database");

                        String insertQuery_misra = "INSERT INTO misra (misra_ID, verse_ID, Misra_1, Misra_2) VALUES (" + misra_id_count + ", " + verse_id_count + ", '" + misra1 + "', '" + misra2 + "')";
                        statement.executeUpdate(insertQuery_misra);
                        System.out.println("Inserted into Misra Database ");

                        connection.close();
                        statement.close();
                    } catch (SQLException e) {
                        System.out.println("Error " + e.getMessage());
                    }
                }
            } catch (SQLException e) {
                System.out.println("Connection Error " + e.getMessage());
            }
        }
    }
}
