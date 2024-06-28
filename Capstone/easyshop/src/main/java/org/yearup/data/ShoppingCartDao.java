package org.yearup.data;


import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{

    // add additional method signatures here
    //~GetMapping
    ShoppingCart getByUserId(int userId);
    //~PostMapping
    ShoppingCart addItemToCart(int id, int userId);
    //~PutMapping
    ShoppingCart updateItemQuantity(int userId, int productId, ShoppingCartItem item);
    //~DeleteMapping
    void deleteShoppingCart(int userId);

}
