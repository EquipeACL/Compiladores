package gramatica;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Gramatica {
	ArrayList<String> Nterminais = new ArrayList<String>();
	ArrayList<String> terminais = new ArrayList<String>();
	ArrayList<String> producoes = new ArrayList<String>();
	String start;
	Map<String,String[]> first = new HashMap();
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
			//System.out.print("First("+ temp[0]+")={");
			if(isTerminal(""+temp[1].charAt(0)) || temp[1].charAt(0)=='&'){
				String temporario[] = first.get(temp[0]);
				if(temporario==null){
					temporario = new String[0];
				}
				String temporario2[] = new String[temporario.length+1];
				for(int i=0;i<temporario2.length-1;i++){
					temporario2[i] = temporario[i];
				}
				temporario2[temporario2.length-1] = ""+temp[1].charAt(0);
				first.put(temp[0],temporario2);
			}else{
				String temporario[] = first.get(temp[0]);
				if(temporario==null){
					temporario = new String[0];
				}
				String temporario2[] = new String[temporario.length+1];
				for(int i=0;i<temporario2.length-1;i++){
					temporario2[i] = temporario[i];
				}
				temporario2[temporario2.length-1] = ""+"First("+ temp[1].charAt(0)+")";
				first.put(temp[0],temporario2);
			}
			
		}
		for(String chave:this.Nterminais){
			System.out.print("first("+chave+"):{");
			String[] listafirst = first.get(chave);
			for(int i=0;i<listafirst.length;i++){
				if(listafirst[i].length()>1){
					String[] listafirstaux = first.get(""+listafirst[i].charAt(6));
					if(contemVazio(listafirstaux)){//se tiver vazio, add first do proximo
						achafirstComVazio(chave,listafirst[i].charAt(6));//procura a produção que gerou first(chave)=first(listafirst[i].charAt(6))
					}
					
					for(int j=0; j<listafirstaux.length;j++){//imprimi os fist sem o vazio
						if(!listafirstaux[j].equals("&")){
							System.out.print(listafirstaux[j]+", ");
						}
					}
					
				}else{
					System.out.print(listafirst[i]+ ", ");
				}
			}
			System.out.println("}");
		}
	}
	
	private void achafirstComVazio(String chave, char caracter) {
		for(String temp:producoes){
			String[] prod = temp.split("->");
			if(prod[0].equals(chave)){
				if(prod[1].charAt(0)==caracter){
					if(prod[1].length()<1){
						System.out.print("$");
					}else{
						System.out.print(Arrays.toString(first.get(""+prod[1].charAt(1)))+", ");
					}
					
				}
			}
		}		
	}

	private boolean contemVazio(String[] listafirst) {
		for(int i=0;i<listafirst.length;i++){
			if(listafirst[i].equals("&")){
				return true;
			}
		}
		return false;
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
		Nterminais.add("B");
		
		terminais.add("(");
		terminais.add("a");
		terminais.add(")");
		terminais.add("+");
		terminais.add("b");
		
		producoes.add("S->FB");
		producoes.add("S->(S+F)");
		producoes.add("F->a");
		producoes.add("F->&");
		producoes.add("B->b");
		Gramatica g = new Gramatica(Nterminais,terminais,producoes,"S");
		//g.listarGramatica();
		//g.listarPorSimbolo("F");
		g.listarFirst();
		//g.listarFollow();
	}
}
