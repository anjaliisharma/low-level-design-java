package decorator;

public class ExtraCheeseDecorator extends PizzaDecorator{

    public ExtraCheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public String bake() {
        return pizza.bake() + addCheese();
    }

    public String addCheese(){
        return " with extra Cheese";
    }
}
