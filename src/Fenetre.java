import javax.swing.JFrame;

public class Fenetre extends JFrame
{
	private Wall wall;
	public Fenetre() {
	// TODO Auto-generated constructor stub
		this.setTitle("ECE PLANNING");		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1600,800);
		this.setVisible(true);
		wall= new Wall();
		// menu= new Menu(wall);
		add(wall);
		this.validate();
	}

}
