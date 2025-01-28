package fr.univrennes.istic.l2gen.geometrie;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Groupe implements IForme {
    private List<IForme> formes;

    /**
     * Constructeur d'un groupe de formes.
     *
     * @param formes autant de formes que necessaires [0;any]
     */
    public Groupe(IForme ... formes) {
        this.formes = new ArrayList<>(Arrays.asList(formes));
    }

    /**
     * Ajoute une IForme au groupe, et retourne ce meme groupe une foi l'operation terminee
     * Pourquoi return le groupe s'il est modiffie ?
     *
     * @param iForme forme ou groupe a ajouter au groupe
     * @return
     */
    public Groupe ajouter(IForme iForme) {
        formes.add(iForme);
        return this;
    }

    /**
     * Calcule le centre géométrique du Groupe, défini comme le point
     * à équidistance des deux points les plus éloignés parmi les formes
     * contenues dans le Groupe.
     *
     * @return Un objet {@link Point} représentant le centre géométrique
     *         du Groupe.
     * @throws IllegalStateException Si le Groupe ne contient aucune forme.
     *
     * @implNote La méthode parcourt toutes les formes du Groupe pour extraire
     *           leurs centres. Elle effectue ensuite une comparaison
     *           de tout les points. Complexité de l'algorithme : O(n^2).
     */
    @Override
    public Point centre() {
        List<Point> points = new ArrayList<>();
        for (IForme forme : formes) {
            points.add(forme.centre());
        }

        if (points.isEmpty()) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }

        double maxDistance = 0;
        Point p1 = null;
        Point p2 = null;

        // Trouver les deux points les plus éloignés
        for (Point a : points) {
            for (Point b : points) {
                double distance = Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2));
                if (distance > maxDistance) {
                    maxDistance = distance;
                    p1 = a;
                    p2 = b;
                }
            }
        }

        if (p1 == null) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }

        double centerX = (p1.x() + p2.x()) / 2;
        double centerY = (p1.y() + p2.y()) / 2;

        return new Point(centerX, centerY);
    }

    /**
     * @param x
     * @param y
     */
    @Override
    public void deplacer(double x, double y) {
        this.formes.forEach(forme -> forme.deplacer(x, y));
    }

    /**
     * Génère une description textuelle de chaques formes du groupe avec indentation
     * spécifiée.
     *
     * @param indentation Le niveau d'indentation (chaque niveau ajoute 2 espaces).
     * @return Une chaîne de caractères représentant chaques formes du groupe.
     */
    @Override
    public String description(int indentation) {
        StringBuilder sb = new StringBuilder();
        String indent = "  ".repeat(indentation);

        sb.append(indent).append("Groupe\n");

        for (IForme forme : formes) {
            sb.append(forme.description(indentation + 1));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * @return 
     */
    @Override
    public String enSVG() {
        StringBuilder sb = new StringBuilder();
        sb.append("<g>\n");
        for (IForme forme : formes) {
            sb.append(forme.enSVG());
            sb.append("\n");
        }
        sb.append("</g>\n");
        return sb.toString();
    }

    /**
     * Crée une copie indépendante (deep copy) du Groupe, incluant tous les éléments
     * qu'il contient.
     *
     * @return Une nouvelle instance de {@link Groupe} contenant des copies
     * indépendantes de chaques sous elements du Groupe actuel.
     */
    @Override
    public IForme dupliquer() {
        List<IForme> formesCopy = new ArrayList<>();
        for (IForme forme : formes) {
            formesCopy.add(forme.dupliquer());
        }
        return new Groupe(formesCopy.toArray(new IForme[0]));
    }

    /**
     * Calcule la hauteur du Groupe, la distance maximale en y
     * entre les centres des éléments qu'il contient et en prenant en compte leur hauteur.
     *
     * @return Un double représentant la hauteur (distance maximale en y).
     * @throws IllegalStateException Si le Groupe ne contient aucune forme.
     */
    @Override
    public double hauteur() {
        if (formes.isEmpty()) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }

        // initialiser min et max a des valeurs impossibles pour comparaison apres.
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (IForme forme : formes) {
            Point centre = forme.centre();
            double y = centre.y();

            if (y < minY) {
                minY = y - forme.hauteur();
            }
            if (y > maxY) {
                maxY = y + forme.hauteur();
            }
        }

        return maxY - minY;
    }

    /**
     * Calcule la largeur du Groupe, définie comme la distance maximale en x
     * entre les centres des éléments qu'il contient et en prenant en compte leur largeur.
     *
     * @return Un double représentant la largeur (distance maximale en x).
     * @throws IllegalStateException Si le Groupe ne contient aucune forme.
     */
    @Override
    public double largeur() {
        if (formes.isEmpty()) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }

        // initialiser min et max a des valeurs impossibles pour comparaison apres.
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;

        for (IForme forme : formes) {
            Point centre = forme.centre();
            double x = centre.x();

            if (x < minX) {
                minX = x - forme.largeur();
            }
            if (x > maxX) {
                maxX = x + forme.largeur();
            }
        }

        return maxX - minX;
    }


    /**
     * Redimensionne toutes les formes d'un facteur different pour la hauteur et la largeur.
     * Ne modifie pas la position des formes.
     *
     * @param largeur Facteur de redimensionement de la largeur
     * @param hauteur   Facteur de redimensionement de la hauteur
     */
    @Override
    public void redimensionner(double largeur, double hauteur) {
        if (formes.isEmpty()) {
            throw new IllegalStateException("Le Groupe ne contient aucune forme.");
        }
        for (IForme forme : formes) {
            forme.redimensionner(largeur, hauteur);
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
