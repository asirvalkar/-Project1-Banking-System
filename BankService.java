import java.sql.*;
import java.util.Scanner;


public class BankService {
    Scanner scanner = new Scanner(System.in);
    /*public void createAccount(String username,String password){
        try(Connection con = DBHelper.getConnection()){
            String sql = "insert into users(username, password, balance) values(?, ?, 0)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            System.out.println("================Account Created Sucessdully==========");
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

    public void createAccount (String username,String password){
        try(Connection con = DBHelper.getConnection()){
            String sql = "INSERt INTO users (username, password,balance) values(?, ? ,0)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            System.out.println("=================Account Created Succesfully===============");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




    public int login(String username, String password) {
        try (Connection con = DBHelper.getConnection()) {
            String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void checkBalance(int userId) {
        try (Connection con = DBHelper.getConnection()) {
            String sql = "SELECT balance FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Balance: " + rs.getDouble("balance"));
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }

    public void deposit(int userId, double amount) {
        try (Connection con = DBHelper.getConnection()) {
            String sql = "UPDATE users SET balance = balance + ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, amount);
            ps.setInt(2, userId);
            ps.executeUpdate();
            String sql1 = "insert into transactions(user_id,type , amount) values(? ,? , ?)";
            PreparedStatement as = con.prepareStatement(sql1);
            as.setInt(1, userId);
            as.setString(2, "deposit"); 
            as.setDouble(3, amount);
            as.executeUpdate();

            System.out.println("==================Deposit successful!====================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void withdraw(int userId, double amount) {
        try (Connection con = DBHelper.getConnection()) {
            String Sql = "SELECT balance FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(Sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
             if (rs.next() && rs.getDouble("balance") >= amount) {
                String sql = "UPDATE users SET balance = balance - ? WHERE id = ?";
                ps = con.prepareStatement(sql);
                ps.setDouble(1, amount);
                ps.setInt(2, userId);
                ps.executeUpdate();
                PreparedStatement as = con.prepareStatement("INSERT INTO transactions (user_id, type, amount) VALUES (?, ?, ?)");
                as.setInt(1, userId);
                as.setString(2, "withdraw"); 
                as.setDouble(3, amount);
                as.executeUpdate();
                System.out.println("===============Withdrawal successfully=======================");
            } else {
                System.out.println("===============You Have Less Amount ========================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAccountDetails(int userId) {
        try (Connection con = DBHelper.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Account ID: " + rs.getInt("id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Balance: " + rs.getDouble("balance"));
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
}

