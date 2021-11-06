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

import java.util.ArrayList;
import java.util.List;
/**
 * BoxAssetsQuery
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-05T14:07:15.709Z[GMT]")
public class BoxAssetsQuery {
  @SerializedName("ergoTreeTemplateHash")
  private String ergoTreeTemplateHash = null;

  @SerializedName("assets")
  private List<String> assets = null;

  public BoxAssetsQuery ergoTreeTemplateHash(String ergoTreeTemplateHash) {
    this.ergoTreeTemplateHash = ergoTreeTemplateHash;
    return this;
  }

   /**
   * SHA-256 hash of ErgoTree template this box script should have
   * @return ergoTreeTemplateHash
  **/
  @Schema(required = true, description = "SHA-256 hash of ErgoTree template this box script should have")
  public String getErgoTreeTemplateHash() {
    return ergoTreeTemplateHash;
  }

  public void setErgoTreeTemplateHash(String ergoTreeTemplateHash) {
    this.ergoTreeTemplateHash = ergoTreeTemplateHash;
  }

  public BoxAssetsQuery assets(List<String> assets) {
    this.assets = assets;
    return this;
  }

  public BoxAssetsQuery addAssetsItem(String assetsItem) {
    if (this.assets == null) {
      this.assets = new ArrayList<String>();
    }
    this.assets.add(assetsItem);
    return this;
  }

   /**
   * IDs of tokens returned boxes should contain
   * @return assets
  **/
  @Schema(description = "IDs of tokens returned boxes should contain")
  public List<String> getAssets() {
    return assets;
  }

  public void setAssets(List<String> assets) {
    this.assets = assets;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoxAssetsQuery boxAssetsQuery = (BoxAssetsQuery) o;
    return Objects.equals(this.ergoTreeTemplateHash, boxAssetsQuery.ergoTreeTemplateHash) &&
        Objects.equals(this.assets, boxAssetsQuery.assets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ergoTreeTemplateHash, assets);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoxAssetsQuery {\n");
    
    sb.append("    ergoTreeTemplateHash: ").append(toIndentedString(ergoTreeTemplateHash)).append("\n");
    sb.append("    assets: ").append(toIndentedString(assets)).append("\n");
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
