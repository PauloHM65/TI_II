package ex;

import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws Exception {

		PizzaDAO pizzaDAO = new PizzaDAO();
		Scanner scanner = new Scanner(System.in);
		
		
		int resposta = -1;
		while(resposta != 0) {
			System.out.println("\n======= Menu =======\n ");
			System.out.println("==== 1) Inserir ====\n ");
			System.out.println("==== 2) Alterar ====\n ");
			System.out.println("==== 3) Listar  ====\n ");
			System.out.println("==== 4) Excluir ====\n ");
			System.out.println("--------------------\n ");
			System.out.println("==== 0) Sair =======\n");
			
			System.out.println("Opcao:  ");
			resposta = scanner.nextInt();
			
			
			
			
			if(resposta == 1) {
				System.out.println("\n\n==== Inserir usuário === ");
				System.out.println("\nCodigo da pizza:\n ");
				int codigo = scanner.nextInt();
				scanner.nextLine();
				System.out.println("\nNome da pizza:\n ");
				String nomePizza = scanner.nextLine();
				System.out.println("\nValor da pizza:\n ");
				float valor = scanner.nextFloat();
				
				Pizza pizza = new Pizza (codigo, nomePizza, valor);
				if (pizzaDAO.insert(pizza) == true) {
					System.out.println("Inserção com sucesso -> " + pizza.toString());
				}
				
				
			}
			if(resposta == 2) {
				
				System.out.println("\n\n==== Alterando pizza === ");
				System.out.println("\nDigite o codigo da pizza quer vai ser alterada: \n");
				int codigo = scanner.nextInt();
				scanner.nextLine();
				System.out.println("\nDigite o novo nome: \n");
				String nomeNovo = scanner.nextLine();
				System.out.println("\nDigite o novo valor: \n");
				float valorNovo = scanner.nextFloat();
				
				Pizza pizzaAlterada = new Pizza(codigo, nomeNovo, valorNovo);
					pizzaDAO.update(pizzaAlterada);
				
			}
			if(resposta == 3) {
				
				System.out.println("\n\n==== Listando pizza === ");
				List<Pizza> pizzas = pizzaDAO.get();
				for (Pizza u: pizzas) {
					System.out.println(u.toString());
				}
				
			}
			if(resposta == 4) {
				
				
				System.out.println("\n\n==== Excluindo pizza === ");
				System.out.println("\nCodigo da pizza:\n");
				int codigo = scanner.nextInt();
				pizzaDAO.delete(codigo);
	
			}
			if(resposta == 0) {
				System.out.println("\n===== SAINDO DA APLICACAO =====\n");
				pizzaDAO.close();
			}
		}
		
		scanner.close();

	}
}