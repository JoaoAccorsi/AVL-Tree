import java.util.Scanner;

public class Main {

      // Texto impresso colorido
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        ArvoreAVL arvore = new ArvoreAVL();
        String opcao = "";
        String estadoSwitch = "OFF";
        int inteiro = 0;

        //------------------------------------------------------//
        // Menu de opções usuário

        while (!opcao.equals("6")) {

            System.out.println(ANSI_PURPLE +  "++-----------------------------------------------+\n" +
                    "[i] Inserir\n" +
                    "[b] Buscar\n" +
                    "[r] Remover\n" +
                    "[l] Logs Inserção " + estadoSwitch + "\n" +
                    "[1] Exibir Árvore --> Estrutura\n" +
                    "[2] Exibir Árvore --> Pré-Ordem\n" +
                    "[3] Exibir Árvore --> Pós-Ordem\n" +
                    "[4] Exibir Árvore --> Em-Ordem\n" +
                    "[5] Exibir Fatores de Balancemento\n" +
                    "[6] Sair" + "\n++-----------------------------------------------+" + ANSI_RESET);

            try {

                System.out.printf("\nDigite a opção que deseja executar: ");
                opcao = teclado.nextLine();

                switch (opcao) {
                    case "i":
                        System.out.printf("Digite um inteiro: ");
                        inteiro = teclado.nextInt();
                        teclado.nextLine();
                        pulaLinhas();
                        arvore.inserir(inteiro);
                        arvore.exibeArvore(arvore.noRaiz, 0);
                        System.out.print("\nÁrvore após inserção Em-Ordem: ");
                        arvore.exibeEmOrdem();
                        while (true) {
                            System.out.println(ANSI_PURPLE + "Aperte ENTER para voltar ao menu." + ANSI_RESET);
                            teclado.nextLine();
                            break;
                        }
                        break;
                    case "b":
                        System.out.printf("Digite um inteiro: ");
                        inteiro = teclado.nextInt();
                        teclado.nextLine();
                        arvore.buscar(inteiro);
                        break;
                    case "r":
                        System.out.printf("Digite um inteiro: ");
                        inteiro = teclado.nextInt();
                        teclado.nextLine();
                        arvore.remover(inteiro);

                        System.out.print("\nÁrvore após remoção Em-Ordem: ");
                        arvore.exibeEmOrdem();
                        break;
                    case "l":
                        estadoSwitch = arvore.switchLogs ? "OFF" : "ON";
                        arvore.switchLogs = arvore.switchLogs ? false : true;
                        break;
                    case "1":
                        pulaLinhas();
                        arvore.exibeArvore(arvore.noRaiz, 0);
                        break;
                    case "2":
                        pulaLinhas();
                        arvore.exibePreOrdem();
                        break;
                    case "3":
                        pulaLinhas();
                        arvore.exibePosOrdem();
                        break;
                    case "4":
                        pulaLinhas();
                        arvore.exibeEmOrdem();
                        break;
                    case "5":
                        pulaLinhas();
                        arvore.exibeFatorBalanceamento();
                        break;
                    case "6":
                        break;
                    default:
                        System.out.println("\nOpção inválida!");
                }
            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("Opção inválida!");
            }
            System.out.println();
        }
    }
      
    public static void pulaLinhas() {
        for (int i = 0; i < 2; i++)
            System.out.println();
    }
}
