package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Bar entity.
 */
public class BarDTO implements Serializable {

    private Long id;

    private Integer bar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getBar() {
        return bar;
    }

    public void setBar(Integer bar) {
        this.bar = bar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BarDTO barDTO = (BarDTO) o;

        if ( ! Objects.equals(id, barDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BarDTO{" +
            "id=" + id +
            ", bar='" + bar + "'" +
            '}';
    }
}
