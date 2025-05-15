package app;

import static spark.Spark.*;
import service.*;
import com.google.gson.Gson;

public class Aplicacao {
	
	private static pizzaService pizzaService = new pizzaService();
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
	    port(6789);

	    staticFiles.location("/public");
	    
	    get("/pizza/list/:orderby", (request, response) -> pizzaService.getAll(request, response), gson::toJson);
	    post("/pizza/insert", (request, response) -> pizzaService.insert(request, response));
	    get("/pizza/update/:id", (request, response) -> pizzaService.getToUpdate(request, response));
	    post("/pizza/update/:id", (request, response) -> pizzaService.update(request, response));
	    get("/pizza/delete/:id", (request, response) -> pizzaService.delete(request, response));
	    get("/pizza/:id", (request, response) -> pizzaService.get(request, response));

	    
	}


}