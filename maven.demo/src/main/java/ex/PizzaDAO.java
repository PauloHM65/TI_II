package ex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PizzaDAO extends DAO {
	
	public PizzaDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Pizza pizza) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO pizzaria (codigo, nome, valor) "
				       + "VALUES ("+pizza.getCodigo()+ ", '" + pizza.getNome() + "', '"  
				       + pizza.getValor() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Pizza get(int codigo) {
		Pizza pizza = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM pizzaria WHERE id=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 pizza = new Pizza(rs.getInt("codigo"), rs.getString("nome"), rs.getFloat("valor"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pizza;
	}
	
	
	public List<Pizza> get() {
		return get("codigo");
	}
	
	
	private List<Pizza> get(String orderBy) {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM pizzaria" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Pizza u = new Pizza(rs.getInt("codigo"), rs.getString("nome"), rs.getFloat("valor"));
	            pizzas.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pizzas;
	}

	
	
	public boolean update(Pizza pizza) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE pizzaria SET nome = '" + pizza.getNome() + "', valor = '" + pizza.getValor() + "'"
					   + " WHERE codigo = " + pizza.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM pizzaria WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}