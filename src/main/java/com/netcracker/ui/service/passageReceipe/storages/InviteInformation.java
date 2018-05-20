/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.passageReceipe.storages;

import com.netcracker.ui.service.receipe.view.basic.objects.intermediate.storages.ReceipeInformation;

/**
 *
 * @author Artem
 */
public class InviteInformation {
    private ReceipeInformation receipeInformation;
    private String sessionId;
    private String inviterId;

    public ReceipeInformation getReceipeInformation() {
        return receipeInformation;
    }

    public void setReceipeInformation(ReceipeInformation receipeInformation) {
        this.receipeInformation = receipeInformation;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }  
}