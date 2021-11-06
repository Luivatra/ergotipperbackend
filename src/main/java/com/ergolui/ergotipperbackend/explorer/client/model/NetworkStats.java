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
 * NetworkStats
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-05T14:07:15.709Z[GMT]")
public class NetworkStats {
  @SerializedName("uniqueAddressesNum")
  private Long uniqueAddressesNum = null;

  public NetworkStats uniqueAddressesNum(Long uniqueAddressesNum) {
    this.uniqueAddressesNum = uniqueAddressesNum;
    return this;
  }

   /**
   * Get uniqueAddressesNum
   * @return uniqueAddressesNum
  **/
  @Schema(required = true, description = "")
  public Long getUniqueAddressesNum() {
    return uniqueAddressesNum;
  }

  public void setUniqueAddressesNum(Long uniqueAddressesNum) {
    this.uniqueAddressesNum = uniqueAddressesNum;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NetworkStats networkStats = (NetworkStats) o;
    return Objects.equals(this.uniqueAddressesNum, networkStats.uniqueAddressesNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uniqueAddressesNum);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NetworkStats {\n");
    
    sb.append("    uniqueAddressesNum: ").append(toIndentedString(uniqueAddressesNum)).append("\n");
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