import java.util.ArrayList;
import java.util.List;

public class Sommet {

	public int id;
	public int x;
	public int y;

	public boolean rocherDejaVu = false;;
	
	private Case parcelle;

	private int valeur;

	public List<Sommet> voisins = new ArrayList<Sommet>();

	Sommet(){
		this.valeur = Integer.MAX_VALUE;
	}
	/**
	 * Cree un sommet qui encapsule une case pour en soutirer certaines informations
	 * @param parcelle Case dont on va soutirer les informations
	 * @see Case
	 */
	Sommet(Case parcelle, int id){
		this();
		this.id = id;
		this.parcelle = parcelle;
		this.x = parcelle.x;
		this.y = parcelle.y;
	}

	public Sommet getSommet(){
		return this;
	}

	public String toString(){
		return "Sommet n°"+id;
	}

	public void setValeur(int valeur){
		this.valeur = valeur;
	}

	public int getValeur(){
		return valeur;
	}

	public void afficheVoisins(){
		System.out.println("Les voisins de "+this+" sont");
		for(Sommet s : voisins){
			System.out.println(s);
		}
	}

	public void ajouterVoisin(Sommet s){
		voisins.add(s);
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean estTraversable(){
		return parcelle.estTraversable();
	}

	public boolean contientperso(){
		return parcelle.contientPersonnage;
	}

	public boolean estRocher(){
		if(parcelle instanceof Rocher){
			return true;
		}
		return false;
	}
	
	public boolean equals(Sommet s){
		if(this.x == s.x && this.y == s.y && this.valeur == s.valeur){
			return true;
		}
		return false;
	}

}


