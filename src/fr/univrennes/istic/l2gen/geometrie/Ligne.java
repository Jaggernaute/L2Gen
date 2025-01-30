package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Ligne implements IForme {
    private final List<Point> sommets;

    public Ligne(double ... points) {
        if (points.length <= 2) {
            throw new IllegalStateException("Une ligne doit avoir au moins deux sommets.");
        }

        sommets = new ArrayList<>();
        for (int i = 0; i < points.length-1; i+=2) {
            sommets.add(new Point(points[i], points[i+1]));
        }
    }

    @Override
    public double hauteur() {
        if (sommets.size() <= 2) {
            throw new IllegalStateException("Une ligne doit avoir au moins deux sommets.");
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

    @Override
    public double largeur() {
        if (sommets.size() <= 2) {
            throw new IllegalStateException("Une ligne doit avoir au moins deux sommets.");
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

    public void ajouterSommet(Point p){
        sommets.add(p);
    }
    public void ajouterSommetD(double x, double y){
        sommets.add(new Point(x,y));
    }
    public List<Point> getsommets(){
        return sommets;
    }

    public Point centre(){
        int moyx = 0;
        int moyy = 0;
        for (Point point : sommets) {
            moyx += (int)point.x();
            moyy += (int)point.y();
        }
        moyx = moyx/ sommets.size();
        moyy = moyy/ sommets.size();
        Point centre = new Point(moyx, moyy);
        return centre;
    }

    public String description(int indentation){
        StringBuilder des = new StringBuilder();
        String indent = "  ".repeat(indentation);
        des.append(indent).append("Ligne ");
        for (Point p : sommets) {
            des.append((int)p.x()).append(",").append((int)p.y()).append(" ");
        }
        des.append("\n");
        return des.toString();
    }

    /**
     * @return 
     */
    @Override
    public String enSVG() {
        StringBuilder s = new StringBuilder();
        for (Point point : sommets) {
            s.append(point.x()).append(",").append(point.y()).append(" ");
        }
        return String.format( "<polygon points = \" %s \"\n fill = \" white \" stroke = \" black \"  />", s);
    }

    public void deplacer(double x, double y) {
        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);
            sommets.set(i, new Point(p.x() + x, p.y() + y));
        }
    }

    public IForme dupliquer(){
        List<Point> pointsCopy = new ArrayList<>();
        for (Point point : sommets) {
            pointsCopy.add(new Point(point.x(), point.y()));
        }
        return new Ligne(
                pointsCopy
                        .stream()
                        .filter(Objects::nonNull)
                        .flatMap(
                                p -> Stream.of(p.x(), p.y())
                                )
                        .mapToDouble(p -> p)
                        .toArray()
        );
    }


    @Override
    public void redimensionner(double largeur, double hauteur) {
        if (sommets.size() <= 2) {
            throw new IllegalStateException("Une ligne doit avoir au moins deux sommets.");
        }
        Point centre = centre();

        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);

            // Appliquer l'échelle autour du centre
            double newX = centre.x() + (p.x() - centre.x()) * largeur;
            double newY = centre.y() + (p.y() - centre.y()) * hauteur;

            sommets.set(i, new Point(newX, newY));
        }
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
