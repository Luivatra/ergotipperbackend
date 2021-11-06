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
 * NotFound
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-05T14:07:15.709Z[GMT]")
public class NotFound {
  @SerializedName("status")
  private Integer status = null;

  @SerializedName("reason")
  private String reason = null;

  public NotFound status(Integer status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(required = true, description = "")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public NotFound reason(String reason) {
    this.reason = reason;
    return this;
  }

   /**
   * Get reason
   * @return reason
  **/
  @Schema(required = true, description = "")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotFound notFound = (NotFound) o;
    return Objects.equals(this.status, notFound.status) &&
        Objects.equals(this.reason, notFound.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, reason);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NotFound {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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
