package view;

import javax.swing.JPanel;

public abstract class Map_Father extends JPanel{
	abstract public void musicStart();
	abstract public void musicPause();
	
	abstract public void moveUp ();
	abstract public void moveDown();
	abstract public void moveLeft();
	abstract public void moveRight();
	
	abstract public void mapSwitching();

	abstract public void setMapY(int y);
	abstract public void setMapX(int x);

	abstract public int getMapX();
	abstract public int getMapY();
}
