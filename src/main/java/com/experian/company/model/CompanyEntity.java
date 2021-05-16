package com.experian.company.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class CompanyEntity {

    private @Id Long msg_id;
    private String company_name;
    private OffsetDateTime registration_date;
    private float score;
    private int directors_count;
    private OffsetDateTime last_updated;

    public Long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Long msg_id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return Float.compare(that.score, score) == 0 &&
                directors_count == that.directors_count &&
                Objects.equals(msg_id, that.msg_id) &&
                Objects.equals(company_name, that.company_name) &&
                Objects.equals(registration_date, that.registration_date) &&
                Objects.equals(last_updated, that.last_updated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(msg_id, company_name, registration_date, score, directors_count, last_updated);
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "msg_id=" + msg_id +
                ", company_name='" + company_name + '\'' +
                ", registration_date=" + registration_date +
                ", score=" + score +
                ", directors_count=" + directors_count +
                ", last_updated=" + last_updated +
                '}';
    }
}
