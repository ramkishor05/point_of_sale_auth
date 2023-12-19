package com.brijframwork.authorization.model.header;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.brijframwork.authorization.model.EOUserRole;

@Entity
@Table(name = "ROLE_HEADER_ITEM", uniqueConstraints= {@UniqueConstraint(columnNames = { "USER_ROLE_ID","HEADER_ITEM_ID" })})
public class EORoleHeaderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "IDEN_NO")
	private String idenNo;
	
	@Column(name = "OWNER_ID", nullable = true)
	private Long ownerId;

	@OneToOne
	@JoinColumn(name = "USER_ROLE_ID")
	private EOUserRole userRole;
	
	@OneToOne
	@JoinColumn(name = "HEADER_ITEM_ID")
	private EOHeaderItem headerItem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdenNo() {
		return idenNo;
	}

	public void setIdenNo(String idenNo) {
		this.idenNo = idenNo;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public EOUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(EOUserRole userRole) {
		this.userRole = userRole;
	}

	public EOHeaderItem getHeaderItem() {
		return headerItem;
	}

	public void setHeaderItem(EOHeaderItem headerItem) {
		this.headerItem = headerItem;
	}
}
