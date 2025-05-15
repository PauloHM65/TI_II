package service;

import java.util.List;
import dao.PizzaDAO;
import model.Pizza;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;

public class pizzaService {

    private PizzaDAO pizzaDAO = new PizzaDAO();

    public Object insert(Request request, Response response) {
        try {
        	
            int codigo = Integer.parseInt(request.queryParams(":codigo"));
            String nome = request.queryParams(":nome");
            double valor = Double.parseDouble(request.queryParams(":valor"));

            Pizza pizza = new Pizza(codigo, nome, valor);

            if (pizzaDAO.insert(pizza)) {
                response.status(201); // Created
                return "Pizza inserida com sucesso!";
            } else {
                response.status(500); // Internal Server Error
                return "Erro ao inserir pizza.";
            }
        } catch (Exception e) {
            response.status(400); // Bad Request
            return "Erro nos parâmetros: " + e.getMessage();
        }
    }

    public Object get(Request request, Response response) {
        try {
            int codigo = Integer.parseInt(request.params(":id"));
            Pizza pizza = pizzaDAO.get(codigo);

            if (pizza != null) {
                response.type("application/json");
                return pizza.toJson();
            } else {
                response.status(404);
                return "Pizza não encontrada.";
            }
        } catch (Exception e) {
            response.status(400);
            return "Erro na busca: " + e.getMessage();
        }
    }

    public Object getAll(Request request, Response response) {
        try {
            String orderBy = request.params(":orderby");
            System.out.println("Ordenando por: " + orderBy);

            List<Pizza> pizzas = pizzaDAO.get(orderBy);

            response.type("application/json");
            return pizzas; // deixe o Spark + Gson cuidar da conversão
        } catch (Exception e) {
            response.status(400);
            return "Erro ao listar: " + e.getMessage();
        }
    }

    public Object getToUpdate(Request request, Response response) {
        return get(request, response); // Mesma lógica do get
    }

    public Object update(Request request, Response response) {
        try {
            int codigo = Integer.parseInt(request.params(":id"));
            Gson gson = new Gson();
            Pizza pizzaBody = gson.fromJson(request.body(), Pizza.class);
            Pizza pizza = new Pizza(codigo, pizzaBody.getNome(), pizzaBody.getValor());
            if (pizzaDAO.update(pizza)) {
                return "Pizza atualizada com sucesso!";
            } else {
                response.status(404);
                return "Pizza não encontrada para atualização.";
            }
        } catch (Exception e) {
            response.status(400);
            return "Erro na atualização: " + e.getMessage();
        }
    }

    public Object delete(Request request, Response response) {
        try {
            String codigo = request.params(":id");

            if (pizzaDAO.delete(codigo)) {
                return "Pizza deletada com sucesso!";
            } else {
                response.status(404);
                return "Pizza não encontrada para deleção.";
            }
        } catch (Exception e) {
            response.status(400);
            return "Erro na deleção: " + e.getMessage();
        }
    }
}
