package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;

@Component
public class MySqlShoppingCart extends MySqlDaoBase implements ShoppingCartDao {

    public MySqlShoppingCart(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ShoppingCart getByUserId(int id) {
        String query = "{CALL GetShoppingCartByUserId(?)}";

        ShoppingCart shoppingCart = new ShoppingCart();
        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {

            cs.setInt(1,id);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                int productId = rs.getInt("product_id");
                int productQuantity = rs.getInt("quantity");
                String productName = rs.getString("name");
                BigDecimal productPrice = rs.getBigDecimal("price");
                int productCategory = rs.getInt("category_id");
                String productDes = rs.getString("description");
                String productColor = rs.getString("color");
                String imageUrl = rs.getString("image_url");
                int productStock = rs.getInt("stock");
                boolean isFeatured = rs.getBoolean("featured");

                //Creating a product
                Product product = new Product(productId,productName, productPrice, productCategory, productDes, productColor, productStock, isFeatured,imageUrl);
                //Adding it as an item
                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                shoppingCartItem.setProduct(product);
                shoppingCartItem.setQuantity(productQuantity);
                //Adding the items into the shopping cart for display
                shoppingCart.add(shoppingCartItem);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart addItemToCart(int userId, int id) {
        String query = "{CALL AddProductsToCart(?,?,?)}";

        var shoppingCart = getByUserId(userId);
        if (shoppingCart.contains(id)) {
            //Added 1
            shoppingCart.get(id).setQuantity(shoppingCart.get(id).getQuantity() + 1);
            return updateItemQuantity(userId, id, (shoppingCart.get(id)));
        }
        else {
            try (Connection conn = this.getConnection();
                 CallableStatement cs = conn.prepareCall(query)) {

                cs.setInt(1,userId);
                cs.setInt(2,id);
                cs.setInt(3, 1);

                cs.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return getByUserId(userId);
        }
    }

    @Override
    public ShoppingCart updateItemQuantity(int userId, int productId, ShoppingCartItem item){
        String query = "{CALL UpdateQuantity(?,?,?)}";

        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {

            cs.setInt(1, userId);
            cs.setInt(2, productId);
            cs.setInt(3, item.getQuantity());

            cs.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return getByUserId(userId);
    }

    @Override
    public void deleteShoppingCart(int userId) {
        String query = "{CALL DeleteProductsInCart(?)}";

        try(Connection conn = this.getConnection();
            CallableStatement cs = conn.prepareCall(query)) {

            cs.setInt(1,userId);

            cs.executeUpdate();
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }

}