package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un polygone défini par une liste de sommets.
 * Un polygone peut avoir un nombre quelconque de sommets (au moins 3).
 */
public class Polygone implements IForme {
    private final List<Point> sommets = new ArrayList<>();

    /**
     * Construit un polygone à partir des coordonnées x et y de ses sommets.
     *
     * @param coords Liste des coordonnées des sommets (x1, y1, x2, y2, ..., xn, yn).
     * @throws IllegalArgumentException Si le nombre de coordonnées est inférieur à 6 (moins de 3 sommets).
     */
    public Polygone(double... coords) {
        if (coords.length < 6 || coords.length % 2 != 0) {
            throw new IllegalArgumentException("Un polygone doit avoir au moins 3 sommets (6 coordonnées).");
        }
        for (int i = 0; i < coords.length; i += 2) {
            sommets.add(new Point(coords[i], coords[i + 1]));
        }
    }

    /**
     * Construit un polygone à partir d'une liste de points.
     *
     * @param points Liste des sommets du polygone.
     * @throws IllegalArgumentException Si le polygone contient moins de 3 sommets.
     */
    public Polygone(List<Point> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("Un polygone doit avoir au moins 3 sommets.");
        }
        this.sommets.addAll(points);
    }

    /**
     * Calcule le centre géométrique (barycentre) du polygone.
     *
     * @return Un objet {@link Point} représentant le centre du polygone.
     */
    @Override
    public Point centre() {
        double sumX = 0, sumY = 0;
        for (Point p : sommets) {
            sumX += p.x();
            sumY += p.y();
        }
        return new Point(sumX / sommets.size(), sumY / sommets.size());
    }

    /**
     * Déplace le polygone en appliquant un décalage aux coordonnées de ses sommets.
     *
     * @param x Déplacement en X.
     * @param y Déplacement en Y.
     */
    @Override
    public void deplacer(double x, double y) {
        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);
            sommets.set(i, new Point(p.x() + x, p.y() + y));
        }
    }

    /**
     * Génère une description textuelle du polygone avec une indentation spécifiée.
     *
     * @param indentation Niveau d'indentation (chaque niveau = 2 espaces).
     * @return Une chaîne de caractères représentant le polygone.
     */
    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(indentation);
        sb.append(indent).append("Polygone ");

        for (Point p : sommets) {
            sb.append((int) p.x()).append(",").append((int) p.y()).append(" ");
        }

        return sb.toString().trim();
    }

    /**
     * Génère une représentation SVG du polygone.
     *
     * @return Une chaîne de caractères représentant le polygone sous forme SVG.
     */
    @Override
    public String enSVG() {
        StringBuilder s = new StringBuilder();
        for (Point point : sommets) {
            s.append(point.x()).append(",").append(point.y()).append(" ");
        }
        return String.format("<polygon points=\"%s\" fill=\"white\" stroke=\"black\" />", s.toString().trim());
    }

    /**
     * Crée une copie indépendante (deep copy) du polygone.
     *
     * @return Une nouvelle instance de {@link Polygone}.
     */
    @Override
    public IForme dupliquer() {
        List<Point> pointsCopy = new ArrayList<>();
        for (Point point : sommets) {
            pointsCopy.add(new Point(point.x(), point.y()));
        }
        return new Polygone(pointsCopy);
    }

    /**
     * Calcule la hauteur du polygone (distance maximale en y entre les sommets).
     *
     * @return Un double représentant la hauteur du polygone.
     */
    @Override
    public double hauteur() {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Point point : sommets) {
            double y = point.y();
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        return maxY - minY;
    }

    /**
     * Calcule la largeur du polygone (distance maximale en x entre les sommets).
     *
     * @return Un double représentant la largeur du polygone.
     */
    @Override
    public double largeur() {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        for (Point point : sommets) {
            double x = point.x();
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
        }
        return maxX - minX;
    }

    /**
     * Redimensionne le polygone en ajustant ses sommets par rapport à son centre.
     *
     * @param largeur  Facteur d'échelle sur l'axe X.
     * @param hauteur  Facteur d'échelle sur l'axe Y.
     */
    @Override
    public void redimensionner(double largeur, double hauteur) {
        Point centre = centre();
        for (int i = 0; i < sommets.size(); i++) {
            Point p = sommets.get(i);
            double newX = centre.x() + (p.x() - centre.x()) * largeur;
            double newY = centre.y() + (p.y() - centre.y()) * hauteur;
            sommets.set(i, new Point(newX, newY));
        }
    }

    /**
     * Génère une fractale à partir du polygone en le dupliquant récursivement.
     *
     * @param base       Forme de base.
     * @param profondeur Niveau de récursivité.
     * @return Une instance de {@link IForme} représentant la fractale.
     */
    @Override
    public IForme fractale(IForme base, int profondeur) {
        if (profondeur <= 0) {
            return base;
        }
        Groupe groupe = new Groupe(base);
        for (int i = 0; i < 4; i++) {
            IForme copie = base.dupliquer();
            copie.redimensionner(0.5, 0.5);
            copie.deplacer((i % 2) * largeur() / 2, (i / 2) * hauteur() / 2);
            groupe.ajouter(copie);
        }
        return fractale(groupe, profondeur - 1);
    }
}
