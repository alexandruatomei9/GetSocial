package info.getsocial.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEntity {
	/**
	 * Update date
	 */
	private Timestamp updatedAt;
	/**
	 * Creation date
	 */
	private Timestamp createdAt;
	
	/**
	 * @return the updatedAt
	 */
	@Column(name = "updated_at", insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	Timestamp getDateMajTech() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the createdAt
	 */
	@Column(name = "created_at", insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	void onCreate() {
		this.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
		this.setUpdatedAt(this.getCreatedAt());
	}

	@PreUpdate
	void onPersist() {
		this.setUpdatedAt(new Timestamp(new java.util.Date().getTime()));
	}
}
