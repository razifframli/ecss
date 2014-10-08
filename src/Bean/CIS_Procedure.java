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
public class CIS_Procedure {
    private String procedure_cd;
    private String procedure_name;
    private String procedure_parent;
    private String status;

    public String getProcedure_cd() {
        return procedure_cd;
    }

    public void setProcedure_cd(String procedure_cd) {
        this.procedure_cd = procedure_cd;
    }

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcedure_parent() {
        return procedure_parent;
    }

    public void setProcedure_parent(String procedure_parent) {
        this.procedure_parent = procedure_parent;
    }
}
