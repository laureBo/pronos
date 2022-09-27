package com.bet.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Participer")
@Table(name = "PARTICIPER")
@Getter
@Setter
public class ParticiperEntity {

	@EmbeddedId
	private ParticiperIdEntity participerId;

}
