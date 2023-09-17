package Java1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Productinfo {
    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // Oracle 데이터베이스 URL
        String username = "project1"; // 데이터베이스 사용자 이름
        String password = "project1"; // 데이터베이스 비밀번호

        System.out.print("제품번호: ");
        String productNumber = sc.nextLine(); // 사용자가 입력한 제품 번호
        System.out.print("제품이름: ");
        String productName = sc.nextLine(); // 사용자가 입력한 제품 이름
        System.out.print("수량: ");
        int quantity = sc.nextInt(); // 사용자가 입력한 수량

        String insertSql = "INSERT INTO productInfo (productNumber,productName,quantity) VALUES (?,?,?)";

        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // PreparedStatement 생성
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, productNumber);
            preparedStatement.setString(2, productName);
            preparedStatement.setInt(3, quantity);


            // SQL 문 실행
            int rowsAffected = preparedStatement.executeUpdate();

            // 결과 확인
            if (rowsAffected > 0) {
                System.out.println("데이터베이스에 데이터가 성공적으로 삽입되었습니다.");
            } else {
                System.out.println("데이터베이스에 데이터를 삽입하는 데 문제가 발생했습니다.");
            }

            // 리소스 해제
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
