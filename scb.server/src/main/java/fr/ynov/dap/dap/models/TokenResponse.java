package fr.ynov.dap.dap.models;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.ynov.dap.dap.data.OutlookAccount;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
	
  @Id
  @GeneratedValue
  private int id;
  @JsonProperty("token_type")
  private String tokenType;
  private String scope;
  @JsonProperty("expires_in")
  private int expiresIn;
  @JsonProperty("access_token")
  @Column(length = 5000)
  private String accessToken;
  @JsonProperty("refresh_token")
  @Column(length = 5000)
  private String refreshToken;
  @JsonProperty("id_token")
  @Column(length = 5000)
  private String idToken;
  private String error;
  @JsonProperty("error_description")
  private String errorDescription;
  @JsonProperty("error_codes")
  private int[] errorCodes;
  private Date expirationTime;
  
  private String tenantID;
  
  @OneToOne
  private OutlookAccount owner;
  
  
  
  public OutlookAccount getOwner() {
    return owner;
  }
  public void setOwner(OutlookAccount owner) {
    this.owner = owner;
  }

  
  public String getTokenType() {
    return tokenType;
  }
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }
  public String getScope() {
    return scope;
  }
  public void setScope(String scope) {
    this.scope = scope;
  }
  public int getExpiresIn() {
    return expiresIn;
  }
  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
    Calendar now = Calendar.getInstance();
    now.add(Calendar.SECOND, expiresIn);
    this.expirationTime = now.getTime();
  }
  public String getAccessToken() {
    return accessToken;
  }
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  public String getRefreshToken() {
    return refreshToken;
  }
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
  public String getIdToken() {
    return idToken;
  }
  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }
  
  public String getTenantID() {
	    return tenantID;
  }
  public void setTenantID(String tenantID) {
    this.tenantID = tenantID;
  }
	  
  public String getError() {
    return error;
  }
  public void setError(String error) {
    this.error = error;
  }
  public String getErrorDescription() {
    return errorDescription;
  }
  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }
  public int[] getErrorCodes() {
    return errorCodes;
  }
  public void setErrorCodes(int[] errorCodes) {
    this.errorCodes = errorCodes;
  }
  public Date getExpirationTime() {
    return expirationTime;
  }
}