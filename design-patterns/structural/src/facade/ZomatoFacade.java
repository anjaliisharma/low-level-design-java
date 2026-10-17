package facade;

public class ZomatoFacade {
    Restaurant restaurant;
    DeliveryBoy deliveryBoy;
    DeliveryTeam deliveryTeam;

    public void deliverOrder(){
        restaurant.prepareOrder();
        deliveryTeam.assignDeliveryBoy();
        restaurant.dispatchOrder();
        deliveryBoy.pickupOrder();
        deliveryBoy.deliverOrder();
    }
}
