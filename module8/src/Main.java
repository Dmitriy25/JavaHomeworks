public class Main {
    public static void main(String[] args) {
        Shape shape = new Shape();

        FigurePattern circle = new Circle();
        FigurePattern quad = new Quad();
        FigurePattern triangle = new Triangle();

        ProjectableShapes tetrahedron = new Tetrahedron(true);
        ProjectableShapes cylinder = new Cylinder(true);
        ProjectableShapes parallelepiped = new Parallelepiped(false);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        shape.printNameOnTheScreen(circle);
        shape.showResultOfScaling(circle);
        shape.showResultOfRotating(circle);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        shape.printNameOnTheScreen(quad);
        shape.showResultOfScaling(quad);
        shape.showResultOfRotating(quad);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        shape.printNameOnTheScreen(triangle);
        shape.showResultOfScaling(triangle);
        shape.showResultOfRotating(triangle);

        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

        shape.projectVolumetricFigureToFlat(tetrahedron);
        shape.projectVolumetricFigureToFlat(cylinder);
        shape.projectVolumetricFigureToFlat(parallelepiped);

    }
}