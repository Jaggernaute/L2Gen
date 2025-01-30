package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;

public class Triangle implements IForme {
    private final List<Point> sommets = new ArrayList<>();

    /**
     * Construit un triangle à partir des coordonnées x et y de ses trois sommets.
     *
     * @param sommet1x Coordonnée x du premier sommet.
     * @param sommet1y Coordonnée y du premier sommet.
     * @param sommet2x Coordonnée x du deuxième sommet.
     * @param sommet2y Coordonnée y du deuxième sommet.
     * @param sommet3x Coordonnée x du troisième sommet.
     * @param sommet3y Coordonnée y du troisième sommet.
     */
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

    /**
     * Construit un triangle à partir de trois objets {@link Point} représentant ses sommets.
     *
     * @param point1 Premier sommet du triangle.
     * @param point2 Deuxième sommet du triangle.
     * @param point3 Troisième sommet du triangle.
     */
    public Triangle(Point point1, Point point2, Point point3) {
        this.sommets.add(point1);
        this.sommets.add(point2);
        this.sommets.add(point3);
    }


    /**
     * Calcule le centre géométrique (barycentre) du triangle.
     * </br>
     * Le centre est obtenu en faisant la moyenne des coordonnées x et y
     * des trois sommets du triangle.
     *
     * @return Un objet {@link Point} représentant le centre du triangle.
     * @throws IllegalStateException Si le triangle ne contient pas exactement trois sommets.
     */
    @Override
    public Point centre() {
        if (sommets.size() != 3) {
            throw new IllegalStateException("Un triangle doit avoir exactement trois sommets.");
        }

        double sumx = 0;
        double sumy = 0;

        for (Point point : sommets) {
            sumx += point.x();
            sumy += point.y();
        }

        return new Point(sumx / 3, sumy / 3);
    }


    /**
     * Déplace le triangle en appliquant un décalage aux coordonnées de ses sommets.
     * </br>
     * Cette méthode ajoute les valeurs de déplacement spécifiées aux coordonnées x et y
     * de chaque sommet du triangle, permettant ainsi de le translater sans modifier sa forme.
     *
     * @param x Déplacement à appliquer sur l'axe X.
     * @param y Déplacement à appliquer sur l'axe Y.
     */
    @Override
    public void deplacer(double x, double y) {
        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);
            sommets.set(i, new Point(p.x() + x, p.y() + y));
        }
    }

    /**
     * Génère une description textuelle du triangle avec une indentation spécifiée.
     * </br>
     * La description contient le mot "Triangle" suivi des coordonnées entières
     * de ses trois sommets.
     *
     * @param indentation Niveau d'indentation à appliquer en nombre d'espaces
     *                    (chaque niveau correspond à deux espaces).
     * @return Une chaîne de caractères représentant le triangle.
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
     * Génère une représentation SVG du triangle.
     * </br>
     * La méthode construit une balise `<polygon>` contenant les coordonnées des sommets
     * du triangle, permettant ainsi de l'afficher dans un document SVG.
     *
     * @return Une chaîne de caractères représentant le triangle sous forme de balise SVG.
     *         Exemple de sortie :
     *         {@code <polygon points="x1,y1 x2,y2 x3,y3" fill="white" stroke="black" />}
     */
    @Override
    public String enSVG() {
        StringBuilder s = new StringBuilder();
        for (Point point : sommets) {
            s.append(point.x()).append(",").append(point.y()).append(" ");
        }
        return String.format( "<polygon points = \" %s \"\n fill = \" white \" stroke = \" black \"  />", s);
    }

    /**
     * Crée une copie indépendante (deep copy) du triangle.
     * </br>
     * Cette méthode duplique le triangle en créant de nouveaux objets {@link Point}
     * pour chaque sommet, pour ne pas referencer les objets composant le triangle d'origine.
     *
     * @return Une nouvelle instance de {@link Triangle}.
     */
    @Override
    public IForme dupliquer() {
        List<Point> pointsCopy = new ArrayList<>();
        for (Point point : sommets) {
            pointsCopy.add(new Point(point.x(), point.y()));
        }
        return new Triangle(pointsCopy.get(0), pointsCopy.get(1), pointsCopy.get(2));
    }

    /**
     * Calcule la hauteur du Triangle, la distance maximale en y
     * entre les sommets les plus éloignés.
     *
     * @return Un double représentant la hauteur (distance maximale en y).
     * @throws IllegalStateException Si le triangle ne contient pas exactement trois sommets.
     */
    @Override
    public double hauteur() {
        if (sommets.size() != 3) {
            throw new IllegalStateException("Un triangle doit avoir exactement trois sommets.");
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
     * Calcule la largeur du Triangle, la distance maximale en x
     * entre les sommets les plus éloignés.
     *
     * @return Un double représentant la largeur (distance maximale en x).
     * @throws IllegalStateException Si le triangle ne contient pas exactement trois sommets.
     */
    @Override
    public double largeur() {
        if (sommets.size() != 3) {
            throw new IllegalStateException("Un triangle doit avoir exactement trois sommets.");
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
     * Redimensionne le triangle en ajustant les sommets par rapport à son centre.
     *
     * @param largeur  Facteur d'échelle sur l'axe X (horizontal).
     * @param hauteur  Facteur d'échelle sur l'axe Y (vertical).
     */
    @Override
    public void redimensionner(double largeur, double hauteur) {
        Point centre = centre();

        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);

            // Appliquer l'échelle autour du centre
            double newX = centre.x() + (p.x() - centre.x()) * largeur;
            double newY = centre.y() + (p.y() - centre.y()) * hauteur;

            sommets.set(i, new Point(newX, newY));
        }
    }


    // TODO: fractale
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
