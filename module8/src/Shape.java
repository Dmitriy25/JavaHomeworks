public class Shape {
    public void printNameOnTheScreen(FigurePattern fp) {
        fp.sayName();
    }
    public void showResultOfScaling(FigurePattern fp) {
        fp.scale();
    }
    public void showResultOfRotating(FigurePattern fp) {
        fp.spin();
    }
    public FigurePattern projectVolumetricFigureToFlat(ProjectableShapes ps) {
        return ps.project();
    }
}