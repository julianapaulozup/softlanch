package softLaunch.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "Clientes_Autorizados")
public class WhiteList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(name = "Nome")
    private String name;
    @NotEmpty
    @Column(name = "CPF",unique = true)
    private String cpf;

    public WhiteList(){

    }

    public WhiteList(String name, String cpf) {
         this.name = name;
         this.cpf = cpf;
    }

    public WhiteList(Long id,String name, String cpf) {
        this.id =id;
        this.name = name;
        this.cpf = cpf;
    }
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
