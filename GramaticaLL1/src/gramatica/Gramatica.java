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
	String[][] tabela;
	public Gramatica(){
		
	}
	
	public Gramatica(ArrayList<String> Nterminais, ArrayList<String> terminais,ArrayList<String> producoes,String start){
		this.Nterminais = Nterminais;
		this.terminais = terminais;
		this.producoes = producoes;
		this.start = start;
		for(String chave:Nterminais){
			first.put(chave, new String[]{});
			follow.put(chave, new String[]{});
		}
		tabela = new String[this.Nterminais.size()][this.terminais.size()+1];
	}
	
	public void addProducao(String producao){
		this.producoes.add(producao);
	}
	
	public String listarGramatica(){
		System.out.println(" - - - - - - GRAMATICA - - - - - - - -");
                String str = "";
		for(String prod:this.producoes){
			System.out.println(prod);
                        str += prod+"\n";
		}
		System.out.println(" - - - - - - - - - - - - - - - - - - -");
                return str;
	}
	
	public void listarPorSimbolo(String simbolo){
		for(String prod:this.producoes){
			String[] temp = prod.split("->");
			if(temp[0].equals(simbolo)){
				System.out.println(prod);
			}
		}
	}
	
	public String listarFirst(){
		
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
                int cont = 0;
		for(String chave:this.Nterminais){
			chave = this.Nterminais.get(this.Nterminais.size()-1-cont);
                        cont++;
                        //System.out.print("first("+chave+"):{");
			String[] listafirst = first.get(chave);
			for(int i=0;i<listafirst.length;i++){
				if(listafirst[i] != null){
					//System.out.println("Chave: "+chave+" ListaFirst: "+Arrays.toString(listafirst)+"Lenght: "+listafirst[i].length());
					if(listafirst[i].length()>1){
						String[] listafirstaux = first.get(""+listafirst[i].charAt(6));
						if(contemVazio(listafirstaux)){//se tiver vazio, add first do proximo
							achafirstComVazio(chave,listafirst[i].substring(6,listafirst[i].length()-1));//procura a produção que gerou first(chave)=first(listafirst[i].charAt(6))
                                                        for (int j = 0; j < listafirstaux.length; j++) {//add os fist sem o vazio
                                                            if (!listafirstaux[j].equals("&")) {
                                                                String[] temporario = append(chave, listafirstaux[j]);
                                                                first.put(chave, temporario);
                                                                //System.out.print(listafirstaux[j]+", ");
                                                            }
                                                        }
                                                }else{
                                                    for (int j = 0; j < listafirstaux.length; j++) {//add os fist.
                                                        String[] temporario = append(chave, listafirstaux[j]);
                                                        first.put(chave, temporario);
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
			
		}
		removeNullFirst();
		removeProducoes();
		System.out.println(" - - - - - - Lista de First's  - - - - - - ");
                String str = "";
		for(String chave:Nterminais) {
			System.out.print("First("+chave+"):");
                        str += "First("+chave+"):";
			System.out.println(Arrays.toString(first.get(chave)));
                        str += Arrays.toString(first.get(chave))+"\n";
		}
		System.out.println(" - - - - - - - - - - - - - - - - - - - - -");
                return str;
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
			if(conteudo != null){
				temporario2[temporario2.length-1] = ""+conteudo.charAt(0);
			}
			
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
	
	public void removeNullFirst() {
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
	
	public void removeNullFollow() {
		for(String prod:Nterminais){
			String[] temp = follow.get(prod);
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
			follow.replace(prod,temp);
		}
		
	}
	
	public void removeProducoes() {
		for(String prod:Nterminais){
			String[] temp = first.get(prod);
			ArrayList<String> lista = new ArrayList<String>();
			for(int i=0; i < temp.length;i++){
				if(!(temp[i].length()>1) && isTerminal(temp[i])){
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
	
	public String listarFollow(){
		
		for(String prod:this.producoes){
			String[] temp1 = prod.split("->");
			prod = temp1[0];
			for(String prodDavez:this.producoes){
				String[] temp2 = prodDavez.split("->");
				//System.out.println(prod+" "+temp2[1]);
				if(contem(temp2[1],prod)){
					//System.out.println(prod+" estar em "+temp2[1]);
					String[] temporario = appendFollow(prod,retornaFollow(prod,temp2));
					//System.out.println("Add Follow("+prod+"):"+Arrays.toString(temporario));
					follow.put(prod,temporario);
				}
			}
			if(prod.equals(start)){
				String[] temporario = follow.get(prod);
				ArrayList<String> aux = new ArrayList<String>();
				for(int i=0; i<temporario.length;i++){
					aux.add(temporario[i]);
				}
				if(!aux.contains("$")){
					aux.add("$");
				}
				temporario = new String[aux.size()];
				for(int i=0; i<temporario.length;i++){
					temporario[i] = aux.get(i);
				}
				follow.put(prod, temporario);
			}
		}
		
		removeNullFollow();
		System.out.println(" - - - - - - Lista de Follow's - - - - - - ");
                String str = "";
		for(String prod:this.Nterminais){
			System.out.println("Follow("+prod+"):"+Arrays.toString(follow.get(prod)));
                        str += "Follow("+prod+"):"+Arrays.toString(follow.get(prod))+"\n";
		}
		System.out.println(" - - - - - - - - - - - - - - - - - - - - -");
                return str;
	}
	
	private String[] retornaFollow(String chave, String[] producao) {
            String[] retorno = new String[0];
           
            for(int i=0; i<producao[1].length();i++){    
                        
			if(chave.equals(""+producao[1].charAt(i))){
				if(i==producao[1].length()-1){
					String[] temporario = follow.get(producao[0]);
					retorno = adicionar(retorno,temporario);
				}else {
					if(isTerminal(""+producao[1].charAt(i+1))){
						//System.out.println("Follow("+chave+"):{"+producao[1].charAt(i+1)+"}");
						retorno = adicionar(retorno,new String[]{""+producao[1].charAt(i+1)});
					}else if(isNTerminal(""+producao[1].charAt(i+1))){
						//System.out.println("Follow("+chave+"):First("+producao[1].charAt(i+1)+")");
                                                String[] temporario = first.get(""+producao[1].charAt(i+1));
                                                ArrayList<String> lista = new ArrayList<String>();//lista para armazenar os first
                                                for(int a=0;a<temporario.length;a++){
                                                    lista.add(temporario[a]);
                                                }
                                                if(lista.contains("&")){//Follow(Beta) = First(B)-{&}UFollow(A)
                                                    String[] temporario2 = new String[temporario.length-1];//First sem o vazio
                                                    for(int a=0;a<temporario.length;a++){
                                                        if(!temporario[a].equals("&")){
                                                            temporario2[a] = temporario[a];
                                                        }
                                                    }
                                                    String[] temporarioFollow = follow.get(""+producao[0]);
                                                    ArrayList<String> listaFinal = new ArrayList<String>();//lista para armazenar os firsts e os follows
                                                    for (int a = 0; a < temporario2.length; a++) {
                                                        listaFinal.add(temporario2[a]);
                                                    }
                                                    for (int a = 0; a < temporarioFollow.length; a++) {
                                                        listaFinal.add(temporarioFollow[a]);
                                                    }
                                                    temporarioFollow = new String[listaFinal.size()];
                                                    for (int a = 0; a < temporarioFollow.length; a++) {
                                                        temporarioFollow[a] = listaFinal.get(a);
                                                    }
                                                    retorno = adicionar(retorno,temporarioFollow);
                                                }else{//Follow(Beta) = First(B)
                                                    retorno = adicionar(retorno,temporario);
                                                }
						
					}
				}
			}
		}
		return retorno;
	}

	private String[] appendFollow(String chave,String conteudo) {
		boolean controle = true;
		String temporario[] = follow.get(chave);
		if(temporario==null){
			temporario = new String[0];
		}
		for(int i = 0;i<temporario.length;i++){
			if(temporario[i].equals(conteudo)){//Não precisa add.
				controle = false;
			}
		}
		String temporario2[];
		
		if(controle==true){//copia tudo q ja tem e add o novo
			temporario2 = new String[temporario.length+1];
			for(int i=0;i<temporario2.length-1;i++){
				temporario2[i] = temporario[i];
			}
			temporario2[temporario2.length-1] = ""+conteudo.charAt(0);
		}else{
			temporario2 = new String[temporario.length];
			for(int i=0;i<temporario2.length;i++){
				temporario2[i] = temporario[i];
			}
			
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
				boolean controle = true;			
				for(int i = 0;i<temporario.length;i++){
					if(temporario[i].equals(conteudo)){//Não precisa add.
						controle = false;
					}
				}
				if(controle==true){//copia tudo q ja tem e add o novo
					temporario2 = new String[temporario.length+1];
					for(int i=0;i<temporario2.length-1;i++){
						temporario2[i] = temporario[i];
					}
					temporario2[temporario2.length-1] = ""+conteudo.charAt(0);
				}else{
					temporario2 = new String[temporario.length];
					for(int i=0;i<temporario2.length;i++){
						temporario2[i] = temporario[i];
					}
					
				}				
				for(int i=0;i<temporario2.length;i++){
					retorno.add(temporario2[i]);
				}
			}
			ArrayList<String> aux = new ArrayList<String>();//Removando valores duplicados
			for(int i=0; i < retorno.size();i++){
				if(!aux.contains(retorno.get(i))){
					aux.add(retorno.get(i));	
				}
				
			}
			temporario2 = new String[aux.size()];
			for(int i=0;i<temporario2.length;i++){
				temporario2[i] = aux.get(i);
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
	
	
	
	public String[][] tabela(){

		for(int i=0;i<tabela.length;i++){
			for(String producao:producoes){
				String[] temp = producao.split("->");
				if(temp[0].equals(Nterminais.get(i))){
					
					ArrayList<String> aux = new ArrayList<String>();
					if(isTerminal(temp[1].charAt(0)+"")){
						aux.add(temp[1].charAt(0)+"");
					}else{
						String[] temporario = first.get(temp[1].charAt(0)+"");
						for(int x=0;x<temporario.length;x++){
							aux.add(temporario[x]);
						}
					}
					if(aux.contains("&")){//First(alfa) tem vazio
						ArrayList<String> auxFollow = new ArrayList<String>();
						System.out.println("Follow("+temp[0]+")");
						String[] temporarioFollow = follow.get(temp[0]);
						for(int x=0;x<temporarioFollow.length;x++){
							auxFollow.add(temporarioFollow[x]);
						}
						if(auxFollow.contains("$")){//First(alfa) tem vazio e Follow(A) tem $
							for(int j=0;j<tabela[i].length-1;j++){
								if(terminais.get(j).equals("$")){
									tabela[i][j] = producao;							
								}				
							}
						}else{//First(alfa) tem vazio e Follow(A) não tem $
							for(int j=0;j<tabela[i].length-1;j++){
								if(auxFollow.contains(terminais.get(j))){
									tabela[i][j] = producao;							
								}				
							}
						}
					}else{//First(alfa) não tem vazio
						for(int j=0;j<tabela[i].length-1;j++){
							if(aux.contains(terminais.get(j))){
								tabela[i][j] = producao;							
							}				
						}
					}					
				}
			}
			System.out.println();
		}
		System.out.println(" - - - - - - - - - - - - - - TABELA  - - - - - - - - - - - - - - ");
		for(String terminal:terminais){
			System.out.print("     "+terminal+"     |   ");
		}
		System.out.println("$      ");
		for(int i=0;i<tabela.length;i++){
			System.out.print(Nterminais.get(i)+"  ");
			for(int j=0;j<tabela[i].length-1;j++){
				System.out.print(tabela[i][j]+"        ");
			}
			System.out.println();
		}
                return this.tabela;
	}

//	public static void main(String args[]){
//		
//		ArrayList<String> Nterminais = new ArrayList<String>();
//		ArrayList<String> terminais = new ArrayList<String>();
//		ArrayList<String> producoes = new ArrayList<String>();
//		
//		Nterminais.add("A");
//		Nterminais.add("B");
//		Nterminais.add("C");
//		
//		terminais.add("+");
//		terminais.add("-");
//		terminais.add("*");
//		terminais.add("/");
//		terminais.add("x");
//		terminais.add("y");
//		terminais.add("z");
//		//terminais.add("&");
//		
//		producoes.add("A->BAA");
//		producoes.add("A->C");
//		producoes.add("B->+");
//		producoes.add("B->-");
//		producoes.add("B->*");
//		producoes.add("B->/");
//		//producoes.add("B->&");
//		producoes.add("C->x");
//		producoes.add("C->y");
//		producoes.add("C->z");
//		
//						
//		Gramatica g = new Gramatica(Nterminais,terminais,producoes,"A");
//		g.listarGramatica();
//		//g.listarPorSimbolo("F");
//		g.listarFirst();
//		g.listarFollow();
//		g.tabela();
//	}

    private String[] adicionar(String[] anterior,String[] novos) {
        ArrayList<String> listaRetorno = new ArrayList<String>();
        for(int i=0;i<anterior.length;i++){
            listaRetorno.add(anterior[i]);
        }
        for(int i=0;i<novos.length;i++){
            if(!listaRetorno.contains(novos[i])){
                listaRetorno.add(novos[i]);
            }
        }
        String[] retorno = new String[listaRetorno.size()];
        for(int i=0;i<retorno.length;i++){
            retorno[i] = listaRetorno.get(i);
        }
        return retorno;
    }
}
