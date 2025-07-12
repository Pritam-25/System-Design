package Projects.JomatoClone.factories;

import java.util.List;

import Projects.JomatoClone.Strategies.PaymentStrategy;
import Projects.JomatoClone.models.Cart;
import Projects.JomatoClone.models.MenuItem;
import Projects.JomatoClone.models.Order;
import Projects.JomatoClone.models.Restaurant;
import Projects.JomatoClone.models.User;

public interface OrderFactory {
    Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItem, PaymentStrategy paymentStrategy, double totalCost, String orderType);
}
