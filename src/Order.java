/**
 * Order.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */

import java.util.Comparator;

public class Order {

    private Customer customer;
    private int shippingSpeed; //TODO: ask her about how we calculate priority
    //TODO: Also is it required to associate some shipping speeds with a cost, or is that extra?
    //and how do we do this? prob need to pass in a total variable somewhere. 
    //stand shipping would be free if the user cart/order's total is >= $35.00.
    //look at lab 1 block.class
    private long date;
    private List<VideoGame> orderContents;
    private boolean shippingStatus;
    private long priority;

    public Order(Customer customer, int date, List<VideoGame> orderContents, int shippingSpeed, boolean shippingStatus) {
        this.customer = customer;
        this.orderContents = orderContents;
        this.shippingSpeed = shippingSpeed;
        this.priority = (System.currentTimeMillis() - (shippingSpeed * 86400000));
        this.shippingStatus = shippingStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public List<VideoGame> getOrderContents() {
        return orderContents;
    }

    public void setOrderContents(List<VideoGame> orderContents) {
        this.orderContents = orderContents;
    }

    public int getShippingSpeed() {
        return shippingSpeed;
    }

    public void setShippingSpeed(int shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(boolean shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    @Override public String toString() {
        return "" + getOrderContents();
        //TODO: why are there 2 extra /n before shipped orders?
        //"Before here: Here are your shipped orders: "
    }

}

//TODO: write single order here PW equivalent of calling toString (prof suggestion)

//Should also contain a Comparator class with a compare method to determine priority
class OrderComparator implements Comparator<Order> {
    public int compare(Order order1, Order order2) {
        if (order1.getPriority() < order2.getPriority()) {
            return -1;
        } else if (order1.getPriority() > order2.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}