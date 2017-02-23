package net.unit8.sigcolle.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author kawasima
 */
@Data
public class SignatureForm extends FormBase {
    @DecimalMin("1")
    @DecimalMax("9999")
    private String campaignId;

    @NotNull
    @Length(min = 1, max = 50)
    private String name;

    @Length(max = 5000)
    private String signatureComment;

    public Long getCampaignIdLong() {
        return Long.parseLong(campaignId);
    }

}
