package Mechanic;


public interface Movement{
	

	void moveUp();
	
	void moveDown();
	
	void moveLeft();

	void moveRight();
	
	/*void moveCharacter()
	{
		int k=0; //при нажатии на кнопку вызывает один из кейсов
		switch(k)
		{
			case 1: 
				moveUp();
				break;
			case 2:
				moveDown();
				break;
			case 3:
				moveLeft();
				break;
			case 4:
				moveRight();
				break;
		}
	}*/
}
