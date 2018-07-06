package com.sphenon.basics.processing.factories;

/****************************************************************************
  Copyright 2001-2018 Sphenon GmbH

  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  License for the specific language governing permissions and limitations
  under the License.
*****************************************************************************/

import com.sphenon.basics.context.*;
import com.sphenon.basics.exception.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.processing.*;

public class Factory_ActivityState {
    private String state;
    private boolean state_is_valid = false;
    private ActivityState activity_state = null;

    public void setState(CallContext context, String state) {
        this.state = state;

        this.state_is_valid = true;

        if (this.state.equals("ATREST"            )) { this.activity_state = ActivityState.ATREST                     ; return; }
        if (this.state.equals("UNREADY"           )) { this.activity_state = ActivityState.UNREADY                    ; return; }
        if (this.state.equals("READY"             )) { this.activity_state = ActivityState.READY                      ; return; }
        if (this.state.equals("INPROGRESS"        )) { this.activity_state = ActivityState.INPROGRESS                 ; return; }
        if (this.state.equals("INTERRUPTED"       )) { this.activity_state = ActivityState.INTERRUPTED                ; return; }
        if (this.state.equals("ABORTED"           )) { this.activity_state = ActivityState.ABORTED                    ; return; }
        if (this.state.equals("COMPLETED"         )) { this.activity_state = ActivityState.COMPLETED                  ; return; }
        if (this.state.equals("SKIPPED"           )) { this.activity_state = ActivityState.SKIPPED                    ; return; }
        if (this.state.equals("VERIFIED"          )) { this.activity_state = ActivityState.VERIFIED                   ; return; }

        this.state_is_valid = false;
        this.activity_state = null;
    }

    public void validateState(CallContext context) throws ValidationFailure {
        if (! this.state_is_valid) {
            ValidationFailure.createAndThrow (context, "Invalid state parameter for Factory_ActivityState, got '%(got)', expected one of UNREADY, READY, REQUESTED, INPROGRESS, INTERRUPTED, ABORTED, COMPLETED, SKIPPED", "got", this.state);

            throw (ValidationFailure) null; // compiler insists
        }
    }

    public ActivityState create (CallContext call_context) {
        Context context = Context.create(call_context);
        CustomaryContext cc = CustomaryContext.create(context);
        try {
            this.validateState(context);
        } catch (ValidationFailure vf) {
            cc.throwPreConditionViolation(context, vf, "At least one factory parameter is invalid");
            throw (ExceptionPreConditionViolation) null;
        }

        return this.activity_state;
    }
}
