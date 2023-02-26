public abstract class FigurePattern implements FlatFiguresProperties {
    private String name;
    public int corners;
    public String colour;

    public abstract String getName();

    @Override
    public void sayName() {
        System.out.println(getName());
    }

    @Override
    public void scale() { // make the figure bigger or smaller on the screen
        System.out.println(getName() + " got bigger/smaller");
    }

    @Override
    public void spin() { //  spin the figure on the screen
        System.out.println(getName() + " rotated");
    }

}
