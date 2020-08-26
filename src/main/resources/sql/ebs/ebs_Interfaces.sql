XODP_EO_CCONTABIL_EMPRESA_V
XODP_EO_CENTROCUSTO_EMPRESA_V
XODP_EO_USUARIO_EMPRESA_NEW_V

XODP_EO_INSERT_INTERFACE_P(P_VENDOR_ID          IN NUMBER,
                                                       P_INVOICE_AMOUNT     IN NUMBER,
                                                       P_INVOICE_DATE       IN DATE,
                                                       P_INVOICE_NUM        IN VARCHAR2,
                                                       P_DESCRIPTION        IN VARCHAR2,
                                                       P_ORG_ID             IN NUMBER, 
                                                       P_ATTRIBUTE_CATEGORY IN VARCHAR2)

XODP_EO_INSERT_INTERF_LINES_P(P_INVOICE_ID     IN NUMBER,
                                                          P_AMOUNT         IN NUMBER,
                                                          P_LINE_NUMBER    IN NUMBER,
                                                          P_PO_LINE_NUMBER IN NUMBER,
                                                          P_ORG_ID         IN NUMBER,
                                                          P_DESCRIPTION    IN VARCHAR2)
                                                          
-- Rodar o Concurrent
BEGIN 
    EO_RUN_AP_IMPORT_INTERFACE_P;
 END;