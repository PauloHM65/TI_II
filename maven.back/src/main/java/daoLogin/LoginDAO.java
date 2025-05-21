package daoLogin;

import java.sql.*;
import daoLogin.DAO.*;
import java.util.ArrayList;
import java.util.List;

import modelLogin.User;


public class LoginDAO extends DAO {
	
	public LoginDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(User user) {
	    boolean status = false;
	    try {  
	        Statement st = conexao.createStatement();
	        String sql = "INSERT INTO usuarios (cnpj, senha, empresa, email) "
	                   + "VALUES ('" + user.getCnpj() + "', '"  
	                   + user.getSenha() + "', '" 
	                   + user.getEmpresa() + "', '" 
	                   + user.getEmail() + "');";
	        System.out.println(sql);
	        st.executeUpdate(sql);
	        st.close();
	        
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}


	
	public User get(String cnpj, String senha) {
	    User user = null;
	    
	    try {
	        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        String sql = "SELECT * FROM usuarios WHERE cnpj = '" + cnpj + "' AND senha = '" + senha + "'";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);	
	        System.out.println(rs);
	        if(rs.next()){            
	            user = new User();
	            user.setCnpj(rs.getString("cnpj"));
	            user.setSenha(rs.getString("senha"));
	            user.setEmpresa(rs.getString("empresa"));
	            user.setEmail(rs.getString("email"));
	        }
	        st.close();
	        rs.close();
	    } catch (Exception e) {
	        System.err.println(e.getMessage());
	    }
	    return user;
	}


}