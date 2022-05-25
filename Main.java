import java.util.Scanner;

class Main {

  static Scanner console = new Scanner(System.in);

  static final int TOTAL_AVALIACOES = 3;
  static final String[] NOMES_AVALIACOES = { "A1", "A2", "A3" };
  static final double[] NOTA_MAX_AVALIACOES = { 30.00, 30.00, 40.00 };
  
  static double[] notas = new double [TOTAL_AVALIACOES];

  
  /**
	 * Ler uma nota do usu�rio
	 * @param mensagem O texto que aparecer� na tela
	 * @return um n�mero double representando a nota.
	 */
	static double lerNota(String mensagem, double notaMaxima) {

      double nota = 0.0;

      do {

        System.out.printf("%s = ", mensagem);
        nota = console.nextDouble();
        
      } while (nota < 0.00 || nota > notaMaxima);

    return nota;
	}

  
  /**
  * Atualiza o valor da respectiva nota do estudante
  * @param indiceNota um n�mero inteiro representando o �ndice (posi��o) da nota no vetor
  */
  static void atualizarNota(int indiceNota) {

    System.out.println();
    notas[indiceNota] = lerNota(NOMES_AVALIACOES[indiceNota], NOTA_MAX_AVALIACOES[indiceNota]);
  
  } // Fim do m�todo atualizarNota

  
 /**
  * @param notaFinal A soma de todas as avali��es feita pelo estudante ao longo do semestre
  * @return uma string representando o status final do estudante, s�o eles: APROVADO, REPROVADO, EM RECUPERA��O.
  */
  static String avaliarSituacao(double notaFinal) {

    if(notaFinal < 30)
      return "REPROVADO";
    else if (notaFinal < 70)
      return "EM RECUPERA��O";
    else
      return "APROVADO";
    
  } // Fim do m�todo avaliarSituacao()
  

  static double calcularMedia(double[] notas){
	  	double media = 0;
	  	
	  	for(int i = 0; i < TOTAL_AVALIACOES; i++){
	  		media += notas[i];
	  	}
	  	
	  	media /= TOTAL_AVALIACOES;
	  	return media;
  }
  
  	static String maiorNota (double[] notas){
  		String melhorAV = "";
  		double verificarMaior = 0;
  		
  		for (int i = 0; i < TOTAL_AVALIACOES; i++){
  			if (notas [i] > verificarMaior){
  				verificarMaior = notas [i];
  				melhorAV = NOMES_AVALIACOES[i];
  			}
  		}
  		
  		return melhorAV;
  	}
  	
  	static void provaAI(double[] notas) {
        double notaAi = 0;
        int piorProvaIndex = notas[0] <= notas[1] ? 0 : 1; //Verifica a menor entre as 2 primeira notas.


        System.out.println("Digite A Nota Da Prova AI");
        notaAi = lerNota("Prova AI", NOTA_MAX_AVALIACOES[piorProvaIndex]);

        if (notaAi > notas[piorProvaIndex]) {
            notas[piorProvaIndex] = notaAi;
            System.out.printf("A Avalia��o %s Foi Substituida Pela Avali��o AI\n", NOMES_AVALIACOES[piorProvaIndex]);
        } else {
            System.out.println("A Nota Da Prova AI N�o Foi Suficiente Para Alterar Alguma Prova");
        }

    } // Fim do m�todo provaAI()

    /**
     * Verifica se deseja fazer a prova AI
     */
    static void opcaoProvaAI(double[] notas) {

        if (calcularMedia(notas) <= 23.0 && calcularMedia(notas) >= 10.0 && (notas[0] + notas[1]) < 60) {
            System.out.print("""
                    Deseja Cadastrar Nota Prova AI?
                    Digite SIM ou N�O:\s""");
            String opcao = console.next();

            boolean checkOpcao = false;
            while (!checkOpcao) {
                if (opcao.equalsIgnoreCase("sim")) {
                    provaAI(notas);
                    mostrarNotas();
                    checkOpcao = true;
                } else if (opcao.equalsIgnoreCase("n�o") || opcao.equalsIgnoreCase("nao")) {
                    System.out.println("Op��o Digitada: N�o");
                    checkOpcao = true;
                } else {
                    System.out.println("Op��o Invalida");
                    System.out.print("Digite SIM ou N�O: ");
                    opcao = console.next();
                }
            }

        }

    } // Fim do m�todo opcaoProvaAI()
  /**
  * Mostra na tela um relat�rio das notas do estudante
  */
  static void mostrarNotas() {

    double notaFinal = 0.0;

    System.out.println("\n\t\tNOTAS");
    System.out.println();

    for (int i = 0; i < TOTAL_AVALIACOES; i++) {

      System.out.printf("Avalia��o %s = %.2f pts", NOMES_AVALIACOES[i], notas[i]);
      System.out.println();
      notaFinal += notas[i];
    
    }

    System.out.printf("\n Nota Final = %.2f pts", notaFinal);
    System.out.printf("\n Situa��o = %s", avaliarSituacao(notaFinal));
    System.out.printf("\n Media geral = %.2f", calcularMedia(notas));
    System.out.printf("\n Melhor prova = %s", maiorNota(notas));	
   

  } // Fim do m�todo mostrarNotas()

  
 /**
  * Exibe o menu principal da aplica��o
  */
  static void mostrarMenu() {

    System.out.println("\n\n");
    System.out.println("\t\tMENU");
    System.out.println();
    
    System.out.println("[1] Cadastrar Notas A1");
    System.out.println("[2] Cadastrar Nota A2");
    System.out.println("[3] Cadastrar Nota A3");
    System.out.println("[4] Mostrar Notas");
    System.out.println("[0] SAIR");

    System.out.print("\nDigite uma op��o:  ");
    byte opcao = console.nextByte();


    switch(opcao) {

      case 0:
        System.exit(0);
        break;
      
      case 1:
        atualizarNota(0);
        break;
      
      case 2:
        atualizarNota(1);
        break;

      case 3:
        atualizarNota(2);
        break;

      case 4:
        mostrarNotas();
        opcaoProvaAI(notas);
        break;

      default:
        mostrarMenu();
        break;

    }

    mostrarMenu();

  } // Fim do m�todo mostrarMenu()

  
  public static void main(String[] args) {
    
    mostrarMenu();
  
  } // Fim do m�todo main();
  
} // Fim da classe Main
