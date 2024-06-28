package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        // get all categories
        String query = "{call getAllCategories()}";

        List<Category> allCategories = new ArrayList<>();

        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)){
            ResultSet row = cs.executeQuery();

            while (row.next()){
                Category category = mapRow(row);
                allCategories.add(category);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return allCategories;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        String query = "{CALL GetCategoryById(?)}";

        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {

            cs.setInt(1,categoryId);
            ResultSet row = cs.executeQuery();

            if(row.next()){
                return mapRow(row);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        String query = "{CALL CreateCategory(?,?)}";

        try(Connection conn = this.getConnection()) {
            CallableStatement cs = conn.prepareCall(query); // Use CallableStatement
            cs.setString(1, category.getName());
            cs.setString(2, category.getDescription());

            cs.execute(); // Ensure it executes

            try (ResultSet rs = cs.getGeneratedKeys()) {
                if (rs.next()) {
                    category.setCategoryId(rs.getInt(1)); // Retrieve and set generated ID
                }
            }

            return category; // Return the category object
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category update(int categoryId, Category category)
    {
        // update category
        String query = "{CALL UpdateCategory(?,?,?)}";

        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)){

            cs.setInt(1,categoryId);
            cs.setString(2,category.getName());
            cs.setString(3, category.getDescription());

            ResultSet row = cs.executeQuery();

            if (row.next()){
                return mapRow(row);
            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String query = "{CALL DeleteCategory(?)}";

        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {
            cs.setInt(1,categoryId);

            cs.executeQuery();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
