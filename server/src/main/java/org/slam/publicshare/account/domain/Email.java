package org.slam.publicshare.account.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Embeddable
@JsonIgnoreProperties({"host", "id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @NotBlank
    @javax.validation.constraints.Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    @Column(name = "email", unique = true, nullable = false)
    private String address;

    private Email(String address) {
        this.address = address.trim().toLowerCase();
    }

    public static Email of(String address) {
        return new Email(address.trim().toLowerCase());
    }

    public String getHost() {
        var index = address.indexOf("@");
        return this.address.substring(index + 1);
    }

    public String getId() {
        var index = address.indexOf("@");
        return this.address.substring(0, index);
    }

}
