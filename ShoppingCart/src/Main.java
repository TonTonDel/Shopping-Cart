import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{	
	// I chose computer parts for the objects in my shopping cart which are GPU, CPU, and Motherboard.
	private static ArrayList<Product> globalList = new ArrayList<Product>();
	private static ArrayList<Motherboard> motherboardList = new ArrayList<Motherboard>();
	private static ArrayList<GraphicsCard> gpuList = new ArrayList<GraphicsCard>();
	private static ArrayList<CentralProcessingUnit> cpuList = new ArrayList<CentralProcessingUnit>();
	private static ArrayList<Product> transaction = new ArrayList<Product>();
	private static ArrayList<Product> productSold = new ArrayList<Product>();
	private static DecimalFormat format = new DecimalFormat("0.00");
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Scanner input = new Scanner(System.in);
	private static int centralID = 100;

	public static void main(String[] args) 
	{
		File f = new File("list.ser");
		
		if (f.exists())
		{
			try {
				importDataBase();
			} catch (Exception e) {
				System.out.println("Error");
			}
			centralID = centralID + productSold.size();
			mainMenu();
		
		}
		else
		{
			mainMenu();
		}
		mainMenu();

	}
	private static void exportDataBase() throws Exception
	{
		// Export Entire Array List as .ser file
		FileOutputStream exportFile = new FileOutputStream("list.ser");
		ObjectOutputStream exportOutput = new ObjectOutputStream(exportFile);
		exportOutput.writeObject(productSold);// Enter name of ArrayList to be exported here
		exportOutput.close();
	}
	@SuppressWarnings("unchecked")
	private static void importDataBase() throws Exception
	{
		// Export Entire Array List as .ser file
		FileInputStream importFile = new FileInputStream("list.ser");
		ObjectInputStream importInput = new ObjectInputStream(importFile);
		
		// Change ArrayList to name of your ArrayList and Datatype of Object as required to cast objects
		productSold = (ArrayList<Product>) importInput.readObject();
		importInput.close();
		
		System.out.println("Import Successful");
	}
	private static void mainMenu() // This is the main menu where the user can choose either Product menu, Sales menu, or Admin menu.
	{
		System.out.println("1 \t Product Menu ");
		System.out.println("2 \t Sales Menu");
		System.out.println("3 \t Admin Menu");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				productMenu();
				break;
			}
			case "2":
			{
				salesMenu();
				break; 
			}
			case "3":
			{
				adminMenu();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 3");
				mainMenu();
			}
		
		}
		
		mainMenu();
	}
	private static void adminMenu() // Admin menu has the Sales and Stock controls. Here, the user can see number of products sold, revenue or value of sales.
	{	
		System.out.println("1 \t Sales");
		System.out.println("2 \t Add Stocks");
		System.out.println("3 \t Clear Stocks");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				double allProductSoldValue=0;
				
				for (Product i: productSold)
				{
					allProductSoldValue = allProductSoldValue + i.getPrice();
				}
				System.out.println("Total Number of Products Sold is " + productSold.size());
				System.out.println("Total Value of Sales " + format.format(allProductSoldValue));
				System.out.println("Average Revenue Per Products Sold " + format.format((allProductSoldValue/productSold.size())));
				break;
			}
			case "2":
			{
				System.out.println("1 \t GPU Stocks");
				System.out.println("2 \t CPU Stocks");
				System.out.println("3 \t Motherboard Stocks");
				System.out.println("4 \t Exit");
				
				String choice1 = input.next();
				
				switch(choice1)
				{
					case "1":
					{	
						for(GraphicsCard i: gpuList)
						{
							printGpu();
							
						}
						System.out.println("Enter ID of Item you want to edit");
					  	int toEdit= input.nextInt();
						
							for(GraphicsCard i: gpuList)
							{
								if (toEdit==i.getId())
								{
									System.out.println("You have chosen to edit "+ i.getName() +"/t"+ i.getType());
									System.out.println("Enter Quantity");
									i.setQuantity(input.nextInt());
								}
							}

						break;
					}
					case "2":
					{	
						for(CentralProcessingUnit c: cpuList)
						{
							printCpu();
							
						}
						System.out.println("Enter ID of Item you want to edit");
					  	int toEdit= input.nextInt();
						
							for(CentralProcessingUnit i: cpuList)
							{
								if (toEdit==i.getId())
								{
									System.out.println("You have chosen to edit "+ i.getName() +"/t"+ i.getType());
									System.out.println("Enter Quantity");
									i.setQuantity(input.nextInt());
								}
							}

						break;
					}
					case "3":
					{	
						for(Motherboard i: motherboardList)
						{
							printMtb();
							
						}
						System.out.println("Enter ID of Item you want to edit");
					  	int toEdit= input.nextInt();
						
							for(Motherboard i: motherboardList)
							{
								if (toEdit==i.getId())
								{
									System.out.println("You have chosen to edit "+ i.getName() +"/t"+ i.getType());
									System.out.println("Enter Quantity");
									i.setQuantity(input.nextInt());
								}
							}

						break;
					}
					default:
					{
						System.out.println("Error. Please choose 1 to 3");
						mainMenu();
					}
				}
				
				break; 
			}
			case "3":
			{
				adminMenu();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 3");
				mainMenu();
			}
		
		}
			
			
		mainMenu();
	}
	private static void salesMenu() // Sales menu is where the user can buy, remove and view items in the cart. It is also where the user can checkout.
	{
		System.out.println("1 \t Buy product(s)");
		System.out.println("2 \t Remove product(s) from cart");
		System.out.println("3 \t View cart");
		System.out.println("4 \t Pay now ");
		System.out.println("5 \t Exit");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				buyProduct();
				break;
			}
			case "2":
			{
				removeProduct();
				break; 
			}
			case "3":
			{
				viewCart();
				break;
			}
			case "4":
			{
				payNow();
				break;
			}
			case "5":
			{
				mainMenu();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 5");
				salesMenu();
			}
		
		}
		mainMenu();
	}
	private static void payNow() //The method for checking out and paying.
	{
		double transactionTotal=0;
		
		for(Product i: transaction)
		{
			transactionTotal = transactionTotal + i.getPrice(); 
			System.out.println("Total Transaction Cost \t € " + (transactionTotal + i.getPrice()));
			System.out.println("Total Items " +  transaction.size());
			System.out.println("Please enter payment");
			
			double moneyEntered = input.nextDouble();
			
			while(moneyEntered<transactionTotal)
			{
				System.out.println("Remaining balance is \t € " + (transactionTotal-moneyEntered));
				moneyEntered+= input.nextDouble();
			}
			
			if(moneyEntered>= transactionTotal)
			{
				System.out.println("Transaction Successful. Change is \t € " + ((moneyEntered - transactionTotal)));
				for(Product t: transaction)
				{
					
					productSold.add(t);
				}
				transaction.clear();
			}
		}
		
	}
	private static void viewCart() // Method for viewing the items the user put in the cart.
	{
		// Common Values
				for(Product i: transaction)
				{
					System.out.println("*****************************");
					System.out.println("ID is " + i.getId());
					System.out.println("Name is " + i.getName());
					System.out.println("Type is " + i.getType());
					System.out.println("Quantity is " + i.getQuantity());
					
					
					// GPU Only
					if (i.getType().equals("GPU"))
					{
						GraphicsCard g = (GraphicsCard)i;
						System.out.println("VRAM is " + g.getVramSize());
						System.out.println("Clock Speed is is " + g.getClockSpeed());
						System.out.println("Price is "+ g.getGpuPrice());
					}
					// CPU Only
					if (i.getType().equals("CPU"))
					{
						CentralProcessingUnit c = (CentralProcessingUnit)i;
						System.out.println("CPU Core is " + c.getCpuCores());
						System.out.println("Frequency is " + c.getFrequency());
						System.out.println("Price is " + c.getCpuPrice());
					}
					// Motherboard Only
					if (i.getType().equals("Motherboard"))
					{
						Motherboard m = (Motherboard)i;
						System.out.println("Maximum Memory is " + m.getMaximumMemory());
						System.out.println("Price is " + m.getMtbPrice());
						
						
					}
					
					System.out.println("*****************************");
				}
		
	}
	private static void removeProduct() // Removes product from the user's cart only.
	{
		System.out.println("0 \t View All");
		System.out.println("1 \t Remove GPU ");
		System.out.println("2 \t Remove CPU");
		System.out.println("3 \t Remove Motherboard");
		System.out.println("4 \t Remove All");
		System.out.println("5 \t Exit");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "0":
			{
				printAll();
				break; 
			}
			case "1":
			{
				for(GraphicsCard g: gpuList)
				{
					printGpu();
					
				}
				System.out.println("Enter ID of GPU to remove");
				int toRemove= input.nextInt();
				for(Product i: gpuList)
				{
					if (toRemove==i.getId())
					{
						transaction.remove(i);
					}	
				}	
				break;
			}
			case "2":
			{
				for(CentralProcessingUnit c: cpuList)
				{
					printCpu();
					
				}
				System.out.println("Enter ID of CPU to remove");
				int toRemove= input.nextInt();
				for(Product i: cpuList)
				{
					if (toRemove==i.getId())
					{
						transaction.remove(i);
					}	
				}	
				break;
			}
			case "3":
			{
				for(Motherboard m: motherboardList)
				{
					printMtb();
					
				}
				System.out.println("Enter ID of Motherboard to remove");
				int toRemove= input.nextInt();
				for(Product i: motherboardList)
				{
					if (toRemove==i.getId())
					{
						transaction.remove(i);
					}	
				}	
				break;
			}
			case "4":
			{
				transaction.clear();
				break;
			}
			case "5":
			{
				payNow();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 4");
				removeProduct();
			}
		
		}
		System.out.println("Product(s) succesfully removed");
		mainMenu();
		
		
	}
	private static void buyProduct() // The method for buying products.

	{
		System.out.println("0 \t View All");
		System.out.println("1 \t Buy GPU ");
		System.out.println("2 \t Buy CPU");
		System.out.println("3 \t Buy Motherboard");
		System.out.println("4 \t Exit");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "0":
			{
				printAll();
				break; 
			}
			case "1":
			{
				for(GraphicsCard g: gpuList)
				{
					printGpu();
					
				}
				System.out.println("Enter ID of GPU to buy");
				int toBuy= input.nextInt();
				for(Product i: gpuList)
				{
					if (toBuy==i.getId())
					{
						transaction.add(i);
						
						
					}	
				}	
				break;
			}
			case "2":
			{
				for(CentralProcessingUnit c: cpuList)
				{
					printCpu();
					
				}
				System.out.println("Enter ID of CPU to buy");
				int toBuy= input.nextInt();
				for(Product i: cpuList)
				{
					if (toBuy==i.getId())
					{
						transaction.add(i);
					}	
				}	
				break;
			}
			case "3":
			{
				for(Motherboard m: motherboardList)
				{
					printMtb();
					
				}
				System.out.println("Enter ID of Motherboard to buy");
				int toBuy= input.nextInt();
				for(Product i: motherboardList)
				{
					if (toBuy==i.getId())
					{
						transaction.add(i);
					}	
				}	
				break;
			}
			case "4":
			{
				salesMenu();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 4");
				buyProduct();
			}
		
		}
		mainMenu();
		
	}
	private static void productMenu() // Method where the user can create, edit and delete the products. The user can also view existing products. 
	{
		System.out.println("1 \t Add Product ");
		System.out.println("2 \t Edit Product ");
		System.out.println("3 \t Delete Product ");
		System.out.println("4 \t View Product ");
		System.out.println("5 \t Exit");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				addProduct();
				break;
			}
			case "2":
			{
				editProduct();
				break; 
			}
			case "3":
			{
				deleteProduct();
				break;
			}
			case "4":
			{
				viewProduct();
				break;
			}
			case "5":
			{
				mainMenu();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 4");
				productMenu();
			}
		
		}
		mainMenu();
	}
	private static void printAll() // Prints all products
	{	// Common Values
		for(Product i: globalList)
		{
			System.out.println("*****************************");
			System.out.println("ID is " + i.getId());
			System.out.println("Name is " + i.getName());
			System.out.println("Type is " + i.getType());
			System.out.println(" Quantity is " + i.getQuantity());
			
			// GPU Only
			if (i.getType().equals("GPU"))
			{
				GraphicsCard g = (GraphicsCard)i;
				System.out.println("VRAM is " + g.getVramSize());
				System.out.println("Clock Speed is " + g.getClockSpeed());
				System.out.println("Price is" + g.getGpuPrice());
			}
			// CPU Only
			if (i.getType().equals("CPU"))
			{
				CentralProcessingUnit c = (CentralProcessingUnit)i;
				System.out.println("CPU Core is " + c.getCpuCores());
				System.out.println("Frequency is " + c.getFrequency());
				System.out.println("Price is " + c.getCpuPrice());
			}
			// Motherboard Only
			if (i.getType().equals("Motherboard"))
			{
				Motherboard m = (Motherboard)i;
				System.out.println("Memory Socket is " + m.getMaximumMemory());
				System.out.println("Price is "+ m.getMtbPrice());
				
			}
			
			System.out.println("*****************************");
		}
	}
	private static void printGpu() // Prints GPUs only
	{
		for(GraphicsCard i: gpuList)
		{
			System.out.println("*****************************");
			System.out.println("ID is " + i.getId());
			System.out.println("Name is " + i.getName());
			System.out.println("Type is " + i.getType());
			System.out.println("VRAM is " + i.getVramSize());
			System.out.println("Clock Speed is " + i.getClockSpeed());
			System.out.println("Price is " + i.getGpuPrice());
			System.out.println(" Quantity is " + i.getQuantity());
			System.out.println("*****************************");
		}
	}
	private static void printCpu() // Prints CPUs only.
	{
		for(CentralProcessingUnit i: cpuList)
		{
			System.out.println("*****************************");
			System.out.println("ID is " + i.getId());
			System.out.println("Name is " + i.getName());
			System.out.println("Type is " + i.getType());
			System.out.println("CPU Core is " + i.getCpuCores());
			System.out.println("Frequency is " + i.getFrequency());
			System.out.println("Price is " + i.getCpuPrice());
			System.out.println(" Quantity is " + i.getQuantity());
			System.out.println("*****************************");
		}
	}
	private static void printMtb() // Mtb = Motherboard 
	{
		for(Motherboard i:motherboardList)
		{
			System.out.println("*****************************");
			System.out.println("ID is " + i.getId());
			System.out.println("Name is " + i.getName());
			System.out.println("Type is " + i.getType());
			System.out.println("Maximum Memory is " + i.getMaximumMemory());
			System.out.println("Price is " + i.getMtbPrice());
			System.out.println(" Quantity is " + i.getQuantity());
			System.out.println("*****************************");
		}
	}
		
	private static void viewProduct() // Method for viewing all products the user has created.

	{
		System.out.println("1 \t View All ");
		System.out.println("2 \t View GPU ");
		System.out.println("3 \t View CPU ");
		System.out.println("4 \t View Motherboard ");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				printAll();
				break;
			}
			case "2":
			{
				printGpu();
				break; 
			}
			
			case "3":
			{
				printCpu();
				break;
			}
			case "4":
			{
				printMtb();
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 4");
				viewProduct();
			}
		
		}
		mainMenu();
		
	}
	private static void deleteProduct() // Deletes product the user has previously created. Does not delete items from the cart.
	{
		 for(Product i: globalList)
		 {
			printAll(); 
		 }
		System.out.println("Enter ID of product to delete");
		int toDelete= input.nextInt();
		
		
		for(Product i: globalList)
		{
			if (toDelete==i.getId())
			{
				
					
						System.out.println("You have chosen to delete "+ i.getId() + "\t" + i.getName()+ "\t" + i.getType());
						System.out.println("Press 1 to confirm deletion");
						
						String choice = input.next();
						switch (choice)
						{
							case "1":
							{
								globalList.remove(i);
								motherboardList.remove(i);
								cpuList.remove(i);
								gpuList.remove(i);
								
							break;
							}
							default:
							{
								System.out.println("Invalid choice");
								deleteProduct();
								break;
							}
							
						}
						System.out.println("Product deleted");
						mainMenu();
			}
		}
		
		mainMenu();
		
	}
	private static void editProduct() // Method for editing existing products. Has choices for GPU, CPU and Motherboard.
	{
		
		System.out.println("1 \t Edit GPU ");
		System.out.println("2 \t Edit CPU ");
		System.out.println("3 \t Edit Motherboard ");
		System.out.println("4 \t Exit ");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				try {
					editGpu();
				} catch (IOException e) 
				{
					
				}
				break;
			}
			case "2":
			{
				try {
					editCpu();
				} catch (IOException e) 
				{
					
				}
				break; 
			}
			
			case "3":
			{
				try {
					editMtb();
				} catch (IOException e) 
				{
					
				}
				break;
			}
			case "4":
			{
				
					mainMenu();
				
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 4");
				viewProduct();
			}
		
		}
		mainMenu();
		
	
		
		
	}
	private static void editMtb() throws IOException // Edits Motherboards
	{
		for(Motherboard i: motherboardList)
		{
			printMtb();
			
		}
		System.out.println("Enter ID of Item you want to edit");
	  	int toEdit= input.nextInt();
		
			for(Motherboard i: motherboardList)
			{
				if (toEdit==i.getId())
				{
					
						
							System.out.println("You have chosen to edit "+ i.getName() +"/t"+ i.getType());
							System.out.println("******************************************************");
							System.out.println("1 \t Edit Name  ");
							System.out.println("2 \t Edit Type  " );
							System.out.println("3 \t Edit Maximum Memory  ");							
							System.out.println("5 \t Edit Quantity  ");
							System.out.println("******************************************************");
							
							String choice = input.next();
							switch (choice)
							{
								case "1":
								{
									System.out.println("Enter Name" );
									i.setName(reader.readLine());
									break;
								}
								case "2":
								{
									System.out.println("Enter Type");
									i.setQuantity(input.nextInt());
									break;
								}
								case "3":
								{
									System.out.println("Enter Maximum Memory");
									i.setMaximumMemory(input.nextInt());
									break;
								}								
								case "4":
								{
									System.out.println("Enter Quantity");
									i.setQuantity(input.nextInt());
									break;
								}
								default:
								{
									System.out.println("Invalid choice. Please press 1 - 4");
									editMtb();
									break;
								}
							}
							editProduct();
				}
			}
		
	}
	private static void editCpu() throws IOException // Edits CPUs
	{
		for(CentralProcessingUnit i: cpuList)
		{
			printCpu();
			
		}
		System.out.println("Enter ID of Item you want to edit");
	  	int toEdit= input.nextInt();
		
			for(CentralProcessingUnit i: cpuList)
			{
				if (toEdit==i.getId())
				{
					
						
							System.out.println("You have chosen to edit "+ i.getName() +"/t"+ i.getType());
							System.out.println("******************************************************");
							System.out.println("1 \t Edit Name ");
							System.out.println("2 \t Edit Type " );
							System.out.println("3 \t Edit CPU Cores ");
							System.out.println("4 \t Edit Frequency ");
							System.out.println("5 \t Edit Quantity ");
							System.out.println("******************************************************");
							
							String choice = input.next();
							switch (choice)
							{
								case "1":
								{
									System.out.println("Enter Name" );
									i.setName(reader.readLine());
									break;
								}
								case "2":
								{
									System.out.println("Enter Type");
									i.setQuantity(input.nextInt());
									break;
								}
								case "3":
								{
									System.out.println("Enter CPU Cores");
									i.setCpuCores(input.nextInt());
									break;
								}
								case "4":
								{
									System.out.println("Enter Frequency");
									i.setFrequency(input.nextInt());
									break;
								}		
								case "5":
								{
									System.out.println("Enter Quantity");
									i.setQuantity(input.nextInt());
									break;
								}
								default:
								{
									System.out.println("Invalid choice. Please press 1 - 5");
									editCpu();
									break;
								}
							}
							editProduct();
				}
			}
		
		
	}
	private static void editGpu() throws IOException // Edits GPUs
	{
		for(GraphicsCard i: gpuList)
		{
			printGpu();
			
		}
		System.out.println("Enter ID of Item you want to edit");
	  	int toEdit= input.nextInt();
		
			for(GraphicsCard i: gpuList)
			{
				if (toEdit==i.getId())
				{
					
						
							System.out.println("You have chosen to edit "+ i.getName() +"/t"+ i.getType());
							System.out.println("******************************************************");
							System.out.println("1 \t Edit Name " );
							System.out.println("2 \t Edit Type " );
							System.out.println("3 \t Edit CPU Cores ");
							System.out.println("4 \t Edit Clock Speed ");
							System.out.println("5 \t Edit Quantity " );
							System.out.println("******************************************************");
							
							String choice = input.next();
							switch (choice)
							{
								case "1":
								{
									System.out.println("Enter Name" );
									i.setName(reader.readLine());
									break;
								}
								case "2":
								{
									System.out.println("Enter Type");
									i.setQuantity(input.nextInt());
									break;
								}
								case "3":
								{
									System.out.println("Enter VRAM Size");
									i.setVramSize(input.nextInt());
									break;
								}
								case "4":
								{
									System.out.println("Enter Clock Speed");
									i.setClockSpeed(input.nextInt());
									break;
								}
	
								case "5":
								{
									System.out.println("Enter Quantity");
									i.setQuantity(input.nextInt());
									break;
								}
								default:
								{
									System.out.println("Invalid choice. Please press 1 - 6");
									editGpu();
									break;
								}
							}
							editProduct();
				}
			}
		
		
	}
	private static void addProduct() // Method for creating products. Leads to each products's create methods
	{
		System.out.println("1 \t Create Graphics Card ");
		System.out.println("2 \t Create CPU ");
		System.out.println("3 \t Create Motherboard ");
		
		String choice = input.next();
		
		switch(choice)
		{
			case "1":
			{
				try {
					createGPU();
				} catch (IOException e) 
				{
					
				}
				break;
			}
			case "2":
			{
				try {
					createCPU();
				} catch (IOException e) 
				{
					
				}
				break; 
			}
			
			case "3":
			{
				try {
					createMtb();
				} catch (IOException e) 
				{
					
				}
				break;
			}
			default:
			{
				System.out.println("Error. Please choose 1 to 3");
				addProduct();
			}
		
		}
		mainMenu();
		
	}
	private static void createMtb() throws IOException // Creates Motherboard
	{
		Motherboard m = new Motherboard();
		m.setId(centralID);
		centralID++;
		m.setType("Motherboard");
		System.out.println("Enter Name (ASUS, GIGABYTE, MSI)");
		m.setName(reader.readLine());
		System.out.println("Enter Quantity");
		m.setQuantity(input.nextInt());
		System.out.println("Enter Maximum Memory(16, 32, 64)(GB)");
		m.setMaximumMemory(input.nextInt());
		
		
		globalList.add(m);
		motherboardList.add(m);
		
	}
	private static void createCPU() throws IOException // Creates CPUs
	{
		CentralProcessingUnit c = new CentralProcessingUnit();
		c.setId(centralID);
		centralID++;
		c.setType("CPU");
		System.out.println("Enter Name (ASUS, GIGABYTE, MSI)");
		c.setName(reader.readLine());
		System.out.println("Enter Quantity");
		c.setQuantity(input.nextInt());
		System.out.println(" Enter Number of Cores (2, 4, 6, 8)");
		c.setCpuCores(input.nextInt());
		System.out.println("Enter Frequency (e.g. 3.5 , 2.5 )(Ghz)" );
		c.setFrequency(input.nextDouble());
		
		
		globalList.add(c);
		cpuList.add(c);
		
	}
	private static void createGPU() throws IOException // Creates GPUs
	{
		GraphicsCard g = new GraphicsCard();
		g.setId(centralID);
		centralID++;
		g.setType("GPU");
		System.out.println("Enter Name (ASUS, GIGABYTE, MSI)");
		g.setName(reader.readLine());
		System.out.println("Enter Quantity");
		g.setQuantity(input.nextInt());
		System.out.println("Enter VRam Size (2, 4, 6, etc.)(GB)");
		g.setVramSize(input.nextInt());
		System.out.println("Enter Clock Speed (e.g. 1733  , 1216 )(Mhz)");
		g.setClockSpeed(input.nextInt());
		
		
		globalList.add(g);
		gpuList.add(g);
	
		
	}
	

}
