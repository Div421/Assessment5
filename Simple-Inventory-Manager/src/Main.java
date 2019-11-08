import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	ArrayList<Stock> stockList;
	ArrayList<String> idList;
	StockDao sD = new StockDao();
	public static void main(String args[]) {
		Main m = new Main();
		
		m.stockList = new ArrayList<>();
		m.idList = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		Stock s = new Stock("001","Pen"," Cello Blue Pen","20","200");
		m.stockList.add(s);
		Stock s1 = new Stock("002","Pencil","Apsara Pencil","10","120");
		m.stockList.add(s1);
		Stock s2 = new Stock("003","Apple","Kashmiri Apples","100","320");
		m.stockList.add(s2);
		Stock s3 = new Stock("004","Grape","Arabian Grapes","155","332");
		m.stockList.add(s3);
		Stock s4 = new Stock("005","Fish","Salmon","170","210");
		m.stockList.add(s4);
		m.idList.add("001");
		m.idList.add("002");
		m.idList.add("003");
		m.idList.add("004");
		m.idList.add("005");
		int f=1;
		do {
			System.out.println("MENU");
			System.out.println("1. Add Stock");
			System.out.println("2. View Stock");
			System.out.println("3. Update Stock");
			System.out.println("4. Delete Stock");
			System.out.println("0. Exit");
			System.out.println("Enter a option:");
			int opt=Integer.parseInt(sc.nextLine());
			switch(opt) {
			case 1:
				m.addStock();
				break;
			case 2:
				m.viewStock();
				break;
			case 3:
				m.updateStock();
				break;
			case 4:
				m.deleteStock();
				break;
			case 0:
				f=0;
				break;
			}	
			
			
		}while(f==1);
		
	}//main method ends
	
	class NameException extends Exception {
		public NameException(String message) {
			super(message);
		}
	}
	
	class idException extends Exception {
		public idException(String message) {
			super(message);
		}
	}
	
	class quantException extends Exception {
		public quantException(String message) {
			super(message);
		}
	}
	
	class priceException extends Exception {
		public priceException(String message) {
			super(message);
		}
	}
	
	public boolean validatename(String name) {
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]+$");
		try {
		if(p.matcher(name).matches()) {return false;}
		if (!(p.matcher(name).matches())) {
			throw new NameException("Name Should not contain numbers or special characters or be null");
		}
		else {return false;}
		}
		catch(NameException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	public boolean validateid(String ph) {
		Pattern p = Pattern.compile("^\\d{3}$");
		try {
		if(p.matcher(ph).matches()) {return false;}
		if (!(p.matcher(ph).matches())) {
			throw new idException("id Should be 3 digits");
		}
		else {return false;}
		}
		catch(idException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	
	public boolean validatequant(String quant) {
		Pattern p = Pattern.compile("^\\d+");
		try {
		if(p.matcher(quant).matches()) {return false;}
		if (!(p.matcher(quant).matches())) {
			throw new quantException("Quantity should be a positive number");
		}
		else {return false;}
		}
		catch(quantException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	
	public boolean validateprice(String price) {
		Pattern p = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
		try {
		if(p.matcher(price).matches()) {return false;}
		if (!(p.matcher(price).matches())) {
			throw new quantException("Price is not correct");
		}
		else {return false;}
		}
		catch(quantException e){
			System.err.println(e.getMessage());
			return true;
		}
	}
	
	public void addStock() {
		Scanner sc = new Scanner(System.in);
		System.out.printf("%70s\n","ENTER DETAILS OF PRODUCT");
		System.out.println("Enter Product Id : ");
		String id=sc.nextLine();
		
		while(this.validateid(id)) {
			System.out.println("Id must be a three digit number.\nPlease enter valid id : ");
			id=sc.nextLine();
		}
		if(idList.contains(id)) {
			System.out.println("Product already exists");
		}
		else {
			System.out.println("Enter Product Name : ");
			String name=sc.nextLine();
			while(this.validatename(name)) {
				System.out.println("Please enter valid product name : ");
				name=sc.nextLine();
			}
			System.out.println("Enter Product Description : ");
			String des=sc.nextLine();
			
			
			System.out.println("Enter Quantity : ");
			String quant = (sc.nextLine());
			while(this.validatequant(quant)) {
				System.out.println("Please enter valid product quantity : ");
				quant=sc.nextLine();
			}
			
			System.out.println("Enter Price : ");
			String price = (sc.nextLine());
			while(this.validateprice(price)) {
				System.out.println("Please enter valid product price : ");
				price=sc.nextLine();
			}
			
			Stock st = new Stock(id,name,des,price,quant);
			this.stockList.add(st);
			sD.create(st);
			
		}
		

		
	}
	
	public void updateStock() {
		int flag=1;
		List<Stock> stocks = sD.findAll();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Item Id to Update");
		String i =sc.nextLine();
		for(Stock s:stocks) {
			if(s.getId().equals(i)) {
				System.out.println("Enter the new Product Name: ");
				String name = sc.nextLine();
				while(validatename(name)) {
					System.out.println("Please enter valid product name : ");
					name=sc.nextLine();
				}
				System.out.println("Enter new Product Description : ");
				String des=sc.nextLine();
				
				
				System.out.println("Enter the new Quantity :");
				String quant = (sc.nextLine());
				while(this.validatequant(quant)) {
					System.out.println("Please enter valid product quantity : ");
					quant=sc.nextLine();
				}
				
				System.out.println("Enter Price : ");
				String price = (sc.nextLine());
				while(this.validateprice(price)) {
					System.out.println("Please enter valid product price : ");
					price=sc.nextLine();
				}
				
				
				
				sD.updateById(i,name,des,price,quant);
				
				flag=0;
				
				System.out.println("Product has been Updated Successfully");
				
				
			}
			
			
		}
		if(flag==1) {
			System.out.println("No item matches the entered Item id");
		}
		
	}
	
	public void viewStock() {
		
		List<Stock> stocks = sD.findAll();
		System.out.println("**************************** STOCK DETAILS ************************************\n");
		System.out.format("%10s %10s %20s %10s %10s\n", "ID","NAME","DESCRIPTION","QUANTITY","PRICE");
		for (Stock n : stocks) {
			System.out.format("%10s %10s %20s %10s %10s",n.getId(),n.getName(),n.getDes(),n.getQuant(),n.getPrice());
			System.out.println();

	}
		System.out.println();
		System.out.println("******************************************************************************");
	}
	
	public void deleteStock() {
		
		int flag=1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Item Id to Delete");
		String i =sc.nextLine();
		for(Stock s:stockList) {
			if(s.getId().equals(i)) {
				stockList.remove(s);
				flag=0;
			}
		}
		if(flag==1) {
			System.out.println("No Such Item Found");
			viewStock();
			
		}
	}

}
