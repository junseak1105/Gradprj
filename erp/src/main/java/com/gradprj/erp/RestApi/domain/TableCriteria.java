package com.gradprj.erp.RestApi.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class TableCriteria {

        private int pageNum;
        private int amount;

        private String type;
        private String keyword;

        public TableCriteria() {
            this(1, 10);
        }

        public TableCriteria(int pageNum, int amount) {
            this.pageNum = pageNum;
            this.amount = amount;
        }

        public String[] getTypeArr() {

            return type == null? new String[] {}: type.split("");
        }
    }

