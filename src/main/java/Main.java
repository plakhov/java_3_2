import java.sql.*;

public class Main {
    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection postgresConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/geekbrains", "cuba", "cuba")) {
            PreparedStatement studentPrepareStatement = postgresConnection.prepareStatement("select * from students where studname=? or groupname=?");
            String studentName = "Bob";
            String groupName = "6517";
            studentPrepareStatement.setString(1,studentName);
            studentPrepareStatement.setString(2,groupName);
            ResultSet bobResultSet = studentPrepareStatement.executeQuery();
            while (bobResultSet.next()) {
                System.out.print("Имя студента:"+bobResultSet.getString("studname"));
                System.out.print(" ");
                System.out.print("Средний балл:"+bobResultSet.getString("averagescore"));
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
