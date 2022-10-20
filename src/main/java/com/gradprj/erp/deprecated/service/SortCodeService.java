package com.gradprj.erp.deprecated.service;

import java.sql.SQLException;

public interface SortCodeService {
    String getSortLv1() throws SQLException;
    String getSortLv2(String sortlv1) throws SQLException;
    String getSortCode(String sortlv1, String sortlv2) throws SQLException;
    String deleteSortLv1(String sortlv1);
    String deleteSortLv2(String sortlv1, String sortlv2);
    String deleteSortCode(String sortlv1, String sortlv2, String sortcode);
    String saveSortCode();
}

