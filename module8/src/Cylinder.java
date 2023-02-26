public class Cylinder implements ProjectableShapes{
    private final String name = "Cylinder";
    public boolean temporaryAnswer;

    public String getName() {
        return name;
    }

    Cylinder (boolean temporaryAnswer) {
        this.temporaryAnswer = temporaryAnswer;
    }

    @Override
    public FigurePattern project() {
        if (temporaryAnswer) {
            System.out.println("As a result of the projection of the " + getName() + ", you received a Circle");
            return new Circle();
        } else {
            System.out.println("As a result of the projection of the " + getName() + ", you received an undefined figure");
            return new UndefinedFigure();
        }
    }
}