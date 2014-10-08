/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "PATIENTHISTORY", catalog = "PUBLIC", schema = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "Patienthistory.findAll", query = "SELECT p FROM Patienthistory p"),
    @NamedQuery(name = "Patienthistory.findByHistoryId", query = "SELECT p FROM Patienthistory p WHERE p.historyId = :historyId"),
    @NamedQuery(name = "Patienthistory.findByBiodataId", query = "SELECT p FROM Patienthistory p WHERE p.biodataId = :biodataId"),
    @NamedQuery(name = "Patienthistory.findByDate", query = "SELECT p FROM Patienthistory p WHERE p.date = :date"),
    @NamedQuery(name = "Patienthistory.findByProblem", query = "SELECT p FROM Patienthistory p WHERE p.problem = :problem"),
    @NamedQuery(name = "Patienthistory.findByPlace", query = "SELECT p FROM Patienthistory p WHERE p.place = :place"),
    @NamedQuery(name = "Patienthistory.findByNextAppointmentDate", query = "SELECT p FROM Patienthistory p WHERE p.nextAppointmentDate = :nextAppointmentDate"),
    @NamedQuery(name = "Patienthistory.findByCategory", query = "SELECT p FROM Patienthistory p WHERE p.category = :category")})
    public class Patienthistory implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HISTORY_ID")
    private Integer historyId;
    @Column(name = "BIODATA_ID")
    private String biodataId;
    @Column(name = "DATE")
    private String date;
    @Column(name = "PROBLEM")
    private String problem;
    @Column(name = "PLACE")
    private String place;
    @Column(name = "NEXT_APPOINTMENT_DATE")
    private String nextAppointmentDate;
    @Column(name = "CATEGORY")
    private String category;

    public Patienthistory() {
    }

    public Patienthistory(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        Integer oldHistoryId = this.historyId;
        this.historyId = historyId;
        changeSupport.firePropertyChange("historyId", oldHistoryId, historyId);
    }

    public String getBiodataId() {
        return biodataId;
    }

    public void setBiodataId(String biodataId) {
        String oldBiodataId = this.biodataId;
        this.biodataId = biodataId;
        changeSupport.firePropertyChange("biodataId", oldBiodataId, biodataId);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        String oldDate = this.date;
        this.date = date;
        changeSupport.firePropertyChange("date", oldDate, date);
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        String oldProblem = this.problem;
        this.problem = problem;
        changeSupport.firePropertyChange("problem", oldProblem, problem);
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        String oldPlace = this.place;
        this.place = place;
        changeSupport.firePropertyChange("place", oldPlace, place);
    }

    public String getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public void setNextAppointmentDate(String nextAppointmentDate) {
        String oldNextAppointmentDate = this.nextAppointmentDate;
        this.nextAppointmentDate = nextAppointmentDate;
        changeSupport.firePropertyChange("nextAppointmentDate", oldNextAppointmentDate, nextAppointmentDate);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        String oldCategory = this.category;
        this.category = category;
        changeSupport.firePropertyChange("category", oldCategory, category);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historyId != null ? historyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patienthistory)) {
            return false;
        }
        Patienthistory other = (Patienthistory) object;
        if ((this.historyId == null && other.historyId != null) || (this.historyId != null && !this.historyId.equals(other.historyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.Patienthistory[historyId=" + historyId + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
