package com.teestore.backend.dao;

import com.teestore.backend.entity.CartEntity;
import com.teestore.backend.entity.UserEntity;
import com.teestore.backend.model.Cart;
import com.teestore.backend.model.Product;
import com.teestore.backend.model.User;
import com.teestore.backend.service.CartService;
import com.teestore.backend.service.ProductService;
import com.teestore.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository(value = "cartDAO")
public class CartDAOImpl implements CartDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

//    @Autowired
//    private OrdersService ordersService;

    @Autowired
    private CartService cartService;

    @Override
    public String addCart(Cart cart) throws Exception {
        String id = null;

//        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
//            StringBuilder products = new StringBuilder();
//            for (Product p:cart.getProducts()) {
//                products.append(p.getProductId()).append(",");
//            }
//            StringBuilder qty = new StringBuilder();
//            for (Integer i:cart.getQuantities()) {
//                qty.append(i).append(",");
//            }

        UserEntity user = entityManager.find(UserEntity.class, cart.getUser().getUserId());

        if (user != null) {
            CartEntity entity = new CartEntity();
//                entity.setProductIds(products.toString());
//                entity.setQuantities(qty.toString());
//                entity.setTotalCost(cart.getTotalCost());
            entity.setUser(user);

            entityManager.persist(entity);

            id = entity.getCartId();
//            }
        }

        return id;
    }

    @Override
    public Integer addProductToCart(String userId, String productId, String size) throws Exception {
        Integer i = null;

        Query query = entityManager.createQuery("select c from CartEntity c where c.user.userId=:userId");
        query.setParameter("userId", userId);

        List<CartEntity> entities = query.getResultList();

        if (entities == null || entities.isEmpty()) {

            Cart c = new Cart();
            User u = new User();

            u.setUserId(userId);
            c.setUser(u);
            String cartId = cartService.addCart(c);

            CartEntity entity = entityManager.find(CartEntity.class, cartId);

            entity.setProductIds(productId);
            entity.setSizes(size);
            entity.setQuantities("1");

            entityManager.persist(entity);
        } else {
            CartEntity entity = entities.get(0);

            entity.setProductIds(entity.getProductIds() + "," + productId);
            entity.setSizes(entity.getSizes() + "," + size);
            entity.setQuantities(entity.getQuantities() + ",1");

            entityManager.persist(entity);
        }
        i = 1;

        return i;
    }

    @Override
    public Integer removeProductFromCart(String userId, String productId, String size) throws Exception {
        Integer n = null;

        Query query = entityManager.createQuery("select c from CartEntity c where c.user.userId=:userId");
        query.setParameter("userId", userId);

        List<CartEntity> entities = query.getResultList();

        if (entities == null || entities.isEmpty()) {

            Cart c = new Cart();
            User u = new User();

            u.setUserId(userId);
            c.setUser(u);
            String cartId = cartService.addCart(c);

            CartEntity entity = entityManager.find(CartEntity.class, cartId);

            entity.setProductIds(productId);
            entity.setSizes(size);
            entity.setQuantities("1");

            entityManager.persist(entity);
        } else {
            CartEntity entity = entities.get(0);

            ArrayList<String> products = new ArrayList<>(Arrays.asList(entity.getProductIds().split(",")));
            ArrayList<String> sizes = new ArrayList<>(Arrays.asList(entity.getSizes().split(",")));
            ArrayList<String> qtys = new ArrayList<>(Arrays.asList(entity.getQuantities().split(",")));

            int r = -1;

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).equals(productId) && sizes.get(i).equals(size)) {
                    r = i;
                }
            }
            products.remove(r);
            sizes.remove(r);
            qtys.remove(r);

            StringBuilder a = new StringBuilder();
            StringBuilder b = new StringBuilder();
            StringBuilder c = new StringBuilder();

            for (int i = 0; i < products.size(); i++) {
                a.append(products.get(i)).append(",");
                b.append(sizes.get(i)).append(",");
                c.append(qtys.get(i)).append(",");
            }

            entity.setProductIds(a.toString().substring(0, a.length() - 1));
            entity.setSizes(b.toString().substring(0, b.length() - 1));
            entity.setQuantities(c.toString().substring(0, c.length() - 1));

            entityManager.persist(entity);
            n = 1;
        }

        return n;
    }

    @Override
    public String editCart(String cartId, Cart cart) throws Exception {
        String id = null;

        CartEntity entity = entityManager.find(CartEntity.class, cartId);

        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
            StringBuilder products = new StringBuilder();
            for (Product p : cart.getProducts()) {
                products.append(p.getProductId()).append(",");
            }
            StringBuilder qty = new StringBuilder();
            for (Integer i : cart.getQuantities()) {
                qty.append(i).append(",");
            }
            StringBuilder sizes = new StringBuilder();
            for (String i : cart.getSizes()) {
                sizes.append(i).append(",");
            }

            UserEntity user = entityManager.find(UserEntity.class, cart.getUser().getUserId());

            if (user != null) {
                entity.setProductIds(products.toString().substring(0, products.length() - 1));
                entity.setQuantities(qty.toString().substring(0, qty.length() - 1));
                entity.setSizes(sizes.toString().substring(0, sizes.length() - 1));
                entity.setTotalCost(cart.getTotalCost());
                entity.setUser(user);

                entityManager.persist(entity);

                id = entity.getCartId();
            }
        }
        return id;
    }

    @Override
    public Cart clearCart(String cartId) throws Exception {
        Cart id = null;

        CartEntity entity = entityManager.find(CartEntity.class, cartId);

        if (entity != null) {
            entity.setSizes("");
            entity.setProductIds("");
            entity.setQuantities("");
            entity.setTotalCost(0.0);

            entityManager.persist(entity);
            id = getCart(entity.getUser().getUserId());
        }

        return id;
    }

    @Override
    public Cart getCart(String userId) throws Exception {
        Query query = entityManager.createQuery("select c from CartEntity c where c.user.userId=:userId");
        query.setParameter("userId", userId);

        List<CartEntity> entities = query.getResultList();

        Cart cart = null;
        List<Product> products = null;
        List<Integer> qty = null;
        List<String> size = null;
        if (entities != null && !entities.isEmpty()) {
            User user = userService.getUser(entities.get(0).getUser().getUserId());
            if (user != null) {
                cart = new Cart();
                cart.setTotalCost(entities.get(0).getTotalCost());
                cart.setUser(user);
                String productIds = entities.get(0).getProductIds();
                String[] ids = productIds.split(",");
                String[] qs = entities.get(0).getQuantities().split(",");
                String[] sizes = entities.get(0).getSizes().split(",");
                if (ids.length > 0) {
                    products = new ArrayList<>();
                    qty = new ArrayList<>();
                    size = new ArrayList<>();
                    for (String id : ids) {
                        if (id.equals(""))
                            continue;
                        Product p = productService.getProductById(id);
                        products.add(p);
                    }
                    for (String q : qs) {
                        if (q.equals(""))
                            continue;
                        qty.add(Integer.parseInt(q));
                    }
                    for (String q : sizes) {
                        if (q.equals(""))
                            continue;
                        size.add(q);
                    }
                }
                cart.setQuantities(qty);
                cart.setProducts(products);
                cart.setSizes(size);
                cart.setCartId(entities.get(0).getCartId());
            }
        }
        return cart;
    }

    @Override
    public String buyNow(String cartId) throws Exception {
//        Cart cart = getCart(cartId);
//        return ordersService.buyNow(cart);
        return null;
    }
}
