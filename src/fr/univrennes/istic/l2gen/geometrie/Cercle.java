package fr.univrennes.istic.l2gen.geometrie;

public class Cercle implements IForme {

    private int centerX;
    private int centerY;
    private int radius;
    
    public void setCenterX (int xValue) {
        this.centerX = xValue;
    }

    public void setCenterY (int yValue) {
        this.centerY = yValue;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    public String description (int indentation) {
        String indent = "  ";
        return indent.repeat(indentation) + "Cercle centre=" + getCenterX() + "," + getCenterY() + " r=" + getRadius();
    }
}
