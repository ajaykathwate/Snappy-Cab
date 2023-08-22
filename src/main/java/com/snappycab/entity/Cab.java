package com.snappycab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cab {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	@Column(name = "cab_id")
	private Integer cabId;
	
	@NotEmpty
	private String cabType;
	
	@Min(6)
	@Max(21)
	private float perKmRate;
	
	@NotNull
	private boolean avalibilityStatus;
	
	@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;

	public Cab(String cabType, float perKmRate) {
		super();
		this.cabType = cabType;
		this.perKmRate = perKmRate;
	}
	
}
