package com.brijframwork.authorization.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brijframwork.authorization.model.menus.EORoleMenuItem;

@Entity
@Table(name = "USER_ROLE")
public class EOUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "POSITION")
	private int position;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "ROLE_ID")
	private String roleId;
	
	@Column(name = "ROLE_TYPE")
	private String roleType;

	@OneToMany(mappedBy = "userRole")
	private List<EOUserAccount> userAccounts;
	
	@OneToMany(mappedBy = "userRole")
	private List<EORoleMenuItem> roleEndpoints;
	
	public EOUserRole() {
	}

	public EOUserRole(int position, String roleName, String roleId) {
		super();
		this.position = position;
		this.roleName = roleName;
		this.roleId = roleId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleID) {
		this.roleId = roleID;
	}
	
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<EOUserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(List<EOUserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public List<EORoleMenuItem> getRoleEndpoints() {
		return roleEndpoints;
	}

	public void setRoleEndpoints(List<EORoleMenuItem> roleEndpoints) {
		this.roleEndpoints = roleEndpoints;
	}

	@Override
	public String toString() {
		return "EOUserRole [id=" + id + ", position=" + position + ", roleName=" + roleName + ", roleID=" + roleId;
	}

	
}
