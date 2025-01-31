package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Ligne implements IForme {
    private final List<Point> sommets;

    /**
     * Construit une Ligne à partir des coordonnées x et y de ses n sommets.
     * Avec n >= 2.
     *
     * @param points Tableau conntenant les coordonnées x/y des sommets.
     */
    public Ligne(double ... points) {
        if (points.length <= 2) {
            throw new IllegalStateException("Une ligne doit avoir au moins deux sommets.");
        }

        sommets = new ArrayList<>();
        for (int i = 0; i < points.length-1; i+=2) {
            sommets.add(new Point(points[i], points[i+1]));
        }
    }

    /**
     * Ajoute un sommet a la ligne par un {@link Point}
     *
     * @param p point a ajouter a la ligne
     */
    public void ajouterSommet(Point p){
        sommets.add(p);
    }

    /**
     * Ajoute un sommet a la ligne en creant un {@link Point} grace a ses coordonnees x et y.
     *
     * @param x Déplacement à appliquer sur l'axe X.
     * @param y Déplacement à appliquer sur l'axe Y.
     */
    public void ajouterSommetD(double x, double y){
        sommets.add(new Point(x,y));
    }

    /**
     * Recuperes une liste des points de la ligne.
     *
     * @return Une liste de tous les points qui composent la ligne
     */
    public List<Point> getsommets(){
        return sommets;
    }


    /**
     * Calcule le centre géométrique (barycentre) de la Ligne.
     * </br>
     * Le centre est obtenu en faisant la moyenne des coordonnées x et y
     * de tous les points de la ligne.
     *
     * @return Un objet {@link Point} représentant le centre de la ligne.
     * @throws IllegalStateException Si la ligne ne contient pas au moins 2 points.
     */
    public Point centre(){
        if (sommets.size() <= 2) {
            throw new IllegalStateException("Une ligne doit avoir au moins deux sommets.");
        }

        int moyx = 0;
        int moyy = 0;
        for (Point point : sommets) {
            moyx += (int)point.x();
            moyy += (int)point.y();
        }
        moyx = moyx/ sommets.size();
        moyy = moyy/ sommets.size();
        return new Point(moyx, moyy);
    }


    /**
     * Déplace la ligne en appliquant un décalage aux coordonnées de ses points.
     * </br>
     * Cette méthode ajoute les valeurs de déplacement spécifiées aux coordonnées x et y
     * de chaque sommet de la ligne, permettant ainsi de le translater sans modifier sa forme.
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
     * Génère une description textuelle de la Ligne avec une indentation spécifiée.
     * </br>
     * La description contient le mot "Ligne" suivi des coordonnées entières
     * de ses points.
     *
     * @param indentation Niveau d'indentation à appliquer en nombre d'espaces
     *                    (chaque niveau correspond à deux espaces).
     * @return Une chaîne de caractères représentant la ligne.
     */
    @Override
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
     * Génère une représentation SVG de la Ligne.
     * </br>
     * La méthode construit une balise {@code <polyline>} contenant les coordonnées des points
     * de la ligne, permettant ainsi de l'afficher dans un document SVG.
     *
     * @return Une chaîne de caractères représentant la ligne sous forme de balise SVG.
     *         Exemple de sortie :
     *         {@code <polyline points="x1,y1 x2,y2 x3,y3 ...,..." fill="white" stroke="black" />}
     */
    @Override
    public String enSVG() {
        StringBuilder s = new StringBuilder();
        for (Point point : sommets) {
            s.append(point.x()).append(",").append(point.y()).append(" ");
        }
        return String.format( "<polyline points = \" %s \"\n fill = \" white \" stroke = \" black \"  />", s);
    }

    /**
     * Crée une copie indépendante (deep copy) de la ligne.
     * </br>
     * Cette méthode duplique la ligne en créant de nouveaux objets {@link Point}
     * pour chaque point pour ne pas referencer les objets composant la ligne d'origine.
     *
     * @return Une nouvelle instance de {@link Ligne}.
     */
    @Override
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


    /**
     * Calcule la hauteur de la ligne, la distance maximale en y
     * entre les points les plus éloignés.
     *
     * @return Un double représentant la hauteur (distance maximale en y).
     * @throws IllegalStateException Si le la ligne ne contient pas au moins 2 points.
     */
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

    /**
     * Calcule la largeur de la ligne, la distance maximale en x
     * entre les points les plus éloignés.
     *
     * @return Un double représentant la largeur (distance maximale en x).
     * @throws IllegalStateException Si le la ligne ne contient pas au moins 2 points.
     */
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


    /**
     * Redimensionne la ligne en ajustant les points par rapport à son centre.
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
