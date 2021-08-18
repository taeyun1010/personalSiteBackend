package com.personal.site.repository;

import com.personal.site.dal.CartDal;
import com.personal.site.domain.Cart;
import com.personal.site.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins="*")
@Repository
public class CartRepository implements CartDal {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CartRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Cart save(Cart cart) {
        mongoTemplate.save(cart);
        return cart;
    }

    @Override
    public Cart findByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        Cart result = mongoTemplate.findOne(query, Cart.class);
        return result;
    }

    @Override
    public void deleteByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        mongoTemplate.remove(query, Cart.class);
    }

    @Override
    public void addToCart(String username, Product product) {
        Update update = new Update();
        update.addToSet("products", product);
        Criteria criteria = Criteria.where("username").is(username);
        mongoTemplate.updateFirst(Query.query(criteria), update, "cart");
    }
}
