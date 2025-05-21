package loginApplication;

import static spark.Spark.*;
import serviceLogin.*;
import com.google.gson.Gson;

public class loginAplication {
	
	private static loginService loginService = new loginService();
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
	    port(6789);

	    staticFiles.location("/public");
	    System.out.println(">>> Servidor iniciado...");


	    post("/teste", (req, res) -> {
	        System.out.println(">>> POST /teste chamado");
	        res.type("application/json");
	        return "{\"message\":\"rota de teste funcionando\"}";
	    });
	    
	    get("/user/:cnpj/:senha", (request, response) -> loginService.get(request, response), gson::toJson);
	    post("/user/insert", (request, response) -> loginService.insert(request, response), gson::toJson);

	    
	}


}