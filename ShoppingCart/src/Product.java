
public class Product 
{	
	private String name;
	
	private double price;
	private double gpuPrice = 350;
	private double cpuPrice = 300;
	private double mtbPrice = 200;
	

	private int id;
	private String type; // GPU, CPU, Motherboard, 
	private int quantity;
	
	
	public Product()
	{
		
	}
	public void calcPrice()
	{
		price=0;
		if(type.equals("GPU"))// == for Strings and Objects
		{
			price = price + 350;
		}
		if(type.equals("CPU"))// == for Strings and Objects
		{
			price = price + 300;
		}
		else
		{
			price = price + 200;
			
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}


	public double getGpuPrice() {
		return gpuPrice;
	}


	public void setGpuPrice(double gpuPrice) {
		this.gpuPrice = gpuPrice;
	}


	public double getCpuPrice() {
		return cpuPrice;
	}


	public void setCpuPrice(double cpuPrice) {
		this.cpuPrice = cpuPrice;
	}


	public double getMtbPrice() {
		return mtbPrice;
	}


	public void setMtbPrice(double mtbPrice) {
		this.mtbPrice = mtbPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
	
}


