package com.lsuc.lsuc;

import org.hibernate.envers.RevisionListener;
import com.wavemaker.runtime.WMAppContext;
import com.wavemaker.runtime.security.SecurityService;

/**
 * Created by tribhuvand on 21/4/17.
 */
public class UserRevisionListener implements RevisionListener {


    @Override
    public void newRevision(Object revisionEntity) {
        String username = WMAppContext.getInstance().getSpringBean(SecurityService.class).getUserId();
        UserRevEntity exampleRevEntity = (UserRevEntity) revisionEntity;
        exampleRevEntity.setUsername(username);
    }
}