
public class CentralProcessingUnit extends Product
{
	
	
	private int cpuCores; // e.g. 4-cores or 2-cores
	private double frequency; // e.g. 3.2 ghz 
	
	
	public CentralProcessingUnit()
	{
		
	}
	
	
	


	public int getCpuCores() 
	{
		return cpuCores;
	}
	public void setCpuCores(int cpuCores) 
	{
		this.cpuCores = cpuCores;
	}
	public double getFrequency() 
	{
		return frequency;
	}
	public void setFrequency(double frequency) 
	{
		this.frequency = frequency;
	}
	
	
}
