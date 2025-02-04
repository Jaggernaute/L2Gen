package fr.univrennes.istic.l2gen.geometrie;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un rectangle défini par son centre, sa largeur et sa hauteur.
 */
public class Rectangle implements IForme {
    private Point centre;
    private double largeur;
    private double hauteur;

    /**
     * Construit un rectangle à partir du centre, de la largeur et de la hauteur.
     *
     * @param centreX Coordonnée x du centre du rectangle.
     * @param centreY Coordonnée y du centre du rectangle.
     * @param largeur Largeur du rectangle.
     * @param hauteur Hauteur du rectangle.
     */
    public Rectangle(double centreX, double centreY, double largeur, double hauteur) {
        this.centre = new Point(centreX, centreY);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    /**
     * Renvoie le centre du rectangle.
     *
     * @return Le point représentant le centre du rectangle.
     */
    @Override
    public Point centre() {
        return centre;
    }

    /**
     * Déplace le rectangle en appliquant un décalage à son centre.
     *
     * @param x Déplacement en X.
     * @param y Déplacement en Y.
     */
    @Override
    public void deplacer(double x, double y) {
        this.centre = new Point(centre.x() + x, centre.y() + y);
    }

    /**
     * Génère une description textuelle du rectangle avec une indentation spécifiée.
     *
     * @param indentation Niveau d'indentation (chaque niveau = 2 espaces).
     * @return Une chaîne de caractères représentant le rectangle.
     */
    @Override
    public String description(int indentation) {
        String indent = "  ".repeat(indentation);
        return String.format("%sRectangle Centre=%.1f,%.1f L=%.1f H=%.1f",
                indent, centre.x(), centre.y(), largeur, hauteur);
    }

    /**
     * Génère une représentation SVG du rectangle.
     *
     * @return Une chaîne de caractères représentant le rectangle sous forme SVG.
     */
    @Override
    public String enSVG() {
        double x = centre.x() - largeur / 2;
        double y = centre.y() - hauteur / 2;
        return String.format(
                "<rect x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\" fill=\"white\" stroke=\"black\" />",
                x, y, largeur, hauteur
        );
    }

    /**
     * Crée une copie indépendante (deep copy) du rectangle.
     *
     * @return Une nouvelle instance de {@link Rectangle}.
     */
    @Override
    public IForme dupliquer() {
        return new Rectangle(centre.x(), centre.y(), largeur, hauteur);
    }

    /**
     * Retourne la hauteur du rectangle.
     *
     * @return La hauteur du rectangle.
     */
    @Override
    public double hauteur() {
        return hauteur;
    }

    /**
     * Retourne la largeur du rectangle.
     *
     * @return La largeur du rectangle.
     */
    @Override
    public double largeur() {
        return largeur;
    }

    /**
     * Redimensionne le rectangle en ajustant sa largeur et sa hauteur.
     *
     * @param facteurLargeur  Facteur d'échelle sur l'axe X.
     * @param facteurHauteur  Facteur d'échelle sur l'axe Y.
     */
    @Override
    public void redimensionner(double facteurLargeur, double facteurHauteur) {
        this.largeur *= facteurLargeur;
        this.hauteur *= facteurHauteur;
    }

    /**
     * Génère une fractale à partir du rectangle en le dupliquant récursivement.
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
            copie.deplacer((i % 2) * largeur / 2, (i / 2) * hauteur / 2);
            groupe.ajouter(copie);
        }
        return fractale(groupe, profondeur - 1);
    }
}

