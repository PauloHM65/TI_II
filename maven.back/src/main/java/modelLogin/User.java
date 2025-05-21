package modelLogin;

import java.util.Objects;

public class User {

    private String cnpj;
    private String senha;
    private String empresa;
    private String email;

    public User() {
        this.cnpj = "";
        this.senha = "";
        this.empresa = "";
        this.email = "";
    }

    public User(String cnpj, String senha, String empresa, String email) {
        this.cnpj = cnpj;
        this.senha = senha;
        this.empresa = empresa;
        this.email = email;
    }

    // Getters e Setters

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString

    @Override
    public String toString() {
        return "User [cnpj=" + cnpj + ", senha=" + senha + ", empresa=" + empresa + ", email=" + email + "]";
    }

    // equals e hashCode (baseados no cnpj, que identifica o usuário)

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(cnpj, other.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    // toJson simples (string JSON válida)

    public String toJson() {
        return "{" +
                "\"cnpj\": \"" + cnpj + "\"," +
                "\"senha\": \"" + senha + "\"," +
                "\"empresa\": \"" + empresa + "\"," +
                "\"email\": \"" + email + "\"" +
                "}";
    }
}
