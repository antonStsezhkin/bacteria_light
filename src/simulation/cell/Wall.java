package simulation.cell;

public class Wall extends DeadCell{
	public static final Wall INSTANCE = new Wall();
	private Wall(){
		super(0);
	}
}
