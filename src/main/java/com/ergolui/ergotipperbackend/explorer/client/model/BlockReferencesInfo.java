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
 * References to previous and next (if exists) blocks
 */
@Schema(description = "References to previous and next (if exists) blocks")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-05T14:07:15.709Z[GMT]")
public class BlockReferencesInfo {
  @SerializedName("previousId")
  private String previousId = null;

  @SerializedName("nextId")
  private String nextId = null;

  public BlockReferencesInfo previousId(String previousId) {
    this.previousId = previousId;
    return this;
  }

   /**
   * ID of the previous block
   * @return previousId
  **/
  @Schema(required = true, description = "ID of the previous block")
  public String getPreviousId() {
    return previousId;
  }

  public void setPreviousId(String previousId) {
    this.previousId = previousId;
  }

  public BlockReferencesInfo nextId(String nextId) {
    this.nextId = nextId;
    return this;
  }

   /**
   * ID of the next block (if one exists)
   * @return nextId
  **/
  @Schema(description = "ID of the next block (if one exists)")
  public String getNextId() {
    return nextId;
  }

  public void setNextId(String nextId) {
    this.nextId = nextId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlockReferencesInfo blockReferencesInfo = (BlockReferencesInfo) o;
    return Objects.equals(this.previousId, blockReferencesInfo.previousId) &&
        Objects.equals(this.nextId, blockReferencesInfo.nextId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(previousId, nextId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlockReferencesInfo {\n");
    
    sb.append("    previousId: ").append(toIndentedString(previousId)).append("\n");
    sb.append("    nextId: ").append(toIndentedString(nextId)).append("\n");
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
