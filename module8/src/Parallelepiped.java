public class Parallelepiped implements ProjectableShapes{
    private final String name = "Parallelepiped";
    public boolean temporaryAnswer;

    public String getName() {
        return name;
    }

    Parallelepiped (boolean temporaryAnswer) {
        this.temporaryAnswer = temporaryAnswer;
    }

    @Override
    public FigurePattern project() {
        if (temporaryAnswer) {
            System.out.println("As a result of the projection of the " + getName() + ", you received a Quad");
            return new Quad();
        } else {
            System.out.println("As a result of the projection of the " + getName() + ", you received an undefined figure");
            return new UndefinedFigure();
        }
    }
}