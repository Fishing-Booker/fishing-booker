package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "complaint_response")
public class ComplaintResponse {
    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "complaintResponseSeqGen", sequenceName = "complaintResponseSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaintResponseSeqGen")
    private Integer id;

    @Column(name = "text")
    private String response;

    @ManyToOne(targetEntity = Complaint.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    //@JoinColumn(name = "complaint_id", referencedColumnName = "id")
    private Complaint complaint;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Integer id, String response, Complaint complaint) {
        this.id = id;
        this.response = response;
        this.complaint = complaint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
