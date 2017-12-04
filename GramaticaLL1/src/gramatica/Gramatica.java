package gramatica;
import java.util.ArrayList;

public class Gramatica {
	ArrayList<String> Nterminais = new ArrayList<String>();
	ArrayList<String> terminais = new ArrayList<String>();
	ArrayList<String> producoes = new ArrayList<String>();
	String start;
	
	public Gramatica(){
		
	}
	
	public Gramatica(ArrayList<String> Nterminais, ArrayList<String> terminais,ArrayList<String> producoes,String start){
		this.Nterminais = Nterminais;
		this.terminais = terminais;
		this.producoes = producoes;
		this.start = start;
	}
	
	public void addProducao(String producao){
		this.producoes.add(producao);
	}
	
	public void listarGramatica(){
		for(String prod:this.producoes){
			System.out.println(prod);
		}
	}
	
	public void listarPorSimbolo(String simbolo){
		for(String prod:this.producoes){
			String[] temp = prod.split("->");
			if(temp[0].equals(simbolo)){
				System.out.println(prod);
			}
		}
	}
	
	public void listarFirst(){
		System.out.println("Lista de First's:");
		for(String prod:this.producoes){
			String[] temp = prod.split("->");
			System.out.print("First("+ temp[0]+")={");
			if(isTerminal(""+temp[1].charAt(0))){
				System.out.println(temp[1].charAt(0)+"}");//adicionar os fist
			}else{
				System.out.println("First("+ temp[1]+")}");
			}
			
		}
	}
	
	public void listarFollow(){
		System.out.println("Lista de Follow's:");
		for(String prod:this.producoes){
			String[] temp = prod.split("->");
			if(temp[0].equals(start)){
				System.out.println("Follow("+temp[0]+")={$}");
			}else{
				//busca em todas as producoes por cada Nterminal.
			
			}
		}
	}
	
	public boolean isNTerminal(String termo){
		boolean controle = false;
		for(String naoTerminal:this.Nterminais){
			if(naoTerminal.equals(termo)){
				controle = true;
				break;
			}
		}
		return controle;
	}
	
	public boolean isTerminal(String termo){
		boolean controle = false;
		for(String naoTerminal:this.terminais){
			if(naoTerminal.equals(termo)){
				controle = true;
				break;
			}
		}
		return controle;
	}
	
	
	
	
	
	
	public static void main(String args[]){
		
		ArrayList<String> Nterminais = new ArrayList<String>();
		ArrayList<String> terminais = new ArrayList<String>();
		ArrayList<String> producoes = new ArrayList<String>();
		
		Nterminais.add("S");
		Nterminais.add("F");
		
		terminais.add("(");
		terminais.add("a");
		terminais.add(")");
		terminais.add("+");
		terminais.add("&");//vazio
		
		producoes.add("S->F");
		producoes.add("S->(S+F)");
		producoes.add("F->a");
		producoes.add("F->&");
		Gramatica g = new Gramatica(Nterminais,terminais,producoes,"S");
		//g.listarGramatica();
		//g.listarPorSimbolo("F");
		g.listarFirst();
		//g.listarFollow();
	}
}
