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
	Map<String,String[]> follow = new HashMap();
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
				String[] temporario2 = append(temp[0],""+temp[1].charAt(0));
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
				temporario2[temporario2.length-1] = ""+"First("+ temp[1]+")";
				first.put(temp[0],temporario2);
			}
			
		}
		for(String chave:this.Nterminais){
			//System.out.print("first("+chave+"):{");
			String[] listafirst = first.get(chave);
			for(int i=0;i<listafirst.length;i++){
				if(listafirst[i].length()>1){
					String[] listafirstaux = first.get(""+listafirst[i].charAt(6));
					if(contemVazio(listafirstaux)){//se tiver vazio, add first do proximo
						achafirstComVazio(chave,listafirst[i].substring(6,listafirst[i].length()-1));//procura a produção que gerou first(chave)=first(listafirst[i].charAt(6))
					}					
					for(int j=0; j<listafirstaux.length;j++){//imprimi os fist sem o vazio
						if(!listafirstaux[j].equals("&")){
							String[] temporario = append(chave,listafirstaux[j]);
							first.put(chave,temporario);
							//System.out.print(listafirstaux[j]+", ");
						}
					}
					//first.remove(chave);
				}else{
					String[] temporario = append(chave,listafirst[i]);
					first.put(chave,temporario);
					//System.out.print(listafirst[i]+ ", ");
				}
			}
			//System.out.println("}");
			
		}
		removeNull();
		removeProducoes();		
		for(String chave:Nterminais) {
			System.out.print("First("+chave+"):");
			System.out.println(Arrays.toString(first.get(chave)));
		}
	}
	
	private String[] append(String chave,String conteudo) {
		boolean controle = false;
		//System.out.println("ADD em "+chave+" - "+conteudo);
		String temporario[] = first.get(chave);
		if(temporario==null){
			temporario = new String[0];
		}
		String temporario2[] = new String[temporario.length+1];
		for(int i=0;i<temporario2.length-1;i++){
			try {
				if(temporario[i].equals(conteudo)) {
					controle = true;
				}
			}catch(Exception ex) {
				
			}
			temporario2[i] = temporario[i];
//			if(temporario[i].length()>1) {
//				temporario2[i] = temporario[i];
//			}else {
//				temporario2[i] = temporario[i];
//			}
		}
		if(controle==false) {
			temporario2[temporario2.length-1] = ""+conteudo.charAt(0);
		}
		//temporario2 = removerDeleteds(temporario2);
		return temporario2;
	}

	private void achafirstComVazio(String chave, String caracter) {
		//System.out.println("First("+chave+"):{First("+caracter+")");
		for(String temp:producoes){
			String[] prod = temp.split("->");
			if(prod[0].equals(chave)){
				if(prod[1].charAt(0)==caracter.charAt(0)){
					for(int k=0;k<prod[1].length();k++) {
						//System.out.print("AQUI: "+prod[1].charAt(k));
						String[] listafirstaux = first.get(""+prod[1].charAt(k)); 
						if(contemVazio(listafirstaux)){
							for(int j=0; j<listafirstaux.length;j++){//imprimi os fist sem o vazio
								if(!listafirstaux[j].equals("&")){
									String[] temporario = append(chave,listafirstaux[j]);
									first.put(chave,temporario);
									//System.out.print(listafirstaux[j]+", ");
								}
							}
						}else {
							//System.out.println("Adicionando: "+Arrays.toString(listafirstaux));
							for(int j=0; j<listafirstaux.length;j++){
								String[] temporario = append(chave,listafirstaux[j]);
								first.put(chave,temporario);
								//System.out.print(listafirstaux[j]+", ");
							}
							break;
						}
					}
					
				}
			}
		}		
	}

	private boolean contemVazio(String[] listafirst) {
		
		try {
			for(int i=0;i<listafirst.length;i++){
				if(listafirst[i].equals("&")){
					return true;
				}
			}
		}catch(Exception ex) {
			return false;
		}
		return false;
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
	
	public void removeNull() {
		for(String prod:Nterminais){
			String[] temp = first.get(prod);
			ArrayList<String> lista = new ArrayList<String>();
			for(int i=0; i < temp.length;i++){
				if(temp[i]!=null){
					lista.add(temp[i]);
				}
			}
			temp = new String[lista.size()];
			for(int i=0; i < temp.length;i++){
				temp[i] = lista.get(i);
			}
			first.replace(prod,temp);
		}
		
	}
	
	public void removeProducoes() {
		for(String prod:Nterminais){
			String[] temp = first.get(prod);
			ArrayList<String> lista = new ArrayList<String>();
			for(int i=0; i < temp.length;i++){
				if(!(temp[i].length()>1)){
					lista.add(temp[i]);
				}
			}
			temp = new String[lista.size()];
			for(int i=0; i < temp.length;i++){
				temp[i] = lista.get(i);
			}
			first.replace(prod,temp);
		}
		
	}
	
	public void listarFollow(){
		System.out.println("Lista de Follow's:");
		for(String prod:this.producoes){
			String[] temp1 = prod.split("->");
			prod = temp1[0];
			for(String prodDavez:this.producoes){
				String[] temp2 = prodDavez.split("->");
				//System.out.println(prod+" "+temp2[1]);
				if(contem(temp2[1],prod)){
					//System.out.println(prod+" estar em "+temp2[1]);
					String[] temporario = appendFollow(prod,retornaFollow(prod,temp2));
					follow.put(prod,temporario);
				}
			}
			if(prod.equals(start)){
				follow.put(prod, new String[]{"$"});
			}
		}
		
		for(String prod:this.Nterminais){
			System.out.println(Arrays.toString(follow.get(prod)));
		}
	}
	
	private String[] retornaFollow(String chave, String[] producao) {
		for(int i=0; i<producao[1].length();i++){
			if(chave.equals(""+producao[1].charAt(i))){
				if(i==producao[1].length()-1){
					String[] temporario = follow.get(producao[0]);
					return temporario;
				}else {
					if(isTerminal(""+producao[1].charAt(i+1))){
						return new String[]{""+producao[1].charAt(i+1)};
					}else if(isNTerminal(""+producao[1].charAt(i+1))){
						return first.get(""+producao[1].charAt(i+1));
					}
				}
			}
		}
		return null;
	}

	private String[] appendFollow(String chave,String conteudo) {
		boolean controle = false;
		String temporario[] = follow.get(chave);
		if(temporario==null){
			temporario = new String[0];
		}
		String temporario2[] = new String[temporario.length+1];
		for(int i=0;i<temporario2.length-1;i++){
			try {
				if(temporario[i].equals(conteudo)) {
					controle = true;
				}
			}catch(Exception ex) {
				
			}
			temporario2[i] = temporario[i];
		}
		if(controle==false){
			temporario2[temporario2.length-1] = ""+conteudo.charAt(0);
		}
		return temporario2;
	}
	
	private String[] appendFollow(String chave,String[] vetorConteudo) {
		ArrayList<String> retorno = new ArrayList<String>();
		String[] temporario = follow.get(chave);
		if(temporario==null){
			temporario = new String[0];
		}
		String temporario2[] = new String[temporario.length+1];
		if(vetorConteudo != null){
			
			for(String conteudo:vetorConteudo){
				boolean controle = false;			
				if(temporario==null){
					temporario = new String[0];
				}
				temporario2 = new String[temporario.length+1];
				for(int i=0;i<temporario2.length-1;i++){
					try {
						if(temporario[i].equals(conteudo)) {
							controle = true;
						}
					}catch(Exception ex) {
						
					}
					temporario2[i] = temporario[i];
				}
				if(controle==false){
					if(conteudo != null){
						temporario2[temporario2.length-1] = ""+conteudo.charAt(0);
					}
				}
				for(int i=0;i<temporario2.length;i++){
					retorno.add(temporario2[i]);
				}
			}
			temporario2 = new String[retorno.size()];
			for(int i=0;i<temporario2.length;i++){
				temporario2[i] = retorno.get(i);
			}
		}
		return temporario2;
	}

	private boolean contem(String temp2, String prod) {
		for(int i=0;i<temp2.length();i++){
			//System.out.println(prod+" estar em "+temp2.charAt(i)+"?");
			if(prod.equals(""+temp2.charAt(i))){
				//System.out.print("sim.");
				//System.out.println(prod+" estar em "+temp2.charAt(i));
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]){
		
		ArrayList<String> Nterminais = new ArrayList<String>();
		ArrayList<String> terminais = new ArrayList<String>();
		ArrayList<String> producoes = new ArrayList<String>();
		
		Nterminais.add("S");
		Nterminais.add("A");
		Nterminais.add("B");
		//Nterminais.add("Z");
		//Nterminais.add("B");
				
		terminais.add("a");
		terminais.add("b");
		terminais.add("c");
		terminais.add("&");
		//terminais.add("e");
		//terminais.add("f");
		//terminais.add("&");
		
		producoes.add("S->AB");
		//producoes.add("A->y");
		//producoes.add("A->&");
		producoes.add("A->c");
		producoes.add("A->&");
		producoes.add("B->cbB");
		producoes.add("B->ca");
		//producoes.add("B->dD");
		//producoes.add("C->f");
		//producoes.add("Z->f");
		//producoes.add("B->f");
						
		Gramatica g = new Gramatica(Nterminais,terminais,producoes,"A");
		//g.listarGramatica();
		//g.listarPorSimbolo("F");
		//g.listarFirst();
		g.listarFollow();
	}
}

