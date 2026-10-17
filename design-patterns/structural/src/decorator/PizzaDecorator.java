package decorator;

public abstract class PizzaDecorator implements Pizza{
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza){
        this.pizza =pizza;
    }

    @Override
    public String bake() {
        return "Pizza";
    }
}
