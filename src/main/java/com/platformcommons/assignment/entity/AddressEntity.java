package com.platformcommons.assignment.entity;

import com.platformcommons.assignment.model.AddressDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressType;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_code",
            referencedColumnName = "student_code"
    )
    private StudentEntity student;
    
    public AddressDTO toModel() {

        return AddressDTO.builder()
                .addressType(this.addressType)
                .addressLine1(this.addressLine1)
                .addressLine2(this.addressLine2)
                .city(this.city)
                .state(this.state)
                .country(this.country)
                .pinCode(this.pinCode)
                .build();
    }

    public static AddressEntity fromModel(
            AddressDTO dto) {

        return AddressEntity.builder()
                .addressType(dto.getAddressType())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .pinCode(dto.getPinCode())
                .build();
    }
}
