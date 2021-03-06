import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * Class regroupant les differentes fonction  de calcul
 * 
 * @author Kalmuns
 *
 */
public class Outil {
	//public int semaine;
	public  Outil()
	{
	}
	
	public int convertirSemaine(java.sql.Date dates)
	{
		//On utilise Calendar pour convertir le date sql en valeur facile a manipuler
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dates);
	    return cal.get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * retourne une date de string 
	 
	 */
	public String GetDate(int semaine, int jour) {
		Calendar cal = Calendar.getInstance();
		
		cal.setWeekDate(2020, semaine, jour);
		java.util.Date date = cal.getTime();
		String day = new SimpleDateFormat("dd").format(date); 
		String month = new SimpleDateFormat("MM").format(date);
		String resultatString = new String();
		resultatString = (day + "/" +month);
		return resultatString;
	}
	public String convertirJour(java.sql.Date dates)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dates);
		
	    switch(dates.getDate())
	    {
	    case 0: return "Lundi";
	    		
	    case 1: return "Mardi";
	    		
	    case 2: return "Mercredi";
	    		
	    case 3: return "Jeudi";
	    		
	    case 4: return "Vendredi";
	    		
	    case 5: return "Samedi";
	    		
	    case 6: return "Dimanche";
	    		
	    default: return "Invalid";
	    		
	    }
	}

	public ArrayList<Salle> salleDisponible(ArrayList<Salle> Allsalles, ArrayList<Seance> seanceParSemaine){
		ArrayList<Salle> salles = new ArrayList<Salle>();
		for (int i = 0; i < Allsalles.size(); i++) {
			for (int j = 0; j < seanceParSemaine.size(); j++) {
				salles=seanceParSemaine.get(j).getsalle();
				for (int j2 = 0; j2 < salles.size(); j2++) {
					if (Allsalles.get(i).getid()==salles.get(j).getid()) {
						Allsalles.remove(i);
						i--;
				}
				
				}
			}
		}
		return Allsalles;	
	}
	
	public int convertirJourInt(java.sql.Date dates) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dates);

		return cal.get(Calendar.DAY_OF_WEEK)-1;

		}
	    

	
	public ArrayList<Seance> searchSeancebyDateHoure(Date date,int heure)
	{
		
		DAO dao=new DAO();
		//System.out.println(" heure:"+heure+" date "+date.toString());
		ArrayList<Seance> seances= new ArrayList<Seance>();
		ArrayList<Seance> seanceperday= new ArrayList<Seance>();
		seanceperday=dao.getSeancesbyDate(date);
		
		//System.out.println(" nb seance dans le jour"+seanceperday.size());
		int i;
		for(i=0;i<seanceperday.size();i++)
		{
			if((seanceperday.get(i).getheure_debut()<=heure)&&(seanceperday.get(i).getheure_debut()+seanceperday.get(i).getduree()>=heure))
					{
						seances.add(seanceperday.get(i));
					}
		}
		
		return seances;
	}
	
	
	public ArrayList<Groupe> searchGroupebyDateHoure(Date date, int heure)
	{
		 DAO dao=new DAO();
		 ArrayList<Groupe> groupes= new  ArrayList<Groupe>();
		 ArrayList<Seance> seanceperday= new ArrayList<Seance>();
		 seanceperday=searchSeancebyDateHoure(date, heure);
		 int i,y;
		 for (i=0;i<seanceperday.size();i++)
		 {
			 for(y=0;y<seanceperday.get(i).getgroupes().size();y++)
			 {
			 groupes.add(seanceperday.get(i).getgroupes().get(y));
			 }
		}
		 return groupes;
	}
	
	public ArrayList<Salle> searchSallebyDateHoure(Date date, int heure)
	{
		 DAO dao=new DAO();
		 ArrayList<Salle> salles= new  ArrayList<Salle>();
		 ArrayList<Seance> seanceperday= new ArrayList<Seance>();
		 seanceperday=searchSeancebyDateHoure(date, heure);
		 int i,y;
		 for (i=0;i<seanceperday.size();i++)
		 {
			 for(y=0;y<seanceperday.get(i).getsalle().size();y++)
			 {
			 salles.add(seanceperday.get(i).getsalle().get(y));
			 }
		}
		 return salles;
	}
	
	public ArrayList<Salle> avalaibleSalle(Date date,int heure)
	{
		 Outil outil=new Outil();
		 DAO dao=new DAO();
		 ArrayList<Salle> salles= new  ArrayList<Salle>();
	
		 ArrayList<Salle> sallesoccupe= new  ArrayList<Salle>();
		 salles=dao.getallsalle();
		 sallesoccupe=outil.searchSallebyDateHoure(date, heure);
		 int i,y;
		 for(i=0;i<sallesoccupe.size();i++)
		 {
			 for(y=0;y<salles.size();y++)
			 {
				 if(sallesoccupe.get(i).getid()==salles.get(y).getid())
				 {
					 salles.remove(y);
					 y--;
				 }
			 }
		 }
		 return salles;
	}
	
	public ArrayList<Groupe> avalaibleGroupe(Date date,int heure)
	{
		 Outil outil=new Outil();
		 DAO dao=new DAO();
		 ArrayList<Groupe> groupe= new  ArrayList<Groupe>();	
		 ArrayList<Groupe> groupeoccupe= new  ArrayList<Groupe>();
		 groupe=dao.getallgroupe();
		 groupeoccupe=outil.searchGroupebyDateHoure(date, heure);
		 int i,y;
		 for(i=0;i<groupeoccupe.size();i++)
		 {
			 for(y=0;y<groupe.size();y++)
			 {
				 if(groupeoccupe.get(i).getid()==groupe.get(y).getid())
				 {
					 groupe.remove(y);
					 y--;
				 }
			 }
		 }
		 return groupe;
	}
	
	public ArrayList<Enseignant> avalaibleEnseignants(Date date,int heure)
	{
		//System.out.println("Date av"+date + "h"+heure);
		 Outil outil=new Outil();
		 DAO dao=new DAO();
		 ArrayList<Enseignant> enseignants= new  ArrayList<Enseignant>();
		 ArrayList<Enseignant> enseignatoccupe= new  ArrayList<Enseignant>();
		 enseignants=dao.getallenEnseignants();
		 enseignatoccupe=outil.searchEnseignantsbyDateHoure(date, heure);
		 //System.out.println(enseignatoccupe.size() +" fr ");
		 int i,y;
		 for(i=0;i<enseignatoccupe.size();i++)
		 {
			 for(y=0;y<enseignants.size();y++)
			 {
				 if(enseignatoccupe.get(i).getID()==enseignants.get(y).getID())
				 {
					 enseignants.remove(y);
					 y--;
				 }
			 }
		 }
		 return enseignants;
	}
	
	public int nbseance(ArrayList<Seance> seances)
	{
		int nb=0,i;
		for(i=0;i<seances.size();i++)
		{
			nb+=seances.get(i).getduree();
		}
		return nb;
	}
	

	
	public ArrayList<Enseignant> searchEnseignantsbyDateHoure(Date date, int heure)
	{
		 DAO dao=new DAO();
		 ArrayList<Enseignant> enseignants= new  ArrayList<Enseignant>();
		 ArrayList<Seance> seanceperday= new ArrayList<Seance>();
		 seanceperday=searchSeancebyDateHoure(date, heure);
		 //System.out.println("date"+date + "h"+heure+ "seancetrouver"+seanceperday.size());
		 int i,y;
		 for (i=0;i<seanceperday.size();i++)
		 {
			 for(y=0;y<seanceperday.get(i).getEnseignants().size();y++)
			 {
			 enseignants.add(seanceperday.get(i).getEnseignants().get(y));
			 }
		}
		 return enseignants;
	}
	
	public ArrayList<Seance> rechercheseance(String recherche)
	{
		ArrayList<Seance> seances= new ArrayList<Seance>();
		
		return seances;
	}
}

