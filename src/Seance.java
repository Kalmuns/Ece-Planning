import java.sql.Date;
import java.util.ArrayList;

/**
 * class seance
 * @author Kalmuns
 *
 */

public class Seance {
	// A discuter a 3
	ArrayList<Groupe> groupe = new ArrayList<Groupe>() ;
	Cour cour=null;
	TypeCour typeCour=null;
	ArrayList<Enseignant> enseignant= new ArrayList<Enseignant>();
	ArrayList<Salle> salle= new ArrayList<Salle>();
	private int idseance,semaine,crenau,duree,etat,heure_debut;  /// Heure début /fin ? pas sur 
	Date date = null;
	
	
	public Seance(ArrayList<Groupe> groupes,ArrayList<Enseignant> enseignants,ArrayList<Salle> salles, int id, Date dates, int hd, int duree, int etats, int semaine, Cour cour,TypeCour typeCour )
	{
		groupe=groupes;
		enseignant=enseignants;
		this.salle=salles;
		idseance=id;
		this.semaine=semaine;
		date=dates;
		etat=etats;
		heure_debut=hd;
		this.duree=duree;
		this.cour=cour;
		this.typeCour=typeCour;	
	}
	public ArrayList<Groupe> getgroupes()
	{
		return groupe;
	}
//	public String getnomseance()
//	{
//		return nomseance;
//	}
	public Cour getCour()
	{
		return cour;
	}
	public TypeCour gettypeCour()
	{
		return typeCour;
	}
	public ArrayList<Enseignant> getEnseignants()
	{
		return enseignant;
	}
	public ArrayList<Salle> getsalle()
	{
		return salle;
	}
	public int getidseance()
	{
		return idseance;
	}
	public int getsemaine()
	{
		return semaine;
	}
	public Date getdate()
	{
		return date;
	}
	public int getetat()
	{
		return etat;
	}
	public int getheure_debut()
	{
		return heure_debut;
	}
	public int getduree()
	{
		return duree;
	}
}
