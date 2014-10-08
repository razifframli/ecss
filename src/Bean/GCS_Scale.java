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
public class GCS_Scale {
    private GCS_Response gCS_Response;
    private String scale_code;
    private String scale_desc;
    private String scale_score;
    private GCS_Month gCS_Month;

    public String getScale_code() {
        return scale_code;
    }

    public void setScale_code(String scale_code) {
        this.scale_code = scale_code;
    }

    public String getScale_desc() {
        return scale_desc;
    }

    public void setScale_desc(String scale_desc) {
        this.scale_desc = scale_desc;
    }

    public String getScale_score() {
        return scale_score;
    }

    public void setScale_score(String scale_score) {
        this.scale_score = scale_score;
    }

    public GCS_Response getgCS_Response() {
        return gCS_Response;
    }

    public void setgCS_Response(GCS_Response gCS_Response) {
        this.gCS_Response = gCS_Response;
    }

    public GCS_Month getgCS_Month() {
        return gCS_Month;
    }

    public void setgCS_Month(GCS_Month gCS_Month) {
        this.gCS_Month = gCS_Month;
    }
}
