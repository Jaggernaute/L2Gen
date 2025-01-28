package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;

public class Ligne implements IForme {

    private List<Point> sommets;

    public Ligne(double ... points) {
        sommets = new ArrayList<Point>();
        for (int i = 0; i < points.length-1; i+=2) {
            sommets.add(new Point(points[i], points[i+1]));
        }
    }

    /**
     * @return 
     */
    @Override
    public Point centre() {
        return null;
    }

    /**
     * @param x 
     * @param y
     */
    @Override
    public void deplacer(double x, double y) {

    }

    /**
     * @param indentation 
     * @return
     */
    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(indentation);

        sb.append(indent).append("Ligne ");
        for (Point p : sommets) {
            sb.append(p.x()).append(",").append(p.y()).append(" ");
        }

        sb.append("\n");
        return sb.toString();
    }

    /**
     * @return 
     */
    @Override
    public String enSVG() {
        return "";
    }

    /**
     * @return 
     */
    @Override
    public IForme dupliquer() {
        return null;
    }

    /**
     * @return 
     */
    @Override
    public double hauteur() {
        return 0;
    }

    /**
     * @return 
     */
    @Override
    public double largeur() {
        return 0;
    }

    /**
     * @param largeur 
     * @param hauteur
     */
    @Override
    public void redimensionner(double largeur, double hauteur) {

    }

    /**
     * @param base 
     * @param profondeur
     * @return
     */
    @Override
    public IForme fractale(IForme base, int profondeur) {
        return null;
    }
}
