package smart_serv;

public class product {
	String Subcategory;
	String	Title;
	String Price;
	String Popularity; 
	
	product(String Subcategory,	String	Title,	String Price,	String Popularity){
		this.Popularity=Popularity;
		this.Price=Price;
		this.Subcategory=Subcategory;
		this.Title=Title;
		
	}
public String	toString()
	{
		return Subcategory+" "+Title+" "+Price+" "+Popularity;
	}

}
