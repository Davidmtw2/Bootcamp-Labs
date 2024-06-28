package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.models.Profile;
import org.yearup.data.ProfileDao;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao
{
    public MySqlProfileDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public Profile create(Profile profile)
    {
        String sql = "INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = getConnection())
        {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getFirstName());
            ps.setString(3, profile.getLastName());
            ps.setString(4, profile.getPhone());
            ps.setString(5, profile.getEmail());
            ps.setString(6, profile.getAddress());
            ps.setString(7, profile.getCity());
            ps.setString(8, profile.getState());
            ps.setString(9, profile.getZip());

            ps.executeUpdate();

            return profile;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Profile getByUserId(int id) {
        String query = "{CALL GetProfile(?)}";

        Profile profile = new Profile();
        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {

            cs.setInt(1,id);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                int userId = rs.getInt("user_id");
                String userFirstName = rs.getString("first_name");
                String userLastName = rs.getString("last_name");
                String userPhone = rs.getString("phone");
                String userEmail = rs.getString("email");
                String userAddress = rs.getString("address");
                String userCity = rs.getString("city");
                String userState = rs.getString("state");
                String userZip = rs.getString("zip");

                profile = new Profile(userId,userFirstName,userLastName,userPhone,userEmail, userAddress,userCity,userState,userZip);
            }
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return profile;
    }

    @Override
    public Profile updateProfile(Profile profile, int id) {
        String query = "{CALL UpdateProfile(?,?,?,?,?,?,?,?,?)}";

        Profile updatedProfile = new Profile();
        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {

            cs.setInt(1, id);
            cs.setString(2, profile.getFirstName());
            cs.setString(3, profile.getLastName());
            cs.setString(4, profile.getPhone());
            cs.setString(5, profile.getEmail());
            cs.setString(6, profile.getAddress());
            cs.setString(7, profile.getCity());
            cs.setString(8, profile.getState());
            cs.setString(9, profile.getZip());

            cs.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return getByUserId(id);
    }
}

