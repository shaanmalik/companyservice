package com.experian.company.model;

import java.time.OffsetDateTime;

public class CompanyDto {

    private int msg_id;
    private String company_name;
    private OffsetDateTime registration_date;
    private float score;
    private int directors_count;
    private OffsetDateTime last_updated;

    public int getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(int msg_id) {
        this.msg_id = msg_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public OffsetDateTime getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(OffsetDateTime registration_date) {
        this.registration_date = registration_date;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getDirectors_count() {
        return directors_count;
    }

    public void setDirectors_count(int directors_count) {
        this.directors_count = directors_count;
    }

    public OffsetDateTime getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(OffsetDateTime last_updated) {
        this.last_updated = last_updated;
    }

}
