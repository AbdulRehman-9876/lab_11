package lab11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DatabaseHandler {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/lab11";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	private Connection connection;

	public DatabaseHandler() {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void storeVerse(int poemId, String verseText) {
		try {
			String insertVerseQuery = "INSERT INTO Verses (PoemID, VerseText) VALUES (?, ?)";
			try (PreparedStatement verseStatement = connection.prepareStatement(insertVerseQuery,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				verseStatement.setInt(1, poemId);
				verseStatement.setString(2, verseText);

				int verseId;
				if (verseStatement.executeUpdate() > 0) {
					try (var generatedKeys = verseStatement.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							verseId = generatedKeys.getInt(1);
						} else {
							throw new SQLException("Failed to retrieve VerseID from the generated keys.");
						}
					}

					List<String> tokens = tokenizeText(verseText);
					String insertTokenQuery = "INSERT INTO Tokens (VerseID, TokenText) VALUES (?, ?)";
					try (PreparedStatement tokenStatement = connection.prepareStatement(insertTokenQuery)) {
						for (String token : tokens) {
							tokenStatement.setInt(1, verseId);
							tokenStatement.setString(2, token);
							tokenStatement.executeUpdate();
						}
					}
				} else {
					throw new SQLException("Failed to insert a new verse.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int insertPoem(String title, String author) {
		try {
			String insertPoemQuery = "INSERT INTO Poems (Title, Author) VALUES (?, ?)";
			try (PreparedStatement poemStatement = connection.prepareStatement(insertPoemQuery,
					PreparedStatement.RETURN_GENERATED_KEYS)) {
				poemStatement.setString(1, title);
				poemStatement.setString(2, author);

				int poemId;
				if (poemStatement.executeUpdate() > 0) {
					try (var generatedKeys = poemStatement.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							poemId = generatedKeys.getInt(1);
							return poemId;
						} else {
							throw new SQLException("Failed to retrieve PoemID from the generated keys.");
						}
					}
				} else {
					throw new SQLException("Failed to insert a new poem.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	private List<String> tokenizeText(String text) {
		return Arrays.asList(text.split("\\s+"));
	}
}
