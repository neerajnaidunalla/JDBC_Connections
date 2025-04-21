
class German{
	String name;
	int size;
	String Eating(String n) {
		return n+" eats a lot";
	}
}
class Serbian extends German{
	@Override
	String Eating(String h) {
		//super.Eating(String h);
		   return h+" is eating less";
	}
	
}

public class Poly {
	

	public static void main(String[] args) {
		
		Serbian s=new Serbian();
		System.out.println(s.Eating("daniel"));

	}

}