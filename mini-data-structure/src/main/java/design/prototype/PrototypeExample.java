/*
 * All Rights Reserved.
 *
 */
package design.prototype;


import design.prototype.impl.MySqlDataSourceConnection;

/**
 * PrototypeExample.
 *
 * @author Carl, 2024-01-04 15:42
 */
public class PrototypeExample {

    public static void main(String[] args) {
        MySqlDataSourceConnection originalConnection = new MySqlDataSourceConnection("jdbc:mysql://localhost:3306/mydb",
                "user", "password");

        try {
            // 复制原始连接对象来创建新的连接对象
            MySqlDataSourceConnection cloneConnection = (MySqlDataSourceConnection) originalConnection.clone();
            cloneConnection.connect();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
