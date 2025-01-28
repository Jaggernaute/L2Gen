package fr.univrennes.istic.l2gen.geometrie;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle implements IForme {
    private List<Point> sommets = new ArrayList<>();

    public Triangle(
            double sommet1x,
            double sommet1y,
            double sommet2x,
            double sommet2y,
            double sommet3x,
            double sommet3y
    ) {
        this.sommets.add(new Point(sommet1x, sommet1y));
        this.sommets.add(new Point(sommet2x, sommet2y));
        this.sommets.add(new Point(sommet3x, sommet3y));
    }

    public Triangle(Point point1, Point point2, Point point3) {
        this.sommets.add(point1);
        this.sommets.add(point2);
        this.sommets.add(point3);
    }


    /**
     * @return 
     */
    @Override
    public Point centre() {
        double sumx = 0;
        double sumy = 0;

        for (Point point : sommets) {
            sumx = point.x();
            sumy = point.y();
        }

        return new Point(sumx/3, sumy/3);
    }

    /**
     * @param x 
     * @param y
     */
    @Override
    public void deplacer(double x, double y) {
        this.sommets.forEach(point -> point.plus(x,y));
    }

    /**
     * @param indentation 
     * @return
     */
    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(indentation);

        sb.append(indent).append("Triangle ");

        for (Point p : sommets) {
            sb.append((int) p.x()).append(",").append((int) p.y()).append(" ");
        }

        return sb.toString();
    }

    /**
     * @return 
     */
    @Override
    public String enSVG() {
        String s = "";
        for (Point point : sommets) {
            s += point.x() + "," + point.y();
        }
        return String.format(
                "< polygon points = \" %s \"\n" +
                        "fill = \" white \" stroke = \" black \" / >",
                s

        );
    }

    /**
     * @return 
     */
    @Override
    public IForme dupliquer() {
        List<Point> pointsCopy = new ArrayList<>();
        for (Point point : sommets) {
            pointsCopy.add(new Point(point.x(), point.y()));
        }
        return new Groupe(pointsCopy.toArray(new IForme[0]));
    }

    /**
     * @return 
     */
    @Override
    public double hauteur() {
        if (sommets.isEmpty()) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }

        // initialiser min et max a des valeurs impossibles pour comparaison apres.
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Point point : sommets) {
            double y = point.y();
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }

        return maxY - minY;
    }

    /**
     * @return 
     */
    @Override
    public double largeur() {
        if (sommets.isEmpty()) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }

        // initialiser min et max a des valeurs impossibles pour comparaison apres.
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;

        for (Point point : sommets) {
            double x = point.x();
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
        }

        return maxX - minX;
    }

    /**
     * @param largeur 
     * @param hauteur
     */
    @Override
    public void redimensionner(double largeur, double hauteur) {
        System.out.println("NON !");
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
