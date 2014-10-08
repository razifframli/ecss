/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Bean;

/**
 *
 * @author End User
 */
public class PhysicalExamBean {
    private String pe_cd;
    private String pe_name;
    private String pe_parent;
    private String pe_status;

    public String getPe_cd() {
        return pe_cd;
    }

    public void setPe_cd(String pe_cd) {
        this.pe_cd = pe_cd;
    }

    public String getPe_name() {
        return pe_name;
    }

    public void setPe_name(String pe_name) {
        this.pe_name = pe_name;
    }

    public String getPe_parent() {
        return pe_parent;
    }

    public void setPe_parent(String pe_parent) {
        this.pe_parent = pe_parent;
    }

    public String getPe_status() {
        return pe_status;
    }

    public void setPe_status(String pe_status) {
        this.pe_status = pe_status;
    }
}
