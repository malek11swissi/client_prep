package com.arabsoft.pfe.projet.util;

public class StaticValue {

  private StaticValue() {
    throw new IllegalStateException("StaticValue class");
  }

  public static final String typeVocTypeOperationImmo = "VOC_TYPEOPERATION";
  public static final String typeVocRaisonOperationImmo = "VOC_RAISONOPERATION";
  public static final String typeVocEtatExploitImmo = "VOC_ETATEXPLOIT";
  public static final String typeVocMethAmort = "VOC_METHODEAMORT";
  public static final String typeVocOrigineImmo = "VOC_ORIGINE";
  public static final String typeVocSourceImmo = "VOC_SOURCE";
  public static final String typeVocTypeCaractImmo = "VOC_TYPECARACT";
  public static final String typeVocEtatImmo = "VOC_ETATIMMO";
  public static final String typeVocMarque = "VOC_MARQUE";
  public static final String typeVocNatPrpImmo = "VOC_NATUREPROP";
  public static final String typeVocNatUtlImmo = "VOC_NATUREIMMO";
  public static final String typeVocPeriodInterv = "VOC_PERIODINTERV";
  public static final String typeVocEtatInv = "VOC_ETAT_INV";

  // -----------------------------------------------------
  public static final String vocPriseEnCharge = "PRSCHG";
  public static final String vocTransfert = "TRANSFERT";
  public static final String vocEnExploit = "EN_EXPLOIT";
  public static final String vocEtatInvNouveau = "NOUVEAU";
  public static final String vocEtatInvEnCours = "ENCOURS";
  public static final String vocEtatInvCloture = "CLOTURE";
  public static final String vocRaisonOpNormal = "NORMAL";
  public static final String vocRaisonOpCorrectInv = "CORRECTION_INV";

  // ----------------------------------------------------
  public static final String urlAuthentification = "/api/v1/utilisateur/authentification";
  public static final String urlConnect = "/api/v1/utilisateur/connect";
  public static final String urlCrypt = "/api/v1/utilisateur/crypthkh";

  public static final String bearerString = "Bearer ";
  public static final String secretKey = "pL$d";

  public static final String traceTech = "TRACETECH";
  public static final String profilAdmin = "ADMIN";

}
