
public class GraphicsCard extends Product
{
	
	
	private int VramSize; // e.g. 2, 4, 6, 8 gb 
	private int clockSpeed; // e.g. 1733 MHz , 1216 MHz
	
	
	public GraphicsCard()
	{
		
	}
	
	
	
	



	public int getVramSize() 
	{
		return VramSize;
	}
	public void setVramSize(int vramSize) 
	{
		VramSize = vramSize;
	}

	public int getClockSpeed() 
	{
		return clockSpeed;
	}
	public void setClockSpeed(int clockSpeed) 
	{
		this.clockSpeed = clockSpeed;
	}
	
	

}
