/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

public class PersonAudId implements Serializable {

    private Integer pk;
    private Integer rev;

    public Integer getPk() {
        return this.pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getRev() {
        return this.rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAud)) return false;
        final PersonAud personAud = (PersonAud) o;
        return Objects.equals(getPk(), personAud.getPk()) &&
                Objects.equals(getRev(), personAud.getRev());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk(),
                getRev());
    }
}
