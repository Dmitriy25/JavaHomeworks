public class Tetrahedron implements ProjectableShapes {
    private final String name = "Tetrahedron";
    public boolean temporaryAnswer;

    public String getName() {
        return name;
    }

    Tetrahedron (boolean temporaryAnswer) {
        this.temporaryAnswer = temporaryAnswer;
    }

    @Override
    public FigurePattern project() {
        if (temporaryAnswer) {
            System.out.println("As a result of the projection of the " + getName() + ", you received a Triangle");
            return new Triangle();
        } else {
            System.out.println("As a result of the projection of the " + getName() + ", you received an undefined figure");
            return new UndefinedFigure();
        }
    }
}