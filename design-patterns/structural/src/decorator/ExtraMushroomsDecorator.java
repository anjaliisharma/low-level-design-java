package decorator;

public class ExtraMushroomsDecorator extends PizzaDecorator{

    public ExtraMushroomsDecorator(Pizza pizza) {
        super(pizza);
    }

    public String bake(){
        return pizza.bake()+addExtraMushrooms();
    }

    public String addExtraMushrooms(){
        return " with Extra Mushrooms";
    }

}
