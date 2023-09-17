package Java1;

import java.sql.*;
import java.util.Scanner;

public class ProductMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean validCorrect = false;
        String role = null;

        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // Oracle 데이터베이스 URL
        String username = "project1"; // 데이터베이스 사용자 이름
        String password = "project1"; // 데이터베이스 비밀번호

        while(!validCorrect) {

            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("PASSWORD: ");
            String pw = sc.nextLine();

            try {
                // JDBC 드라이버 로드
                Class.forName("oracle.jdbc.driver.OracleDriver");

                // 데이터베이스 연결
                Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

                // 사용자 입력한 ID와 비밀번호를 사용하여 데이터베이스에서 사용자 이름 조회
                String selectSql = "SELECT username FROM users WHERE userid = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, pw);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String userName = resultSet.getString("username");
                    role = resultSet.getString("username");
                    System.out.println("사용자 이름: " + userName);
                    validCorrect = true;
                } else {
                    System.out.println("일치하는 사용자가 없습니다.");
                }

                // 리소스 해제
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        if ("탁".equals(role) || "조".equals(role) || "김".equals(role)){
            ManagerMenu managerMenu = new ManagerMenu();
            managerMenu.display();
        }
    }
}
