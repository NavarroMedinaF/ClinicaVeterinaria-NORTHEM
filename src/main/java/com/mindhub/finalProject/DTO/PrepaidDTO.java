package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Category;
import com.mindhub.finalProject.models.Prepaid;

public class PrepaidDTO {

    private long associateNumber;
    private CategoryDTO category;

    public PrepaidDTO() {
    }

    public PrepaidDTO(Prepaid prepaid) {
        this.associateNumber = prepaid.getAssociateNumber();
        this.category = new CategoryDTO(prepaid.getCategory());
    }

    /*GETTERS*/

    public long getAssociateNumber() {
        return associateNumber;
    }

    public CategoryDTO getCategory() {
        return category;
    }
}

