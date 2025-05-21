package serviceLogin;

import java.util.List;
import daoLogin.LoginDAO;
import modelLogin.User;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;

public class loginService {

    private LoginDAO loginDAO = new LoginDAO();
    User user;

    public Object insert(Request request, Response response) {
        try {
            Gson gson = new Gson();
            
            // Lê o corpo JSON e converte para User
            User user = gson.fromJson(request.body(), User.class);

            if (loginDAO.insert(user)) {
                response.status(201); // Created
                response.type("application/json");
                return new MessageResponse("Usuário inserido com sucesso!");
            } else {
                response.status(500); // Internal Server Error
                response.type("application/json");
                return new MessageResponse("Erro ao inserir usuário.");
            }
        } catch (Exception e) {
            response.status(400); // Bad Request
            response.type("application/json");
            return new MessageResponse("Erro nos parâmetros: " + e.getMessage());
        }
    }



    public Object get(Request request, Response response) {
        try {
            String cnpj = request.params(":cnpj");
            String senha = request.params(":senha");
            User user = loginDAO.get(cnpj, senha);

            if (user != null) {
                response.type("application/json");
                return user;  // Gson vai converter pra JSON automaticamente
            } else {
                response.status(404);
                response.type("application/json");
                return new MessageResponse("Usuario não encontrado");
            }
        } catch (Exception e) {
            response.status(400);
            response.type("application/json");
            return new MessageResponse("Erro na busca: " + e.getMessage());
        }
    }

    // Classe auxiliar para erros
    public class MessageResponse {
        private String mensagem;

        public MessageResponse(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }



}
