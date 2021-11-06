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
 * EpochParameters
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-11-05T14:07:15.709Z[GMT]")
public class EpochParameters {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("height")
  private Integer height = null;

  @SerializedName("storageFeeFactor")
  private Integer storageFeeFactor = null;

  @SerializedName("minValuePerByte")
  private Integer minValuePerByte = null;

  @SerializedName("maxBlockSize")
  private Integer maxBlockSize = null;

  @SerializedName("maxBlockCost")
  private Integer maxBlockCost = null;

  @SerializedName("blockVersion")
  private Integer blockVersion = null;

  @SerializedName("tokenAccessCost")
  private Integer tokenAccessCost = null;

  @SerializedName("inputCost")
  private Integer inputCost = null;

  @SerializedName("dataInputCost")
  private Integer dataInputCost = null;

  @SerializedName("outputCost")
  private Integer outputCost = null;

  public EpochParameters id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @Schema(required = true, description = "")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EpochParameters height(Integer height) {
    this.height = height;
    return this;
  }

   /**
   * Get height
   * @return height
  **/
  @Schema(required = true, description = "")
  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public EpochParameters storageFeeFactor(Integer storageFeeFactor) {
    this.storageFeeFactor = storageFeeFactor;
    return this;
  }

   /**
   * Get storageFeeFactor
   * @return storageFeeFactor
  **/
  @Schema(required = true, description = "")
  public Integer getStorageFeeFactor() {
    return storageFeeFactor;
  }

  public void setStorageFeeFactor(Integer storageFeeFactor) {
    this.storageFeeFactor = storageFeeFactor;
  }

  public EpochParameters minValuePerByte(Integer minValuePerByte) {
    this.minValuePerByte = minValuePerByte;
    return this;
  }

   /**
   * Get minValuePerByte
   * @return minValuePerByte
  **/
  @Schema(required = true, description = "")
  public Integer getMinValuePerByte() {
    return minValuePerByte;
  }

  public void setMinValuePerByte(Integer minValuePerByte) {
    this.minValuePerByte = minValuePerByte;
  }

  public EpochParameters maxBlockSize(Integer maxBlockSize) {
    this.maxBlockSize = maxBlockSize;
    return this;
  }

   /**
   * Get maxBlockSize
   * @return maxBlockSize
  **/
  @Schema(required = true, description = "")
  public Integer getMaxBlockSize() {
    return maxBlockSize;
  }

  public void setMaxBlockSize(Integer maxBlockSize) {
    this.maxBlockSize = maxBlockSize;
  }

  public EpochParameters maxBlockCost(Integer maxBlockCost) {
    this.maxBlockCost = maxBlockCost;
    return this;
  }

   /**
   * Get maxBlockCost
   * @return maxBlockCost
  **/
  @Schema(required = true, description = "")
  public Integer getMaxBlockCost() {
    return maxBlockCost;
  }

  public void setMaxBlockCost(Integer maxBlockCost) {
    this.maxBlockCost = maxBlockCost;
  }

  public EpochParameters blockVersion(Integer blockVersion) {
    this.blockVersion = blockVersion;
    return this;
  }

   /**
   * Get blockVersion
   * @return blockVersion
  **/
  @Schema(required = true, description = "")
  public Integer getBlockVersion() {
    return blockVersion;
  }

  public void setBlockVersion(Integer blockVersion) {
    this.blockVersion = blockVersion;
  }

  public EpochParameters tokenAccessCost(Integer tokenAccessCost) {
    this.tokenAccessCost = tokenAccessCost;
    return this;
  }

   /**
   * Get tokenAccessCost
   * @return tokenAccessCost
  **/
  @Schema(required = true, description = "")
  public Integer getTokenAccessCost() {
    return tokenAccessCost;
  }

  public void setTokenAccessCost(Integer tokenAccessCost) {
    this.tokenAccessCost = tokenAccessCost;
  }

  public EpochParameters inputCost(Integer inputCost) {
    this.inputCost = inputCost;
    return this;
  }

   /**
   * Get inputCost
   * @return inputCost
  **/
  @Schema(required = true, description = "")
  public Integer getInputCost() {
    return inputCost;
  }

  public void setInputCost(Integer inputCost) {
    this.inputCost = inputCost;
  }

  public EpochParameters dataInputCost(Integer dataInputCost) {
    this.dataInputCost = dataInputCost;
    return this;
  }

   /**
   * Get dataInputCost
   * @return dataInputCost
  **/
  @Schema(required = true, description = "")
  public Integer getDataInputCost() {
    return dataInputCost;
  }

  public void setDataInputCost(Integer dataInputCost) {
    this.dataInputCost = dataInputCost;
  }

  public EpochParameters outputCost(Integer outputCost) {
    this.outputCost = outputCost;
    return this;
  }

   /**
   * Get outputCost
   * @return outputCost
  **/
  @Schema(required = true, description = "")
  public Integer getOutputCost() {
    return outputCost;
  }

  public void setOutputCost(Integer outputCost) {
    this.outputCost = outputCost;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EpochParameters epochParameters = (EpochParameters) o;
    return Objects.equals(this.id, epochParameters.id) &&
        Objects.equals(this.height, epochParameters.height) &&
        Objects.equals(this.storageFeeFactor, epochParameters.storageFeeFactor) &&
        Objects.equals(this.minValuePerByte, epochParameters.minValuePerByte) &&
        Objects.equals(this.maxBlockSize, epochParameters.maxBlockSize) &&
        Objects.equals(this.maxBlockCost, epochParameters.maxBlockCost) &&
        Objects.equals(this.blockVersion, epochParameters.blockVersion) &&
        Objects.equals(this.tokenAccessCost, epochParameters.tokenAccessCost) &&
        Objects.equals(this.inputCost, epochParameters.inputCost) &&
        Objects.equals(this.dataInputCost, epochParameters.dataInputCost) &&
        Objects.equals(this.outputCost, epochParameters.outputCost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, height, storageFeeFactor, minValuePerByte, maxBlockSize, maxBlockCost, blockVersion, tokenAccessCost, inputCost, dataInputCost, outputCost);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EpochParameters {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    storageFeeFactor: ").append(toIndentedString(storageFeeFactor)).append("\n");
    sb.append("    minValuePerByte: ").append(toIndentedString(minValuePerByte)).append("\n");
    sb.append("    maxBlockSize: ").append(toIndentedString(maxBlockSize)).append("\n");
    sb.append("    maxBlockCost: ").append(toIndentedString(maxBlockCost)).append("\n");
    sb.append("    blockVersion: ").append(toIndentedString(blockVersion)).append("\n");
    sb.append("    tokenAccessCost: ").append(toIndentedString(tokenAccessCost)).append("\n");
    sb.append("    inputCost: ").append(toIndentedString(inputCost)).append("\n");
    sb.append("    dataInputCost: ").append(toIndentedString(dataInputCost)).append("\n");
    sb.append("    outputCost: ").append(toIndentedString(outputCost)).append("\n");
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