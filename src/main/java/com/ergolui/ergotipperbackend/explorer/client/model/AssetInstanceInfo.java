/*
 * Ergo Explorer API v1
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.ergolui.ergotipperbackend.explorer.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AssetInstanceInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-05T14:07:15.709Z[GMT]")
public class AssetInstanceInfo {
  @SerializedName("tokenId")
  private String tokenId = null;

  @SerializedName("index")
  private Integer index = null;

  @SerializedName("amount")
  private Long amount = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("decimals")
  private Integer decimals = null;

  @SerializedName("type")
  private String type = null;

  public AssetInstanceInfo tokenId(String tokenId) {
    this.tokenId = tokenId;
    return this;
  }

   /**
   * Token ID
   * @return tokenId
  **/
  @Schema(required = true, description = "Token ID")
  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public AssetInstanceInfo index(Integer index) {
    this.index = index;
    return this;
  }

   /**
   * Index of the asset in an output
   * @return index
  **/
  @Schema(required = true, description = "Index of the asset in an output")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public AssetInstanceInfo amount(Long amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Amount of tokens
   * @return amount
  **/
  @Schema(required = true, description = "Amount of tokens")
  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public AssetInstanceInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of a token
   * @return name
  **/
  @Schema(description = "Name of a token")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AssetInstanceInfo decimals(Integer decimals) {
    this.decimals = decimals;
    return this;
  }

   /**
   * Number of decimal places
   * @return decimals
  **/
  @Schema(description = "Number of decimal places")
  public Integer getDecimals() {
    return decimals;
  }

  public void setDecimals(Integer decimals) {
    this.decimals = decimals;
  }

  public AssetInstanceInfo type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Type of a token (token standard)
   * @return type
  **/
  @Schema(description = "Type of a token (token standard)")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssetInstanceInfo assetInstanceInfo = (AssetInstanceInfo) o;
    return Objects.equals(this.tokenId, assetInstanceInfo.tokenId) &&
        Objects.equals(this.index, assetInstanceInfo.index) &&
        Objects.equals(this.amount, assetInstanceInfo.amount) &&
        Objects.equals(this.name, assetInstanceInfo.name) &&
        Objects.equals(this.decimals, assetInstanceInfo.decimals) &&
        Objects.equals(this.type, assetInstanceInfo.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenId, index, amount, name, decimals, type);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssetInstanceInfo {\n");
    
    sb.append("    tokenId: ").append(toIndentedString(tokenId)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    decimals: ").append(toIndentedString(decimals)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
