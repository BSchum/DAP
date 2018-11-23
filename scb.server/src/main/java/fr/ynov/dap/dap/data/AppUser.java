package fr.ynov.dap.dap.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AppUser {
	@Id
	@GeneratedValue
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGoogleAccounts(List<GoogleAccount> googleAccounts) {
		this.googleAccounts = googleAccounts;
	}

	public List<GoogleAccount> getAccounts() {
		return googleAccounts;
	}

	String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	List<GoogleAccount> googleAccounts;

	public void adGoogleAccount(GoogleAccount account) {

		account.setOwner(this);

		this.getAccounts().add(account);

	}

}
